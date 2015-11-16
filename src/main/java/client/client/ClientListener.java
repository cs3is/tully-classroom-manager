package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Task;
import utils.Log;
import utils.ServerLog;

public class ClientListener implements Runnable {
	private ClientData cd;
	private Object sConfig;

	public ClientListener(ClientData cd) {
		this.cd = cd;
		Thread t = new Thread();
		t.start();
		initializeObjectListener();
	}

	public void initializeObjectListener() {
		Thread th = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Log.debug("reading inputStream");
						Object o = cd.getIn().readObject();
						Log.debug("read inputstream");

						if (o instanceof Task) {

							actOnTask(o);

						} else {
							Log.error("Client has recieved an unrecognized object");
						}

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

	/**
	 * This method receives a task from the thread, and then tells the server what to do based on the task's contents.
	 * 
	 * @param o
	 *            The Task that is sent to the server, in the form of an object
	 */
	private void actOnTask(Object o) {
		Task t = (Task) o;
		Log.info("received " + t.getTask());
		switch (t.getTask()) {

		case Task.QUESTION_ADDED:
			cd.setQuestionAdded(true);
			Log.info("received QUESTION_ADDED");
			// TODO Add QUESTION_NOT_ADDED, make both send baloon messages too.
			break;
		case Task.INIT:
			Log.info("received INIT");
			// sConfig = t.getO();
			break;

		case Task.QUESTION_REMOVED:
			break;

		case Task.SEND_NOTIFICATION:
			System.out.println(t.getText());
			break;

		case Task.GET_SCREENSHOT:
			break;

		case Task.GET_PROCESSES:
			break;

		case Task.DISABLE_COMPUTER:
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
}
