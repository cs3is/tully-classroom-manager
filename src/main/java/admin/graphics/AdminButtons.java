package graphics;

import java.io.IOException;
import java.io.ObjectOutputStream;

import Admin.AdminData;
import Questions.Question;
import util.Task;
import utils.AdminLog;

public class AdminButtons {
	AdminData ad;

	public AdminButtons(AdminData ad) {
		this.ad = ad;
	}

	public void getScr(ObjectOutputStream out, int computerNumber) {
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

	public Question removeQuestion(ObjectOutputStream out) throws Exception {
		try {
			out.writeObject(new Task(Task.REMOVE_QUESTION));
			Question q = null;
			for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Question temp = ad.getQuestion();
				if(temp!=null){
					q = temp;

				}
				boolean isRemoved = ad.isQuestionRemoved();
				if (isRemoved&&q != null) {

					AdminLog.info("remove successful -- removed "+q);
					return q;
				}
				else if(isRemoved^q!=null){
					AdminLog.error("ONE OF THE FOLLOWING WAS NOT CORRECT:\nisRemoved: "+isRemoved+" (true) \nq: "+q+" (not null)");
					throw new Exception();
				}
			}
			AdminLog.info("failed to remove qu3etsion -- removed "+q);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
