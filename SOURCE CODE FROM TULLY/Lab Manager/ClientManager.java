import java.io.*;
import java.net.*;
import java.util.*;

public class ClientManager implements Runnable
{
	private ServerData data = null;
	private ObjectInputStream isFromClient;
	private ObjectOutputStream osToClient;
	private String hostName;
	private int computerNumber;
	private User user = null;
	
	ClientManager(ObjectInputStream isFromClient, ObjectOutputStream osToClient, String hostName)
	{	
		this.hostName		= hostName;
		this.isFromClient 	= isFromClient;
		this.osToClient		= osToClient;
		data 				= ServerDataManager.getData();
		
	}
	
	public void run()
	{
		try
		{
			synchronized(data)
			{
				computerNumber = data.getComputerNumber(hostName);
				if(computerNumber == -1)
				{
					osToClient.writeObject(new Command(Command.UNKNOWN_COMPUTER));
					return;
				}
				else
				{
					boolean computerInUse = data.computerInUse(computerNumber);
					if(computerInUse)
					{
						osToClient.writeObject(new Command(Command.DUPLICATE_COMPUTER));
						return;
					}
					else
					{
						data.connectComputer(computerNumber);
					}
				}
			}
		
			while(true)
			{
				
				Command command = (Command)isFromClient.readObject();
				synchronized(data)
				{
					if(command.getCommand() == Command.LOGIN)
					{
						String name = (String)command.getCommandData();
						System.out.print(name + " is attempting to login (");
						user = data.getUser(name);
						
						if(user==null)
						{
							osToClient.writeObject(new Command(Command.UNKNOWN_USER));
							data.disconnectComputer(computerNumber);
							System.out.println("1)");
							return;
						}
						else if(user.getLoggedIn()==false)
						{
							System.out.println("students comp number"+user.getComputerNumber());
							System.out.println("comp number"+computerNumber);
							if(user.getComputerNumber() != computerNumber)
							{
								System.out.println("2)");
								osToClient.writeObject(new Command(Command.INCORRECT_SEAT));
								data.disconnectComputer(computerNumber);
							}
							else
							{
								user.setLogin(true);
								
								//sends the spot int line, lastAsked -1 if they Never, screen shot delay, 
								// boolean internet, boolean lockdown
								ArrayList<Object> sendData = new ArrayList<Object>();
								sendData.add(user.getSpotInLine());
								sendData.add(user.getLastAsked());
								sendData.add(user.getScreenShotDelay());
								sendData.add(user.getInternet());
								sendData.add(user.getLockedDown());
							
								osToClient.writeObject(new Command(Command.LOGIN_SUCCESSFUL));
							}
						}
						else
						{
							System.out.println("3)");
							osToClient.writeObject(new Command(Command.DUPLICATE_USER));
							data.disconnectComputer(computerNumber);
							return;
						}
	
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}