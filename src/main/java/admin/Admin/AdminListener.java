package Admin;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import Questions.Question;
import graphics.ClientTray;
import graphics.LockFrame;
import main.AdminMain;
import util.Task;
import utils.ClientLog;
import utils.ServerLog;

public class AdminListener implements Runnable {
	private AdminData ad;
	private Object sConfig;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Robot robo;

	public AdminListener(AdminData cd) {
		this.ad = cd;
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

			} catch (Exception e) {
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

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
		ClientLog.debug("clientlistener initialized.");
	}

	@SuppressWarnings("unchecked")
	public void readObj() throws ClassNotFoundException, IOException, Exception {
		ClientLog.debug("reading inputStream");
		Object o = ad.getIn().readObject();
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
	@SuppressWarnings("unchecked")
	private void actOnTask(Object o) {
		Task t = (Task) o;
		ClientLog.info("received " + t.getTask());
		switch (t.getTask()) {

		case Task.GET_SCREENSHOT:

			try {
				ad.getOut().writeObject(new Task(Task.SCREENSHOT, robo.createScreenCapture(
						new Rectangle(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight()))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			break;

		case Task.GET_QUESTION_LIST:
			if (t.getO() instanceof ArrayList<?>)
				AdminMain.questionList = (LinkedList<Question>) t.getO();

		case Task.UPDATE_QUESTIONS:
			System.out.println("receiving question list"+ ((LinkedList<Question>) t.getO()).size());
			if (t.getO() instanceof LinkedList<?>){
				System.out.println("question list is valid");
				AdminMain.questionList = (LinkedList<Question>) t.getO();
			}
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
