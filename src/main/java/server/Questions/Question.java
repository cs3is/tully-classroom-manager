package Questions;

import java.io.ObjectOutputStream;
import java.io.Serializable;

import server.Info;

public class Question implements Serializable{

	private final long TIME_ASKED = System.nanoTime();
	private String hostName;
	private String userName;
	private ObjectOutputStream out;

	public Question(String hn, String un) {
	 userName = un;
	 hostName = hn;
	}

	public int getTIME_ASKED() {
		return (int) (System.nanoTime() - TIME_ASKED) / 1000000000;
	}


	public String getHostName() {
		return hostName;
	}

	public String getUserName() {
		return userName;
	}
}
