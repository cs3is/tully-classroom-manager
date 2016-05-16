package theadmin.main;

import java.io.IOException;
import java.io.ObjectOutputStream;

import shared.networking.Task;
import shared.networking.TaskEnum;
import shared.res.ConnectionData;
import shared.res.Question;
import shared.utils.Log;

public class AdminButtons {
	ConnectionData conData;


	public AdminButtons(ConnectionData conData) {
		this.conData = conData;
	}

	public void getScr(ObjectOutputStream out, int compNum) {
		try {
			out.writeObject(new Task(TaskEnum.A_REQUEST_SCREENSHOT,compNum));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lockScr(ObjectOutputStream out,int compNum) {
		try {
			out.writeObject(new Task(TaskEnum.S_DISABLE_COMPUTER,compNum));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeQuestion(ObjectOutputStream out) {
		try {
			out.writeObject(new Task(TaskEnum.A_REMOVE_FIRST_QUESTION));
			for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				if (conData.isQuestionRemoved()) {

					Log.info("remove successful");
					conData.questionIsRemoved();
					break;
				}
			}
			Log.info("failed to remove qu3etsion");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendNotification(ObjectOutputStream out, String msg,int compNum) {
		try {
			out.writeObject(new Task(TaskEnum.S_SEND_NOTIFICATION,compNum, msg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
