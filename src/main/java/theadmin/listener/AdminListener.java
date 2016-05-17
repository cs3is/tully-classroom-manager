package theadmin.listener;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import theClient.graphics.ClientTray;
import theClient.graphics.LockFrame;
import theadmin.main.AdminMain;
import shared.networking.Task;
import shared.res.ConnectionData;
import shared.res.Question;
import shared.utils.Log;

public class AdminListener implements Runnable {
	private ConnectionData conData;
	private BufferedImage scr;
	private Object sConfig;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Robot robo;

	public AdminListener(ConnectionData conData) {
		// Log.info ("WTF");
		this.conData = conData;
		Thread t = new Thread();
		try {
			Robot robo = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		// Log.info ("WTF");
		t.start();
		do {
			try {

				readObj();

			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.info ("WTFasdf");

		} while (sConfig == null);
		Log.info("Received init file");
		initializeObjectListener();
	}

	public void initializeObjectListener() {
		Thread th = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						// Log.info ("WTF");
						readObj();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
		Log.debug("clientlistener initialized.");
	}

	@SuppressWarnings("unchecked")
	public void readObj() throws ClassNotFoundException, IOException, Exception {
		Log.debug("reading inputStream");
		Object o = conData.getIn().readObject();
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
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void actOnTask(Object o) throws IOException {
		Task t = (Task) o;
		Log.info("received " + t.getTask());

		switch(t.getTask()){
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
			AdminMain.getAdminData().questionIsRemoved();
			Log.info("Question removal confirmed!");
			break;
		case S_SCREENSHOT:
			//TODO FILL THIS IN WITH WHAT USED TO BE THERE BECAUSE I ATE IT WITH MY CLIPBOARD
			break;
		case S_SENDING_QUESTIONS:
			break;
		case S_SEND_NOTIFICATION:
			break;
		case S_UPDATE_QUESTIONS:
			Log.info("receiving question list " + ((LinkedList<?>) t.getObject()).size() + "   "
					+ System.identityHashCode(t.getObject()));
			// TODO print out the task .tostring to see if there is an issue
			// with the memory addresses
			if (t.getObject() instanceof LinkedList<?>) {
				Log.info("question list is valid");
				AdminMain.questionList = (LinkedList<Question>) t.getObject();
			}
			break;
		default:
			break;

		}

		//TODO MOVE THIS TO NEW SWITCH

//		switch (t.getTask()) {
//
//		case Task.SCREENSHOT:
//
//
//		case Task.GET_QUESTION_LIST:
//
//
//		case Task.UPDATE_QUESTIONS:
//
//			break;
//		case Task.QUESTION_REMOVED:
//
//			break;
//
//		}
		conData.getOut().flush();
		conData.getOut().reset();

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
