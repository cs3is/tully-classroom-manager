package Questions;

public class Question {

	private final long TIME_ASKED = System.nanoTime();
	private String question;
	private String computerName;
	private String userName;

	public Question(String question, String computerName, String userName) {
		this.question = question;
		this.computerName = computerName;
		this.userName = userName;
	}

	public int getTIME_ASKED() {
		return (int) (System.nanoTime() - TIME_ASKED) / 1000000000;
	}

	public String getQuestion() {
		return question;
	}

	public String getComputerName() {
		return computerName;
	}

	public String getUserName() {
		return userName;
	}
}
