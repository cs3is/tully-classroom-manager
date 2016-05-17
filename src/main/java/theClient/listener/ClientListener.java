package theClient.listener;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import shared.networking.Task;
import shared.res.ConnectionData;
import shared.utils.Log;
import theClient.graphics.LockFrame;


public class ClientListener implements Runnable {
	private ConnectionData cd;
	private Object sConfig;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Robot robo;

	public ClientListener(ConnectionData cd) {
		this.cd = cd;
		Thread t = new Thread();
		try {
			Robot robo = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		t.start();

		do {
			try {

				readObj();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (sConfig == null);
		Log.info("Received init file");
		initializeObjectListener();
	}

	public void initializeObjectListener() {
		Thread th = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {

						readObj();

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
		Log.debug("clientlistener initialized.");
	}

	public void readObj() throws ClassNotFoundException, IOException {
		Log.debug("reading inputStream");
		Object o = cd.getIn().readObject();
		Log.debug("read inputstream");

		if (o instanceof Task) {

			actOnTask(o);

		} else {
			Log.error("Client has recieved an unrecognized object;ayy lmao");
		}
	}

	/**
	 * This method receives a task from the thread, and then tells the server
	 * what to do based on the task's contents.
	 *
	 * @param o
	 *            The Task that is sent to the server, in the form of an object
	 */
	private void actOnTask(Object o) {
		Task t = (Task) o;
		Log.info("received " + t.getTask());
		switch (t.getTask()) {
		case A_GET_QUESTION_lIST:
			break;
		case A_REMOVE_FIRST_QUESTION:
			break;
		case A_REMOVE_QUESTIONS:
			break;
		case A_REQUEST_SCREENSHOT:
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
		case S_DISABLE_COMPUTER:
			break;
		case S_ENABLE_COMPUTER:
			break;
		case S_GET_PROCESSES:
			break;
		case S_GET_SCREENSHOT:
			break;
		case S_INIT:
			break;
		case S_QUESTION_ADDED:
			break;
		case S_QUESTION_NOT_ADDED:
			break;
		case S_QUESTION_REMOVED:
			break;
		case S_SCREENSHOT:
			break;
		case S_SENDING_QUESTIONS:
			break;
		case S_SEND_NOTIFICATION:
			break;
		case S_UPDATE_QUESTIONS:
			break;
		default:
			break;
		}

//		case Task.QUESTION_ADDED:
//			//TODO RE-ADD OLD CD METHODS cd.setQuestionAdded(true);
//			Log.info("received QUESTION_ADDED");
//			// TODO Add QUESTION_NOT_ADDED, make both send baloon messages too.
//			break;
//		case Task.INIT:
//			Log.info("received INIT");
//			sConfig = t.getO();
//			// clienttray.initInit();
//			break;
//
//		case Task.QUESTION_REMOVED:
//			Log.info("received QUESTION_REMOVED");
//			//TODO RE-ADD OLD CLIENTDATA METHODS TO CONNECTION DATA cd.setCountdownBegin(true);
//			break;
//
//		case Task.SEND_NOTIFICATION:
//			Log.info(t.getText());
//			break;
//
//		case Task.GET_SCREENSHOT:
//
//			try {
//				cd.getOut().writeObject(new Task(Task.SCREENSHOT, robo.createScreenCapture(
//						new Rectangle(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight()))));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//
//			}
//			break;
//
//		case Task.GET_PROCESSES:
//			// METHOD IN CLIENTTRAY CALLED listTask() DOES THIS
//			break;
//
//		case Task.DISABLE_COMPUTER:
//			try {
//				robo = new Robot();
//				new LockFrame(robo.createScreenCapture(
//						new Rectangle(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight())));
//			} catch (AWTException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			break;
//
//		case Task.SYNC:
//			// DO WE EVEN NEED THIS?
//			break;
//
//		}
//		// WHY AM I COMMENTING IN ALL CAPS?
	}

	@Override
	public void run() {
		while (true) {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public Object getConfig() {
		return sConfig;
	}

}
