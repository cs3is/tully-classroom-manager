package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Task;

public class UserInformation {
	/**
	 * The nanotime at which the last question was asked
	 */
	long LQT = 0;

	private String Hostname;
	private String userName;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public UserInformation(String userName, String Hostname, ObjectInputStream in, ObjectOutputStream out) {
		this.userName = userName;
		this.Hostname = Hostname;
		this.in = in;
		this.out = out;

	}

	public void setLastQuestionTime(Long s) {
		LQT = s;
	}

	public long getLastQuestionTime() {
		return LQT;
	}

}
