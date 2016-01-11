package graphics;

import java.io.IOException;
import java.io.ObjectOutputStream;

import util.Task;

public class AdminButtons {
	public void getScr(ObjectOutputStream out) {
		try {
			out.writeObject(new Task(Task.GET_SCREENSHOT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lockScr(ObjectOutputStream out) {
		try {
			out.writeObject(new Task(Task.DISABLE_COMPUTER));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeQuestion(ObjectOutputStream out) {
		try {
			out.writeObject(new Task(Task.REMOVE_QUESTION));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendNotification(ObjectOutputStream out, String msg) {
		try {
			out.writeObject(new Task(Task.SEND_NOTIFICATION,msg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
