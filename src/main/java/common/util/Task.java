package util;

import java.io.Serializable;

public class Task implements Serializable {

	// Only to be invoked by the Client
	public final static int ASK_QUESTION = 0;
	public final static int SUBMIT_LAB = 1;
	public final static int CLIENT_ERROR = 2;
	public final static int SCREENSHOT = 3;
//	public final static int CAN_ASK = 3;

	// Only to be invoked by the server TODO determine how commands from the server and admin client will work
	public final static int QUESTION_REMOVED = 20;
	public final static int SEND_NOTIFICATION = 21;
	public final static int GET_SCREENSHOT = 22;
	public final static int GET_PROCESSES = 23;
	public final static int DISABLE_COMPUTER = 24;
	public final static int QUESTION_ADDED = 25;
	public final static int QUESTION_NOT_ADDED = 26;
	public final static int INIT = 27;
	public final static int ENABLE_COMPUTER = 28;

	// Only to be invoked by the admin client
	public final static int REMOVE_QUESTION = 40;

	// To be invoked by either the Client or the server
	public final static int SYNC = 60;
	public final static int REQUEST_VALUE = 61;

	private int toDo = -1;
	private String text = null;
	private Object o = null;

	/**
	 * The basic type of object that will be sent in between the server and the client in order to communicate. Every task object will include an int
	 * called toDo, which will be interpreted by the recipient of this file, and then perform actions accordingly.
	 * 
	 * @param toDo
	 *            Int that corresponds with one of the predefined commands in this file and will be what this object will tell the recipient what to
	 *            do.
	 */
	public Task(int toDo) {
		this.toDo = toDo;
		System.out.println("The new task is "+ toDo);
	}

	/**
	 * The basic type of object that will be sent in between the server and the client in order to communicate. Every task object will include an int
	 * called toDo, which will be interpreted by the recipient of this file, and then perform actions accordingly.
	 * 
	 * @param toDo
	 *            Int that corresponds with one of the predefined commands in this file and will be what this object will tell the recipient what to
	 *            do.
	 * @param text
	 *            The text that will be associated with the given toDo int. Text should not be included if the task does not require it.
	 */
	public Task(int toDo, String text) {
		this.toDo = toDo;
		this.text = text;
	}
	
	public Task(int toDo, Object o) {
		this.toDo = toDo;
		this.o = o;
	}

	public int getTask() {
		return toDo;
	}

	public String getText() {
		return text;
	}
	
	public Object getO(){
		return o;
	}

	public String toString() {
		return "tried to print a Task with a value of " + toDo + ", and a message of \"" + text + "\"";
	}

}
