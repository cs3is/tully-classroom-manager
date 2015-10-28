package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Task;
import utils.ServerLog;

public class UserListener implements Runnable {

	ObjectInputStream in;
	ObjectOutputStream out;

	public UserListener(ObjectInputStream in, ObjectOutputStream out) {
		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {

		while (true) {
			try {
				Object o = in.readObject();

				if (o instanceof Task) {

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

	}

}
