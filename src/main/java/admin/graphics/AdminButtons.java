package graphics;

import java.io.IOException;
import java.io.ObjectOutputStream;

import util.Task;

public class AdminButtons {
	private void getScr(ObjectOutputStream out) {
		try {
			out.writeObject(new Task(Task.GET_SCREENSHOT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void lockScr(ObjectOutputStream out) {
		try {
			out.writeObject(new Task(Task.DISABLE_COMPUTER));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendNotification() {

	}
}
