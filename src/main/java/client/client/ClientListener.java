package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Task;
import utils.ServerLog;

public class ClientListener implements Runnable {
	private ClientData cd = null;

	public ClientListener(ClientData cd) {
		this.cd = cd;
		Thread t = new Thread();
		t.start();
		initializeObjectListener();
	}

	public void initializeObjectListener() {
		Thread th = new Thread(new Runnable() {
			public void run() {
				try {
					Object o = cd.in.readObject();
					if (o instanceof Task) {

						actOnTask(o);

					} else {
						ServerLog.error("Server has recieved an unrecognized object"); // TODO make this print out the computer that sent the invalid
																						// object as well
					}

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This method receives a task from the trhead, and then tells the server what to do based on the task's contents.
	 * 
	 * @param o
	 *            The Task that is sent to the server, in the form of an object
	 */
	private void actOnTask(Object o) {
		Task t = (Task) o;
		switch (t.getTask()) {

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
