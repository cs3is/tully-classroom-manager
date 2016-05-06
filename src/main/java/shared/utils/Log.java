package shared.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Log {

	protected static FileWriter f;

	/*
	 * add any text that should be printed into the console upon startup to this
	 * block.
	 */
	static {
		f = new FileWriter();
		f.startWriting("log.txt");

		// TODO alter this
		info("Example Code created by Andrew");

	}

	/**
	 * TODO add an if statement here in order to hide debugging info if it is
	 * disabled.
	 * <p>
	 * Use this method whenever there is data that should be printed when
	 * problem solving/looking for issues in the program, but that won't be
	 * needed on a regular basis when using the program.
	 * </p>
	 * 
	 * @param toPrint
	 *            The String to be printed into the console.
	 */
	public static void debug(String toPrint) {
		log(LogLevel.debug, toPrint);
	}

	/**
	 * <p>
	 * Printed in the console in red in order to ensure visibility.
	 * </p>
	 * <p>
	 * Use this method in order to notify the user whenever there is something
	 * that goes wrong with the program, and has the potential to cause severe
	 * issues.
	 * </p>
	 * 
	 * @param toPrint
	 */
	public static void error(String toPrint) {
		log(LogLevel.error, toPrint);
	}

	/**
	 * <p>
	 * Use this method whenever printing off information for the user to see.
	 * </p>
	 * 
	 * @param toPrint
	 */
	public static void info(String toPrint) {
		log(LogLevel.info, toPrint);
	}

	/**
	 * <p>
	 * Printed in the console in red in order to ensure visibility.
	 * </p>
	 * <p>
	 * Use this method whenever something is slightly wrong with the
	 * program/something the user is doing, and should be fixed soon, although
	 * likely won't cause any serious issues for a while.
	 * </p>
	 * 
	 * @param toPrint
	 */
	public static void warn(String toPrint) {
		log(LogLevel.warn, toPrint);
	}

	/**
	 * <p>
	 * A majority of content printed into the console should be of this type.
	 * </p>
	 * <p>
	 * Use this method whenever the reason for printing does not specifically
	 * fall into one of the other categories.
	 * </p>
	 * 
	 * @param toPrint
	 */
	public static void log(String toPrint) {
		log(LogLevel.log, toPrint);
	}

	/**
	 * </p>
	 * Print all occurrences of <code>java.lang.StackTraceElement<code> through
	 * this method. This will allow any desired manipulation by the user to be
	 * possible.
	 * </p>
	 * 
	 * @param throwable
	 */
	public static void printStackTrace(Throwable throwable) {
		debug("printing stackTrace");
		log(LogLevel.stackTrace, getStackTrace(throwable));
	}

	/**
	 * TODO print out the program name before the type of error message
	 * <p>
	 * **This method should only be invoked by its helper methods.**
	 * </p>
	 * <p>
	 * It is utilized
	 * to print information into the console with a specific tag before the
	 * message to allow for easier reading by the user.
	 * </p>
	 * 
	 * @param logLevel
	 *            The type of message that should be printed. It should be
	 *            related to the reason why it is being printed.
	 * @param toPrint
	 *            Whatever the string is that needs to be printed out.
	 */
	protected static void log(LogLevel logLevel, String toPrint) {
		switch (logLevel) {
		case debug:
			print("[DEBUG] ");
			break;
		case error:
			err("[ERROR] ");
			break;
		case info:
			print("[INFO] ");
			break;
		case warn:
			err("[WARNING] ");
			break;
		case log:
			print("[LOG] ");
			break;
		case stackTrace:
			break;
		default:
			errln("The protected method \"log\" has been invoked using a logLevel that is undefined in the the switch loop.");
			break;
		}

		if (logLevel == LogLevel.stackTrace)
			errln(toPrint);
		else
			println(toPrint);

	}

	/**
	 * <p>
	 * Gets the stack trace from a Throwable as a String.
	 * </p>
	 *
	 * <p>
	 * The result of this method vary by JDK version as this method
	 * uses {@link Throwable#printStackTrace(java.io.PrintWriter)}.
	 * On JDK1.3 and earlier, the cause exception will not be shown
	 * unless the specified throwable alters printStackTrace.
	 * </p>
	 *
	 * @param throwable
	 *            the <code>Throwable</code> to be examined
	 * @return the stack trace as generated by the exception's
	 *         <code>printStackTrace(PrintWriter)</code> method
	 */
	protected static String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

	/**
	 * <p>
	 * **should only ever be invoked by the <code>Log.log</code> method**
	 * </p>
	 * <p>
	 * A method to print things into the console while adding a new line to the
	 * end of whatever is printed.
	 * </p>
	 * <p>
	 * This should always be called
	 * instead of the defaults system.out method in order to be able to change
	 * whatever happens to the information that needs to be printed without
	 * having to make significant changes to the program.
	 * </p>
	 * 
	 * @param toPrint
	 *            The string that needs to be printed to the console.
	 */
	protected static void println(String toPrint) {
		System.out.println(toPrint);
		f.insertString(toPrint);
		f.nextLine();
	}

	/**
	 * <p>
	 * **should only ever be invoked by the <code>Log.log()</code> method**
	 * </p>
	 * <p>
	 * A method to print things into the console.
	 * </p>
	 * <p>
	 * This should always be called instead of the defaults
	 * <code>System.out.print()</code> method in
	 * order to be able to change whatever happens to the information that needs
	 * to be printed without having to make significant changes to the program.
	 * </p>
	 * 
	 * @param toPrint
	 *            The string that needs to be printed to the console.
	 */
	protected static void print(String toPrint) {
		System.out.print(toPrint);
		f.insertString(toPrint);
	}

	/**
	 * <p>
	 * **should only ever be invoked by the <code>Log.log()</code> method**
	 * </p>
	 * <p>
	 * *Will print with more visibility than the other methods*
	 * </p>
	 * <p>
	 * A method to print things into the console. This should always be called
	 * instead of the defaults <code>System.err.print()</code> method in order
	 * to be able to change whatever happens to the information that needs to be
	 * printed without having to make significant changes to the program.
	 * </p>
	 * 
	 * @param toPrint
	 *            The string that needs to be printed to the console.
	 */
	protected static void err(String toPrint) {
		System.err.print(toPrint);
		f.insertString(toPrint);
	}

	/**
	 * <p>
	 * **should only ever be invoked by the <code>Log.log()</code> method**
	 * </p>
	 * <p>
	 * *Will print with more visibility than the other methods*
	 * </p>
	 * <p>
	 * A method to print things into the console while adding a new line to the
	 * end of whatever is printed. This should always be called
	 * instead of the defaults <code>System.err.println()</code> method in order
	 * to be able to change
	 * whatever happens to the information that needs to be printed without
	 * having to make significant changes to the program.
	 * </p>
	 * 
	 * @param toPrint
	 *            The string that needs to be printed to the console.
	 */
	protected static void errln(String toPrint) {
		System.err.println(toPrint);
		f.insertString(toPrint);
		f.nextLine();
	}

	/**
	 * TODO document this
	 */
	public static void onShutdown() {
		info("shutting down");
		f.endWriting();
	}

}
