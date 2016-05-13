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
import shared.networking.Task;
import shared.utils.Log;

public class AdminListener implements Runnable {
	private BufferedImage scr;
	private Object sConfig;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Robot robo;

	public AdminListener(AdminData cd) {
		// Log.info ("WTF");
		this.ad = cd;
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
		Object o = ad.getIn().readObject();
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
//		switch (t.getTask()) {
//
//		case Task.SCREENSHOT:
//			scr = (BufferedImage) t.getO();
//			break;
//
//		case Task.GET_QUESTION_LIST:
//			if (t.getO() instanceof ArrayList<?>)
//				AdminMain.questionList = (LinkedList<Question>) t.getO();
//
//		case Task.UPDATE_QUESTIONS:
//			Log.info("receiving question list " + ((LinkedList<?>) t.getO()).size() + "   "
//					+ System.identityHashCode(t.getO()));
//			// TODO print out the task .tostring to see if there is an issue
//			// with the memory addresses
//			if (t.getO() instanceof LinkedList<?>) {
//				Log.info("question list is valid");
//				AdminMain.questionList = (LinkedList<Question>) t.getO();
//			}
//			break;
//		case Task.QUESTION_REMOVED:
//			ad.questionIsRemoved();
//			Log.info("Question removal confirmed!");
//			break;
//
//		}
		ad.getOut().flush();
		ad.getOut().reset();

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
