package shared.networking;

import java.io.Serializable;

/**
 * TODO add more enums to this file as needed
 * <p>
 * Contains all of the enums to be used by the <code>AdminGui.java</code> in
 * order to help inform whatever computer it is being sent to of what needs to
 * be done.
 * </p>
 *
 * @author Andrew
 *
 */
public enum TaskEnum implements Serializable{
	/**
	 * <p>
	 * To be sent to the client to the server.
	 * </p>
	 * <p>
	 * Used to inform the server that the student at whichever computer sent a
	 * task containing this enum has a question for the teacher.
	 * </p>
	 */
	C_ASK_QUESTION,
	/**
	 * <p>
	 * To be sent to the client to the server.
	 * </p>
	 * <p>
	 * TODO determine what exactly this will be used for.
	 * </p>
	 */
	C_SUBMIT_LAB,
	/**
	 * <p>
	 * To be sent to the client to the server.
	 * </p>
	 * <p>
	 * Informs the server that there is a string containing an error that the
	 * client experienced attached to the task. This can be used for logging
	 * purposes and to give the teacher an idea of the status of all of the
	 * clients, and see if any of them aren't functioning properly.
	 * </p>
	 */
	C_CLIENT_ERROR,
	/**
	 * <p>
	 * To be sent to the client to the server.
	 * </p>
	 * <p>
	 * Informs the server that there is a screenshot of the client's screen
	 * attached to whatever task contains this enum.
	 * </p>
	 */
	C_SCREENSHOT,
	/**
	 * <p>
	 * To be sent to the client to the server.
	 * </p>
	 * <p>
	 * Asks the server whether or not the client can ask a question. The server
	 * should respond with an answer.
	 * </p>
	 */
	C_CAN_ASK,
	// ------------------------------------------------------------------------------------------

	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * Informs the client that the question has been removed from the queue by
	 * the teacher.
	 * </p>
	 */
	S_QUESTION_REMOVED,
	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * Attach to a task when the task contains a message to be sent to the
	 * client and displayed on their screen.
	 * </p>
	 */
	S_SEND_NOTIFICATION,
	/**
	 * <p>
	 * To be sent from the server to the admin.
	 * </p>
	 * <p>
	 * contains the screenshot
	 * </p>
	 */
	S_SCREENSHOT,
	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * Requests the client to send a screenshot to the server.
	 * </p>
	 */
	S_GET_SCREENSHOT,
	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * Requests a list of running processes from the client.
	 * </p>
	 */
	S_GET_PROCESSES,
	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * DIsables the client's keyboard and mouse input.
	 * TODO possibly have it effect volume/the screen
	 * </p>
	 */
	S_DISABLE_COMPUTER,
	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * Informs the client that their question has successfully been added to the
	 * queue of questions.
	 * </p>
	 */
	S_QUESTION_ADDED,
	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * Informs the client that their question has NOT been added to the queue
	 * successfully.
	 * </p>
	 */
	S_QUESTION_NOT_ADDED,
	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * Indicates that this is the first task sent to the client by the server.
	 * It could possibly contain launch parameters or something.
	 * TODO clarify this once implemented.
	 * </p>
	 */
	S_INIT,
	/**
	 * <p>
	 * To be sent from the server to the client.
	 * </p>
	 * <p>
	 * Enables the client after it has been previously disabled.
	 * </p>
	 */
	S_ENABLE_COMPUTER,
	/**
	 * <p>
	 * To be sent from the server to the admin.
	 * </p>
	 * <p>
	 * Indicates that the server's question queue is attached to this task.
	 * </p>
	 */
	S_SENDING_QUESTIONS,
	/**
	 * <p>
	 * To be sent from the server to the admin.
	 * </p>
	 * <p>
	 * TODO what is this for
	 * </p>
	 */
	S_UPDATE_QUESTIONS,
	// -------------------------------------------------------------------------------------

	/**
	 * <p>
	 * To be sent from the admin to the server.
	 * </p>
	 * <p>
	 * Tells the server to remove the first question in the question queue.
	 * </p>
	 */
	A_REMOVE_FIRST_QUESTION,
	/**
	 * <p>
	 * To be sent from the admin to the server.
	 * </p>
	 * <p>
	 * Tells the server to remove all of the questions in the question queue.
	 * </p>
	 */
	A_REMOVE_QUESTIONS,
	/**
	 * <p>
	 * To be sent from the admin to the server.
	 * </p>
	 * <p>
	 * Asks the server if it can send the question list to the admin.
	 * </p>
	 */
	A_GET_QUESTION_lIST,
	/**
	 * <p>
	 * To be sent from the admin to the server.
	 * </p>
	 * <p>
	 * Asks the server if it can send the screenshot to the admin after getting it form the client.
	 * </p>
	 */
	A_REQUEST_SCREENSHOT,

	// -------------------------------------------------------------------------------------------

	/**
	 * <p>
	 * TODO set this up
	 * </p>
	 * <p>
	 *
	 * </p>
	 */
	SYNC,
	/**
	 * <p>
	 * TODO set this up
	 * </p>
	 * <p>
	 *
	 * </p>
	 */
	REQUEST_VALUE

}
