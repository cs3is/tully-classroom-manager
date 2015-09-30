import java.io.*;
import java.net.*;
import java.util.*;

public class ClientManager implements Runnable
{
	ClientInformation cI = null;
	
	ClientManager(ClientInformation cI)
	{	
		this.cI=cI;
		cI.updateClient();
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				Command command = (Command)cI.getInputStream().readObject();
			
				if(command.getCommand() == Command.DISCONNECT)
				{
					cI.disconnect();
					System.out.println(cI.getIPAddress() + " disconnected.");
					break;
				}
				else if(command.getCommand() == Command.ADD_QUESTION
					&& cI.getCanAsk())
				{
					
					QuestionList.addQuestion(cI.getRefName());
					cI.setCanAsk(false,ClientInformation.NO_WAIT);
					cI.updateClient();
				}
				else if(command.getCommand() == Command.FLUSH)
				{
					cI.getOutputStream().flush();
				}
				else if(command.getCommand() == Command.REMOVE_QUESTION)
				{
						
					if(QuestionList.getFirstQuestion().equals(cI.getRefName()) != true
						&& cI.getCanRemove()
						&& QuestionList.clientRemoveQuestion(cI.getRefName())!= null)
					{
						cI.setCanRemove(false);
						cI.updateClient();
						cI.setCanAsk(true,ClientInformation.CLIENT_WAIT);
						
					}
				}

			}
		}
		catch(Exception e)
		{
			System.out.println(cI.getRefName() + " disconnected.");
		}
	}
}