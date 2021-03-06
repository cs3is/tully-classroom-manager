package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientData {

	private String computerName;
	private String IPAddress;
	private boolean connected;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Boolean bool;
	private Boolean questionAdded;
	private Boolean countdownBegin = false;

	public Boolean getCountdownBegin() {
		return countdownBegin;
	}

	public void setCountdownBegin(Boolean countdownBegin) {
		this.countdownBegin = countdownBegin;
	}

	public ClientData(ObjectInputStream in, ObjectOutputStream out) {
		this.in = in;
		this.out = out;
	}

	public String getComputerName() {
		return computerName;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public boolean getBoolean() {
		boolean t = bool;
		bool = null;
		return t;

	}

	public boolean isConnected() {
		return connected;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public Boolean getQuestionAdded() {
		return questionAdded;
	}

	public void setQuestionAdded(Boolean b) {
		questionAdded = b;
	}

}
