import java.io.*;

public class Command implements Serializable
{
	private int command 								=0;				// type of command
	private Object commandData 							= null;			// data needed to perform a command
	public static final int DISCONNECT 					= 0;			// requests a disconnect by server or client		
	public static final int ADD_QUESTION 				= 1;			// add user to question list
	public static final int REMOVE_QUESTION 			= 2;			// remove user from question list
	public static final int ACTIVATE_QUESTION			= 3;			// turn on question button
	public static final int DEACTIVATE_QUESTION			= 4;			// turn off question button
	public static final int ACTIVATE_REMOVE_QUESTION	= 5;			// turn on question button
	public static final int DEACTIVATE_REMOVE_QUESTION	= 6;			// turn off question button
	public static final int SPOT_IN_LINE				= 7;			// sends the new spot in line for the user
	public static final int WAIT_TIME					= 8;			// sends the time left until user can ask another question
	public static final int FLUSH						= 9;			// Clears the commands to the clinet
	
	Command(int command)
	{
		this.command = command;	
		this.commandData = null;
	}
	
	Command(int command, Object commandData)
	{
		this.command = command;	
		this.commandData = commandData;
	}
	
	public int getCommand()
	{
		return command;	
	}
	
	public Object getCommandData()
	{
		return commandData;
	}
}