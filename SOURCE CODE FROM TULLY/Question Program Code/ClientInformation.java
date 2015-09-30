import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The ClientInformation class stores inforamtion clients and
 * their information.
 */
public class ClientInformation
{
	private String refName;								// Identifying information for the PC (ex. Station Number)
	private String computerName;						// Name of the connecting computer
	private String IPAddress;							// IPAddress of the Client
	private boolean connected;							// True when the client is connected
	private ObjectOutputStream osToClient 	= null;		// Output Stream to the Client
	private ObjectInputStream isFromClient 	= null;		// Intput Stream to the Client
	private long lastCheckIn				=	0;		// Stores the amount of time it has been sence the user checked in
	private long DISCONNECTED				=	-1;		// Represents that the computer is not connected to the server
	private boolean canAsk;			// True when the computer may ask a question
	

	private boolean canRemove;			
	private long waitTime 						= 0;		// Number of seconds the user must wait before he/she can ask a question
	private static long clientRemoveWaitTime 	= 55;
	private static long serverRemoveWaitTime 	= 295;
	public static final int NO_WAIT				= 0;
	public static final int SERVER_WAIT			= 1;
	public static final int CLIENT_WAIT			= 2;
	
	/*
	 *
	 * Creates a new ClientInformation with the sent Computer Name
	 * and ip address. Sets the input/ output streams to zero and marks
	 * the client as not connected.
	 */
	ClientInformation(String computerName,String refName)
	{
		this.computerName = computerName;
		this.refName = refName;
		connected = false;
		canAsk =true;
		canRemove = false;
	}	
	
	/*
	 * Returns whether the client is connected or not.
	 * true 	- connected
	 * false	- not connected
	 */
	public boolean getConnected()
	{
		return connected;	
	}
	
	/*
	 * Returns the client's Computer Name.
	 */
	public String getComputerName()
	{
		return computerName;
	}
	
	/*
	 * Returns the client's ip address as a String.
	 * The String starts with a forward slash (/) followed by the ip address
	 */
	public String getIPAddress()
	{
		return IPAddress;
	}
	
	/*
	 * Returns the client's refName.
	 */
	public String getRefName()
	{
		return refName;
	}
	
	/*
	 * Returns whether the sent object is equal the current object.
	 * The sent object must be of type ClientInformation.
	 * true 	- they are equal
	 * false	- false they are not equal
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof ClientInformation)
		{
			ClientInformation cI = (ClientInformation) obj;
			return computerName.equals(cI.getComputerName()) && IPAddress.equals(cI.getIPAddress()) && refName.equals(cI.getRefName());
		}
		return false;
	}
	
	/*
	 * connect - sets up the client's connects
	 * -Marks the client as connected.
	 * -Sets the input/output stream to those that were recieved.
	 */
	public void connect( ObjectInputStream isFromClient,ObjectOutputStream osToClient)
	{
		this.osToClient = osToClient;
		this.isFromClient = isFromClient;
		connected = true;
	}
	
	/*
	 * Disconnects the client. This disconnect sets the input/output
	 * streams to null and marks the client as not connected.
	 */
	public void disconnect()
	{
		this.osToClient = null;
		this.isFromClient = null;
		connected = false;	
	}	
	
	//good
	public ObjectInputStream getInputStream()
	{
		return isFromClient;
	}
	
	//good
	public ObjectOutputStream getOutputStream()
	{
		return osToClient;	
	}
	
	/* changes the refName of the client to the 
	 * recieved refName
	 */
	public void getIPAddress(String refName)
	{
		this.refName = refName;
	}
	
	public void setWaitTime(long waitTime)
	{
		this.waitTime = waitTime;
	}
	
	public long getWaitTime()
	{
		return waitTime;
	}
	
	public void setCanAsk(boolean canAsk, int waitType)
	{
		if(waitType== NO_WAIT)
		{
			this.canAsk =canAsk;
		}
		else if(waitType == CLIENT_WAIT)
		{
			Thread t = new Thread(new CountDown(this,true,clientRemoveWaitTime));
			t.start();
		}
		else
		{			
			Thread t = new Thread(new CountDown(this,true,serverRemoveWaitTime));
			t.start();
		}
	
	}
	
	public boolean getCanAsk()
	{
		return canAsk;
	}
	
	public void setCanRemove(boolean canRemove)
	{
		this.canRemove = canRemove;
	}
	
	public boolean getCanRemove()
	{
		return canRemove;
	}
	
	public static void setClientRemoveWaitTime(long waitTime)
	{
		clientRemoveWaitTime = waitTime;
	}
	
	public static void setServerRemoveWaitTime(long waitTime)
	{
		serverRemoveWaitTime = waitTime;
	}
	
	public void updateClient()
	{
		try
		{
			if(osToClient!=null)
			{
				if(canAsk)
				{
					osToClient.writeObject(new Command(Command.ACTIVATE_QUESTION));
				}
				else
				{
					osToClient.writeObject(new Command(Command.DEACTIVATE_QUESTION));
				}
				if(canRemove)
				{
					osToClient.writeObject(new Command(Command.ACTIVATE_REMOVE_QUESTION));
				}
				else
				{
					osToClient.writeObject(new Command(Command.DEACTIVATE_REMOVE_QUESTION));
				}
				
				if(waitTime>0)
				{
					String wait;
					if(waitTime%60>=10)
						wait = "" + waitTime/60 + ":" + waitTime%60;
					else
						wait = "" + waitTime/60 + ":0" + waitTime%60;
					osToClient.writeObject(new Command(Command.WAIT_TIME,wait));
				}
				else
				{
					osToClient.writeObject(new Command(Command.SPOT_IN_LINE,QuestionList.getSpotInLine(refName)));
				}
    		}
				
		}
		catch(Exception ex)
		{
			System.out.println("Commands to client fail.(In update client)");
			connected=false;
		}
	}
}