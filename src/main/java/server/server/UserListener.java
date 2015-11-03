package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import util.Task;
import utils.ServerLog;

public class UserListener implements Runnable {

	UserInformation u;

	public UserListener(UserInformation u) {
		this.u = u;
		
	}

	@Override
	public void run() {

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
			}
		}
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

		case Task.ASK_QUESTION:
			//SQL if (LastQuestionAsked == 0 || lastQuestionAsked-currentTime > maxTime){
			//questionList.add (computernumber)
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
