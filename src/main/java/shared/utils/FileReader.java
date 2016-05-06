package shared.utils;

import java.util.Scanner;
import java.util.regex.Pattern;

public class FileReader {
	//TODO finish this class and make it actually read files.

	/**
	 * The delimiter is used in order to separate text based on the characters
	 * it contains when reading through a document. See the documentation for
	 * charsToIgnore in order to see a full list of commands if multiple
	 * delimiters are needed.
	 */
	protected Pattern delimiter;

	/**
	 * After encountering any of the characters in this patter, the file reader
	 * will then stop reading the current line and proceed onto the next one.
	 * The use of this function is useful if there are any comments in the file
	 * that is being read from, or if there is anything else that should be
	 * ignored.
	 * 
	 * See the below documentation for information on how to use all of the
	 * commands in the pattern.
	 * 
	 * @formatter:off
	 * <p>
	 * abc…				Letters
	 * 123… 			Digits
	 * \d 				Any Digit
	 * \D 				Any Non-digit character
	 * . 				Any Character
	 * \. 				Period
	 * [abc] 			Only a, b, or c
	 * [^abc] 			Not a, b, nor c
	 * [a-z] 			Characters a to z
	 * [0-9] 			Numbers 0 to 9
	 * \w 				Any Alphanumeric character
	 * \W 				Any Non-alphanumeric character
	 * {m} 				m Repetitions
	 * {m,n} 			m to n Repetitions
	 * * 				Zero or more repetitions
	 * + 				One or more repetitions
	 * ? 				Optional character
	 * \s 				Any Whitespace
	 * \S 				Any Non-whitespace character
	 * ^…$ 				Starts and ends
	 * (…) 				Capture Group
	 * (a(bc)) 			Capture Sub-group
	 * (.*) 			Capture all
	 * (ab|cd) 			Matches ab or cd
	 * </p>
	 * @formatter:on
	 */
	protected Pattern charsToIgnore;

	/**
	 * Use this class in order to read anything from any sort of files that may
	 * need to be read. Only using this file will ensure that the proper format
	 * is used, and that there are fewer potential errors.
	 * 
	 * The default delimiter that will be used by this class is a ",", and the
	 * default characters that the file reader will ignore are "*", as well as
	 * "/".
	 */
	public FileReader() {

		setDefaults();

	}

	/**
	 * Sets both the delimiter and the characters that the file reader will
	 * ignore to the default characters. This should be called upon
	 * initialization and whenever a method is called to read a file without
	 * providing parameters to the class.
	 */
	protected void setDefaults() {

		delimiter = Pattern.compile(",");

		charsToIgnore = Pattern.compile("\\W*\\W/");

	}
	
	
	

}
