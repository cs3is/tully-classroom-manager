package theadmin.main;

import java.io.IOException;
import java.io.ObjectOutputStream;

import Admin.AdminData;
import util.Task;
import utils.AdminLog;

public class AdminButtons {
	AdminData ad;

	public AdminButtons(AdminData ad) {
		this.ad = ad;
	}

	public void getScr(ObjectOutputStream out, int compNum) {
		try {
			out.writeObject(new Task(Task.REQUEST_SCREENSHOT,computerNumber));
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
			for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				if (ad.isQuestionRemoved()) {

					AdminLog.info("remove successful");
					ad.questionIsRemoved();
					break;
				}
			}
			AdminLog.info("failed to remove qu3etsion");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendNotification(ObjectOutputStream out, String msg) {
		try {
			out.writeObject(new Task(Task.SEND_NOTIFICATION, msg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
