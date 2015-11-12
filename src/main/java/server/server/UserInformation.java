package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Task;

public class UserInformation {
	/**
	 * The nanotime at which the last question was asked
	 */
	long LQT = 0;

	private int classroom;
	private String Hostname;
	private String userName;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public UserInformation(int classroom,String userName, String Hostname, ObjectInputStream in, ObjectOutputStream out) {
		this.classroom = classroom;
		this.userName = userName;
		this.Hostname = Hostname;
		this.in = in;
		this.out = out;

	}

	public int getClassroom(){
		return classroom;
	}
	
	public void setLastQuestionTime(Long s) {
		LQT = s;
	}

	public long getLastQuestionTime() {
		return LQT;
	}
	
	public ObjectInputStream in(){
		return in;
	}
	
	public ObjectOutputStream out(){
		return out;
	}


	public String getHostname() {
		return Hostname;
	}

	public String getUserName() {
		return userName;
	}
	
}
