package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Questions.Question;
import util.Task;
import util.infoForClientToReceiveAndParseAndProbablyUseToo;
//import util.infoForClientToReceiveAndParseAndProbablyUseToo;
import utils.ServerConfigManager;
import utils.ServerLog;

public class UserListener implements Runnable {

	UserInformation u;
	Socket connection;
	Long timeBetweenQuestions;

	public UserListener(UserInformation u, Socket connection) {
		this.u = u;
		this.connection = connection;
		
	}

	@Override
	public void run() {
		timeBetweenQuestions = ServerConfigManager.getLong("TIME_BETWEEN_QUESTIONS")*1000000000L;
		try {
		//	u.out().reset();
			Thread.sleep(125);
			ServerLog.debug("sending init");
			u.out().writeObject(new Task(Task.INIT,new infoForClientToReceiveAndParseAndProbablyUseToo(timeBetweenQuestions)));
			ServerLog.debug("sent init");
		} catch (IOException e2) {
			e2.printStackTrace();
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		while (true) {
			try {
				Object o = u.in().readObject();

				if (o instanceof Task) {

					actOnTask(o);

				} else {
					ServerLog.error("Server has recieved an unrecognized object from "+u.getUserName()+" on the computer "+u.getHostname());
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				ServerLog.warn("Lost connection with "+u.getHostname());
				break;
				
			}
		}
		try {
			connection.close();
			ServerLog.info("Successfully closed connection w/ "+u.getHostname());
		} catch (IOException e1) {
			e1.printStackTrace();
			ServerLog.error("Error closing connection w/ "+u.getHostname());
		}
		
	}

	/**
	 * This method receives a task from the thead, and then tells the server what to do based on the task's contents.
	 * 
	 * @param o
	 *            The Task that is sent to the server, in the form of an object
	 */
	private void actOnTask(Object o) {
		Task t = (Task) o;
		switch (t.getTask()) {

		case Task.ASK_QUESTION:
			ServerLog.info("received "+t.getTask());
			if(u.getLastQuestionTime() == 0 || u.getLastQuestionTime()-System.nanoTime() > timeBetweenQuestions){
				u.setLastQuestionTime(System.nanoTime());
				Server.getQuestionList().add(new Question(u.getHostname(),u.getUserName()));
				//SQL if (LastQuestionAsked == 0 || lastQuestionAsked-currentTime > maxTime){
				//questionList.add (computernumber)
				try {
					ServerLog.info("sending QUESTION_ADDED");
					u.out().writeObject(new Task(Task.QUESTION_ADDED));
					ServerLog.info("sent QUESTION_ADDED");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					ServerLog.info("sending QUESTION_NOT_ADDED");
					u.out().writeObject(new Task(Task.QUESTION_NOT_ADDED));
					ServerLog.info("sent QUESTION_NOT_ADDED");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case Task.REMOVE_QUESTION:
			ServerLog.info("removed question "+Server.getQuestionList().poll());
			ServerLog.info("sending REMOVED_QUESTION");
			try {
				u.out().writeObject(new Task(Task.QUESTION_REMOVED));
			} catch (IOException e) {
				e.printStackTrace();
			}
			ServerLog.info("sent REMOVED_QUESTION");
			break;

		case Task.SUBMIT_LAB:
			break;

		case Task.CLIENT_ERROR:
			break;

		case Task.SYNC:
			break;

		}

	}

}
