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
import utils.ServerConfigManager;
import utils.ServerLog;

public class UserListener implements Runnable {

	Info u;
	Socket connection;
	Long timeBetweenQuestions;
	BufferedImage mostRecentScreenshot;

	public UserListener(Info u, Socket connection) {
		this.u = u;
		this.connection = connection;

	}

	@Override
	public void run() {
//		System.out.println("created a userListener");
		timeBetweenQuestions = ServerConfigManager.getLong("TIME_BETWEEN_QUESTIONS");
		ServerLog.info ("tbq = "+timeBetweenQuestions);
		try {
			// u.out().reset();
			Thread.sleep(125);
			ServerLog.debug("sending init");
			u.out().writeObject(
					new Task(Task.INIT, new infoForClientToReceiveAndParseAndProbablyUseToo(timeBetweenQuestions)));
			ServerLog.debug("sent init");
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		while (true) {
			try {
				Object o = u.in().readObject();

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

			if (u.getLastQuestionTime() == 0 || u.getLastQuestionTime() - System.nanoTime() > timeBetweenQuestions) {

				u.setLastQuestionTime(System.nanoTime());
				// this works System.out.println("The classroom of the added
				// student is " + u.getClassroom());
				Server.getQuestionList(u.getClassroom()).add(new Question(u.getHostname() , u.getUserName()));
				System.out.println(Server.getQuestionList(u.getClassroom()).size());
				// SQL if (LastQuestionAsked == 0 ||
				// lastQuestionAsked-currentTime > maxTime){
				// questionList.add (computerNumber)

				u.out().writeObject(new Task(Task.QUESTION_ADDED));
				ServerLog.info("sent QUESTION_ADDED");

			} else {

				ServerLog.info("sending QUESTION_NOT_ADDED");
				u.out().writeObject(new Task(Task.QUESTION_NOT_ADDED));
				ServerLog.info("sent QUESTION_NOT_ADDED");

			}
			
			Thread.sleep(3000);
			u.out().writeObject(new Task(Task.QUESTION_REMOVED));
			break;
		case Task.REMOVE_QUESTION:
			Question temp = Server.getQuestionList(u.getClassroom()).poll();
			ServerLog.info("removed question " + temp);
			ServerLog.debug("sending REMOVED_QUESTION");
			ServerLog.error(Server.getConnectedClients()+"");
					for (int i = 0 ; i < Server.getConnectedClients().get(u.getClassroom()).size();i++){
						if(Server.getConnectedClients().get(u.getClassroom()).get(i).getHostname()!=null&&Server.getConnectedClients().get(u.getClassroom()).get(i).getHostname()==temp.getHostName()){
							ServerLog.info("lloooooppp");
							Server.getConnectedClients().get(u.getClassroom()).get(i).out().writeObject(new Task(Task.QUESTION_REMOVED));
						}
					}
					ServerLog.info("sent REMOVED_QUESTION");
			u.out().writeObject(new Task(Task.QUESTION_REMOVED));

			ServerLog.info("sent REMOVED_QUESTION");
			break;

		case Task.SUBMIT_LAB:
			break;

		case Task.CLIENT_ERROR:
			break;

		case Task.SYNC:
			break;

		case Task.REQUEST_VALUE:

			u.out().writeObject(ServerConfigManager.getCfg(t.getText()));

		case Task.SCREENSHOT:
			mostRecentScreenshot = (BufferedImage) t.getO();
			// AdminInformation.getOut().writeOut(mostRecentScreenshot);
			break;

		case Task.GET_QUESTION_LIST:
			System.out.println("sending list of questions"+Server.getQuestionList(u.getClassroom()).size());
			Task tt =new Task(Task.UPDATE_QUESTIONS, Server.getQuestionList(u.getClassroom()));
			System.out.println(((LinkedList<?>) (tt.getO())).size());
			u.out().writeObject(tt);
			//	u.out().writeObject(Server.getQuestionList(u.getClassroom()));
			break;

		}
		u.out().flush();
		u.out().reset();
	}

}
