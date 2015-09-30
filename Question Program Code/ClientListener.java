import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
public class ClientListener implements Runnable
{
	ObjectInputStream inStream; 
	QuestionFrame qF = null;
	
	ClientListener(ObjectInputStream inStream, QuestionFrame qF)
	{
		this.qF = qF;
		this.inStream=inStream;
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				Object o = inStream.readObject();
				
				if(o instanceof Command)
				{
					Command c = (Command)o;
					if(c.getCommand() == Command.ACTIVATE_QUESTION)
						qF.enableQuestion(true);
					else if(c.getCommand() == Command.DEACTIVATE_QUESTION)
						qF.enableQuestion(false);
					if(c.getCommand() == Command.ACTIVATE_REMOVE_QUESTION)
					{
						qF.enableRemoveQuestion(true);
					}
					else if(c.getCommand() == Command.DEACTIVATE_REMOVE_QUESTION)
						qF.enableRemoveQuestion(false);
					else if(c.getCommand() == Command.DISCONNECT)
						qF.serverExit();
					else if(c.getCommand() == Command.SPOT_IN_LINE)
					{
						String text = (String)c.getCommandData();
						qF.setPlaceInLine(text);
					}
					else if(c.getCommand() == Command.WAIT_TIME)
					{
						String text = (String)c.getCommandData();
						qF.setWaitTime(text);
					}
					
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Lost connection to the server.");
			System.out.println("Closing down ...");
			System.exit(0);
		}	
	}
}