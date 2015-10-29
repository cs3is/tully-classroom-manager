package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Task;


public class UserInformation {
	/**
	 * The nanotime at which the last question was asked
	 */
	long LQT = 0;
	
	public UserInformation(String userName, String Hostname, ObjectInputStream in, ObjectOutputStream out){

		
	}
	
	public void setLastQuestionTime(Long s){
		LQT = s;
	}
	public long getLastQuestionTime(){
		return LQT;
	}
	
}
