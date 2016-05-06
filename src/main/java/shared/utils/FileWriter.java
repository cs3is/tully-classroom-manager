package shared.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter {

	protected String delimiter;

	protected int itemsPerLine;

	protected StringBuffer toWrite;

	protected Path filePath;

	protected Charset utf8 = StandardCharsets.UTF_8;

	/**
	 * Use this class in order to write anything to a file. Using this class
	 * only will ensure that there are no formatting issues with any of the text
	 * files, as well as reducing the possible errors that could occur in the
	 * program.
	 */
	public FileWriter() {

		setDefaults();

	}

	/**
	 * <p>
	 * Sets both the delimiter and the characters that the file reader will
	 * ignore to the default characters. This should be called upon
	 * initialization and whenever a method is called to read a file without
	 * providing parameters to the class.
	 * </p>
	 */
	protected void setDefaults() {

		delimiter = ",";

		itemsPerLine = 2;

	}

	/**
	 * TODO make this method write down all of the values of the config file.
	 * 
	 * @param filePath
	 * @param delimiter
	 * @param itemsPerLine
	 * @return
	 */
	public boolean writeConfig(String filePath, String delimiter, int itemsPerLine) {
		this.filePath = Paths.get(filePath);
		this.delimiter = delimiter;
		this.itemsPerLine = itemsPerLine;
		return false;
	}

	/**
	 * TODO make this method write down all of the values of the config file.
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean writeConfig(String filePath) {
		this.filePath = Paths.get(filePath);
		setDefaults();
		return false;
	}

	/**
	 * TODO make this method start a string or something that will keep track of
	 * everything that needs to be printed, or something among those lines.
	 * 
	 * @param filePath
	 * @param delimiter
	 * @param itemsPerLine
	 * @return
	 */
	public void startWriting(String filePath, String delimiter, int itemsPerLine) {
		this.filePath = Paths.get(filePath);
		this.delimiter = delimiter;
		this.itemsPerLine = itemsPerLine;

		toWrite = new StringBuffer();
	}

	/**
	 * TODO same as above
	 * 
	 * @param filePath
	 * @return
	 */
	public void startWriting(String filePath) {
		this.filePath = Paths.get(filePath);
		setDefaults();

		toWrite = new StringBuffer();

	}

	/**
	 * TODO make this add a value into whatever startWriting() has created
	 * 
	 * @param s
	 * @return
	 */
	public void insertString(String s) {
		if (toWrite != null)
			toWrite.append(s);
		else
			Log.warn("Tried to print to a closed instance of FileWriter");
	}

	/**
	 * TODO make this add the delimiter to whatever startWriting() has created
	 * 
	 * @return
	 */
	public void insertDelimiter() {
		if (toWrite != null)
			toWrite.append(delimiter);
		else
			Log.warn("Tried to print to a closed instance of FileWriter");
	}

	/**
	 * TODO make this add a /n character into whatever startWriting() has
	 * created.
	 * 
	 * @return
	 */
	public void nextLine() {
		if (toWrite != null)
			toWrite.append("\n");
		else
			Log.warn("Tried to print to a closed instance of FileWriter");
	}

	/**TODO document this
	 * 
	 * @return
	 */
	public boolean updateFile() {
		try {
			Files.write(filePath, toWrite.toString().getBytes());
			return true;
		} catch (IOException e) {
			Log.printStackTrace(e);
			// e.printStackTrace();
		}
		return false;
	}

	/**
	 * TODO end the writing process and finalize everything.
	 * 
	 * @return
	 */
	public boolean endWriting() {
		try {
			Files.write(filePath, toWrite.toString().getBytes());
			cancelWriting();
			return true;
		} catch (IOException e) {
			Log.printStackTrace(e);
			// e.printStackTrace();
		}
		return false;
	}

	/**
	 * TODO make this abort whatever writing process is going on at the moment
	 * 
	 * @return
	 */
	public void cancelWriting() {
		toWrite = null;
	}

}
