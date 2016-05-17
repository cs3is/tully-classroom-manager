package server.listener;

import java.awt.image.BufferedImage;
import java.io.IOException;

import server.res.ServerConfig;
import shared.networking.InitializationInformation;
import shared.networking.Task;
import shared.networking.TaskEnum;
import shared.res.ConnectionData;
import shared.utils.Log;

public class ServerListener implements Runnable {

	ConnectionData con;
	Long timeBetweenQuestions = Long.MIN_VALUE; // TODO set this
	BufferedImage mostRecentScreenshot;

	public ServerListener(ConnectionData con) {
		this.con = con;

		Log.info("tbq = " + timeBetweenQuestions);
		try {
			// u.out().reset();
			Thread.sleep(125);
			Log.debug("sending init");
			con.getOut().writeObject(new Task(TaskEnum.S_INIT, new InitializationInformation(ServerConfig.TIME_BETWEEN_QUESTIONS)));
			Log.debug("sent init");
		} catch (Exception e) {
			Log.printStackTrace(e);
		}

	}

	@Override
	public void run() {
		// ServerLog.info("created a userListener");

		while (true) {
			try {
				Object o = con.getIn().readObject();

				if (o instanceof Task) {

					actOnTask(o);

				} else {
					Log.error("Server has recieved an unrecognized object from " + con.getUserName()
							+ " on the computer " + con.getHostName());
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				Log.warn("Lost connection with " + con.getHostName());
				break;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			con.getConnection().close();
			Log.info("Successfully closed connection w/ " + con.getHostName());
		} catch (IOException e1) {
			e1.printStackTrace();
			Log.error("Error closing connection w/ " + con.getHostName());
		}

	}

	/**
	 * This method receives a task from the thead, and then tells the server
	 * what to do based on the task's contents.
	 *
	 * @param o
	 *            The Task that is sent to the server, in the form of an object
	 */
	@SuppressWarnings("incomplete-switch")
	private void actOnTask(Object o) throws Exception {
		Task t = (Task) o;

		//TODO update the switch
		switch(t.getTask()){
		case A_GET_QUESTION_lIST:
			break;
		case A_REMOVE_FIRST_QUESTION:
			break;
		case A_REMOVE_QUESTIONS:
			break;
		case C_ASK_QUESTION:
			break;
		case C_CAN_ASK:
			break;
		case C_CLIENT_ERROR:
			break;
		case C_SCREENSHOT:
			break;
		case C_SUBMIT_LAB:
			break;
		case REQUEST_VALUE:
			break;
		case SYNC:
			break;

		}
//		switch (t.getTask()) {
//
//		case Task.ASK_QUESTION:
//			ServerLog.info("received " + t.getTask());
//
//			// SQL if (LastQuestionAsked == 0 || lastQuestionAsked-currentTime >
//			// maxTime){
//			// questionList.add (computerNumber)
//
//			if (u.getLastQuestionTime() == 0 || u.getLastQuestionTime() - System.nanoTime() > timeBetweenQuestions) {
//
//				u.setLastQuestionTime(System.nanoTime());
//				// this works ServerLog.info("The classroom of the added
//				// student is " + u.getClassroom());
//				Server.getQuestionList(u.getClassroom()).add(new Question(u.getHostname(), u.getUserName()));
//				ServerLog.info("" + Server.getQuestionList(u.getClassroom()).size());
//				// SQL if (LastQuestionAsked == 0 ||
//				// lastQuestionAsked-currentTime > maxTime){
//				// questionList.add (computerNumber)
//
//				u.out().writeObject(new Task(Task.QUESTION_ADDED));
//				ServerLog.info("sent QUESTION_ADDED");
//
//			} else {
//
//				ServerLog.info("sending QUESTION_NOT_ADDED");
//				u.out().writeObject(new Task(Task.QUESTION_NOT_ADDED));
//				ServerLog.info("sent QUESTION_NOT_ADDED");
//
//			}
//
//			Thread.sleep(3000);
//			u.out().writeObject(new Task(Task.QUESTION_REMOVED));
//			break;
//		case Task.REMOVE_QUESTION:
//			Question temp = Server.getQuestionList(u.getClassroom()).poll();
//			ServerLog.info("removed question " + temp);
//			ServerLog.debug("sending REMOVED_QUESTION");
//
//			ServerLog.error(Server.getConnectedClients() + "");
//			if (temp != null) {
//				u.out().writeObject(new Task(Task.QUESTION_REMOVED));
//			}
//			ServerLog.info("sent REMOVED_QUESTION");
//			break;
//
//		case Task.SUBMIT_LAB:
//			break;
//
//		case Task.CLIENT_ERROR:
//			break;
//
//		case Task.SYNC:
//			break;
//
//		case Task.REQUEST_VALUE:
//
//			u.out().writeObject(ServerConfigManager.getCfg(t.getText()));
//
//		case Task.SCREENSHOT:
//			mostRecentScreenshot = (BufferedImage) t.getO();
//			// AdminInformation.getOut().writeOut(mostRecentScreenshot);
//			break;
//
//		case Task.GET_QUESTION_LIST:
//			ServerLog.info("sending list of questions" + Server.getQuestionList(u.getClassroom()).size());
//			Task tt = new Task(Task.UPDATE_QUESTIONS, Server.getQuestionList(u.getClassroom()));
//			ServerLog.info("" + ((LinkedList<?>) (tt.getO())).size());
//			u.out().writeObject(tt);
//			// u.out().writeObject(Server.getQuestionList(u.getClassroom()));
//			break;
//
//		}
		con.getOut().flush();
		con.getOut().reset();
	}

}
