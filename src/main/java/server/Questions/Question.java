package Questions;

import server.Info;

public class Question {

	private final long TIME_ASKED = System.nanoTime();
	private String computerName;
	private String userName;
	private Info u;

	public Question(Info info) {
		this.u = info;
	}

	public int getTIME_ASKED() {
		return (int) (System.nanoTime() - TIME_ASKED) / 1000000000;
	}

	public Info getU(){
		return u;
	}

//	public String getComputerName() {
//		return computerName;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
}
