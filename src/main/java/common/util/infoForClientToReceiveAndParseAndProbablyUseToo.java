
package util;

import java.io.Serializable;

public class infoForClientToReceiveAndParseAndProbablyUseToo implements Serializable {
	long timeBetweenQuestions = -9999999;

	public infoForClientToReceiveAndParseAndProbablyUseToo(long timeBetweenQuestions) {
		this.timeBetweenQuestions = timeBetweenQuestions;
	}

	public long getTimeBetweenQuestions() {
		return timeBetweenQuestions;
	}

	public void setTimeBetweenQuestions(long timeBetweenQuestions) {
		this.timeBetweenQuestions = timeBetweenQuestions;
	}
}
