package Questions;

public class Question {

	private final long TIME_ASKED = System.nanoTime();
	private String computerName;
	private String userName;

	public Question(String computerName, String userName) {
		this.computerName = computerName;
		this.userName = userName;
	}

	public int getTIME_ASKED() {
		return (int) (System.nanoTime() - TIME_ASKED) / 1000000000;
	}


	public String getComputerName() {
		return computerName;
	}

	public String getUserName() {
		return userName;
	}
}
