public class Computer
{
	private User user;
	private String refName;
	private int computerNumber;
	private boolean connected;
	
	
	public Computer(String refName, int computerNumber)
	{
		this.refName 			= refName;
		this.computerNumber 	= computerNumber;
		connected				= false;
		user 					= null;
	}
	
	public boolean getConnected()
	{	return connected;	}
	
	public String getRefName()
	{	return refName;	}
	
	public int getComputerNumber()
	{	return computerNumber;	}
	
	public User getUser()
	{	return user;	}
	
	public void setUser(User user)
	{	this.user = user;	}	
		
	public void connect()
	{	connected = true;	}
	
	public void disconnect()
	{	connected = false;	}
	
	public String toString()
	{
		String s ="";
		
		s+= "(Comptuer Number: " + computerNumber + ") (RefenceName: "+refName+")";
		
		return s;
	}
}