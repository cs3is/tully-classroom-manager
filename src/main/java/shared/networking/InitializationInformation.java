package shared.networking;

import java.io.Serializable;

/**
 * <p>
 * This should be the first thing sent to any client or admin that connects to
 * the server, and will contain any settings that need to be updated every
 * startup.
 * </p>
 *
 * @author Andrew
 *
 */
public class InitializationInformation implements Serializable {
	// TODO add things to set on the client or admin in here.
	long timeBetweenQuestions = -9999999;

	public InitializationInformation(long timeBetweenQuestions) {
		this.timeBetweenQuestions = timeBetweenQuestions;
	}

	public long getTimeBetweenQuestions() {
		return timeBetweenQuestions;
	}

	public void setTimeBetweenQuestions(long timeBetweenQuestions) {
		this.timeBetweenQuestions = timeBetweenQuestions;
	}
}
