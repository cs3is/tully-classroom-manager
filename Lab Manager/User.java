public class User
{
	private String userName;
	private String firstName;
	private String lastName;
	private int computerNumber;
	private boolean loggedIn;
	private boolean internet;
	private boolean lockedDown;
	private int period;
	private long lastAsked;
	private int spotInLine;
	private long screenShotDelay;
	
	public static final String LOCKED_MESSAGE = "This station is locked =(";
	
	public User(int period, String userName, String firstName, String lastName, 
		int computerNumber, int spotInLine, long screenShotDelay)
	{
		this.userName 			= userName;
		this.firstName 			= firstName;
		this.lastName 			= lastName;
		this.computerNumber 	= computerNumber;
		this.screenShotDelay	= screenShotDelay;
		loggedIn 				= false;
		lastAsked				= -1;
		spotInLine				= -1;
		internet				= false;
		lockedDown				= false;
	}
	
	public void setLastAsked(long lastAsked)
	{	this.lastAsked = lastAsked;	}
	
	public long getLastAsked()
	{	return lastAsked;	}
	
	public void setSpotInLine(int spotInLine)
	{	this.spotInLine = spotInLine;	}
	
	public long getScreenShotDelay()
	{	return screenShotDelay;	}
	
	public void setScreenShotDelay(long screenShotDelay)
	{	this.screenShotDelay = screenShotDelay;	}
	
	public int getSpotInLine()
	{	return spotInLine;	}
	
	public String getUserName()
	{	return userName;	}
	
	public String lastName()
	{	return lastName;	}
	
	public String firstName()
	{	return firstName;	}
	
	public int getComputerNumber()
	{	return computerNumber;	}
	
	public boolean getLoggedIn()
	{	return loggedIn;	}
	
	public int getPeriod()
	{	return period;	}
	
	public void setLogin(boolean loggedIn)
	{	this.loggedIn = loggedIn; }
	
	
	public boolean getInternet()
	{	return internet;	}
	
	public void setInternet(boolean internet)
	{	this.internet = internet;	}
	
	public boolean getLockedDown()
	{	return internet;	}
	
	public void setIntergetLockedDownnet(boolean lockedDown)
	{	this.lockedDown = lockedDown;	}
	
	public String toString()
	{
		String s ="";
		
		s+= firstName+" ," +lastName+"(UserName:"+userName+") (Period:"+period+") (Seat:"+computerNumber+")";
		
		if(loggedIn)
			s+= " (Signed in)";
		else
			s+= " (Signed out)";
			
		return s;
	}
}