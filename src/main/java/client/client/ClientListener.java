package client;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import graphics.ClientTray;
import util.Task;
import utils.ClientLog;
import utils.ServerLog;

public class ClientListener implements Runnable {
	private ClientData cd;
	private Object sConfig;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Robot robo;

	public ClientListener(ClientData cd) {
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
		ClientLog.info("Received init file");
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
		ClientLog.debug("clientlistener initialized.");
	}

	public void readObj() throws ClassNotFoundException, IOException {
		ClientLog.debug("reading inputStream");
		Object o = cd.getIn().readObject();
		ClientLog.debug("read inputstream");

		if (o instanceof Task) {

			actOnTask(o);

		} else {
			ClientLog.error("Client has recieved an unrecognized object;ayy lmao");
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
		ClientLog.info("received " + t.getTask());
		switch (t.getTask()) {

		case Task.QUESTION_ADDED:
			cd.setQuestionAdded(true);
			ClientLog.info("received QUESTION_ADDED");
			// TODO Add QUESTION_NOT_ADDED, make both send baloon messages too.
			break;
		case Task.INIT:
			ClientLog.info("received INIT");
			sConfig = t.getO();
			// clienttray.initInit();
			break;

		case Task.QUESTION_REMOVED:
			ClientLog.info("received QUESTION_REMOVED");
			cd.setCountdownBegin(true);
			break;

		case Task.SEND_NOTIFICATION:
			System.out.println(t.getText());
			break;

		case Task.GET_SCREENSHOT:
			
			try {
				cd.getOut().writeObject(new Task(Task.SCREENSHOT,robo.createScreenCapture(new Rectangle(0,0,(int)screenSize.getWidth(),(int)screenSize.getHeight()))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case Task.GET_PROCESSES:
			break;

		case Task.DISABLE_COMPUTER:
			//TODO new Frame();
			break;

		case Task.SYNC:
			break;

		}
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
