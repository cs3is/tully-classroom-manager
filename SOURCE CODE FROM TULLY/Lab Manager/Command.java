import java.io.*;

public class Command implements Serializable
{
	private int command 								=0;				// type of cammand
	private Object commandData 							= null;			// data needed to preform a command
	
	public static final int LOGIN 						= 0;
	
	// LOGIN RESPONCES
	public static final int LOGIN_SUCCESSFUL 			= 1;
			//sends the lastAsked -1 if they Never, screen shot delay, boolean internet, boolean lockdown, boolean control
			
	public static final int DUPLICATE_USER				= 2;	// s
	public static final int DUPLICATE_COMPUTER			= 3;	// s
	public static final int UNKNOWN_USER				= 4;	// s
	public static final int UNKNOWN_COMPUTER			= 5;	// s
	public static final int INCORRECT_SEAT				= 6;
	
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