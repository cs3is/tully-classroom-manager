package server;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import Questions.Question;
import util.Task;
import util.infoForClientToReceiveAndParseAndProbablyUseToo;
import utils.ServerConfig;
import utils.ServerConfigManager;
import utils.ServerLog;

public class UserListener implements Runnable {

	private static final int ADMIN = -1;
	Info u;
	Socket connection;
	Long timeBetweenQuestions = ServerConfig.TIME_BETWEEN_QUESTIONS;
	BufferedImage mostRecentScreenshot;

	public UserListener(Info u, Socket connection) {
		this.u = u;
		this.connection = connection;

		ServerLog.info("tbq = " + timeBetweenQuestions);
		try {
			// u.out().reset();
			Thread.sleep(125);
			ServerLog.debug("sending init");
			u.out().writeObject(
					new Task(Task.INIT, new infoForClientToReceiveAndParseAndProbablyUseToo(timeBetweenQuestions)));
			ServerLog.debug("sent init");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// ServerLog.info("created a userListener");

		while (true) {
			try {
				Object o = getUserInfo().in().readObject();

				if (o instanceof Task) {

					actOnTask(o);

				} else {
					ServerLog.error("Server has recieved an unrecognized object from " + u.getUserName()
							+ " on the computer " + u.getHostname());
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				ServerLog.warn("Lost connection with " + u.getHostname());
				break;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			connection.close();
			ServerLog.info("Successfully closed connection w/ " + u.getHostname());
		} catch (IOException e1) {
			e1.printStackTrace();
			ServerLog.error("Error closing connection w/ " + u.getHostname());
		}

	}

	/**
	 * This method receives a task from the thead, and then tells the server
	 * what to do based on the task's contents.
	 *
	 * @param o
	 *            The Task that is sent to the server, in the form of an object
	 */
	private void actOnTask(Object o) throws Exception {
		Task t = (Task) o;
		switch (t.getTask()) {

		case Task.ASK_QUESTION:
			ServerLog.info("received " + t.getTask());

			// SQL if (LastQuestionAsked == 0 || lastQuestionAsked-currentTime >
			// maxTime){
			// questionList.add (computerNumber)

			if (getUserInfo().getLastQuestionTime() == 0 || getUserInfo().getLastQuestionTime() - System.nanoTime() > timeBetweenQuestions) {

				getUserInfo().setLastQuestionTime(System.nanoTime());
				// this works ServerLog.info("The classroom of the added
				// student is " + u.getClassroom());
				Server.getQuestionList(getUserInfo().getClassroom()).add(new Question(getUserInfo().getHostname(), getUserInfo().getUserName()));
				ServerLog.info("" + Server.getQuestionList(getUserInfo().getClassroom()).size());
				// SQL if (LastQuestionAsked == 0 ||
				// lastQuestionAsked-currentTime > maxTime){
				// questionList.add (computerNumber)

				getUserInfo().out().writeObject(new Task(Task.QUESTION_ADDED));
				ServerLog.info("sent QUESTION_ADDED");

			} else {

				ServerLog.info("sending QUESTION_NOT_ADDED");
				getUserInfo().out().writeObject(new Task(Task.QUESTION_NOT_ADDED));
				ServerLog.info("sent QUESTION_NOT_ADDED");

			}

			Thread.sleep(3000);
			getUserInfo().out().writeObject(new Task(Task.QUESTION_REMOVED));
			break;
		case Task.REMOVE_QUESTION:
			Question temp = Server.getQuestionList(u.getClassroom()).poll();
			ServerLog.info("removed question " + temp);
			ServerLog.debug("sending REMOVED_QUESTION");

			ServerLog.error(Server.getConnectedClients() + "");
			if(temp!=null){
			u.out().writeObject(new Task(Task.QUESTION_REMOVED,temp));
			}
			ServerLog.info("sent REMOVED_QUESTION to admin");
			Server.getConnectedClients().get(u.getClassroom()).get(temp.getHostName()).out().writeObject(new Task(Task.QUESTION_REMOVED,temp));
			break;

		case Task.SUBMIT_LAB:
			break;

		case Task.CLIENT_ERROR:
			break;

		case Task.SYNC:
			break;

		case Task.REQUEST_VALUE:
			getUserInfo().out().writeObject(ServerConfigManager.getCfg(t.getText()));
			break;

		case Task.SCREENSHOT:
			mostRecentScreenshot = (BufferedImage) t.getO();
			getUserInfo(ADMIN).out().writeObject(mostRecentScreenshot);
				//TODO need to find the computer number of selected computer.
			// AdminInformation.getOut().writeOut(mostRecentScreenshot);
			break;
		case Task.REQUEST_SCREENSHOT:
			Server.getConnectedClients().get(u.getClassroom()).get(t.getO());
			break;
		case Task.GET_QUESTION_LIST:
			ServerLog.info("sending list of questions" + Server.getQuestionList(getUserInfo().getClassroom()).size());
			Task tt = new Task(Task.UPDATE_QUESTIONS, Server.getQuestionList(getUserInfo().getClassroom()));
			ServerLog.info("" + ((LinkedList<?>) (tt.getO())).size());
			getUserInfo().out().writeObject(tt);
			// u.out().writeObject(Server.getQuestionList(u.getClassroom()));
			break;

		}
		getUserInfo().out().flush();
		getUserInfo().out().reset();
	}

	private Info getUserInfo(){
		return u;
	}
	private Info getUserInfo(int i){
		return Server.getConnectedClients().get(u.getClassroom()).get(i);
	}


}
