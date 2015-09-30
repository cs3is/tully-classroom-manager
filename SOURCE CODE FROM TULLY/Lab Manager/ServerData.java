import java.util.*;
import java.applet.*;
import java.net.URL;
import java.util.*;
import java.io.*;


public class ServerData
{
	private ArrayList<User> users;
	private ArrayList<Computer> computers = new ArrayList<Computer>();
	private AudioClip questionSound = null;
	private long serverRemoveTimer;
	private long clientRemoveTimer;
	private long screenShotDelay;
	
	
	public ServerData()
	{
		users = new ArrayList<User>();
		loadSettings();
		loadData();
	}
	
	public long getScreenShotDelay()
	{	return screenShotDelay;	}
	
	public void setScreenShotDelay(long screenShotDelay)
	{	this.screenShotDelay = screenShotDelay;	}
	
	public User getUser(String userName)
	{
		for(User u:users)
		{
			System.out.println("\tChecking-"+u.getUserName());
			if(u.getUserName().equals(userName))
				return u;
		}
		return null;
	}
	
	public boolean computerInUse(int num)
	{
		Computer c = getComputer(num);
		
		if(c==null || c.getConnected()==false)
			return false;
		else
			return true;
	}
	
	public Computer getComputer(int num)
	{
		for(Computer c:computers)
			if(c.getComputerNumber()==num )
				return c;
		return null;
	}
	
	public void connectComputer(int num)
	{
		Computer c = getComputer(num);
		
		if(c!=null)
			c.connect();
	}
	
	public void disconnectComputer(int num)
	{
		Computer c = getComputer(num);
		
		if(c!=null)
			c.connect();
	}
	
	public int getComputerNumber(String hostName)
	{
		for(Computer c:computers)
			if(c.getRefName().equals(hostName))
				return c.getComputerNumber();
		return -1;
	}
	
	public void loadData()
	{
		try
		{
			Scanner fromFile = new Scanner(new File("Data\\Users.txt"));
			
			fromFile.nextLine();
			
			while(fromFile.hasNextLine())
			{
				Scanner fromText 	= new Scanner(fromFile.nextLine()).useDelimiter(",");
				int period 			= fromText.nextInt();
				int computerNumber	= fromText.nextInt();
				String firstName 	= fromText.next().trim();
				String lastName 	= fromText.next().trim();
				String userName 	= fromText.next().trim();

				users.add(new User(period,userName,firstName,lastName,computerNumber,-1,screenShotDelay));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadSettings()
	{
		Scanner lineScanner;

		try
		{
			Scanner fromFile = new Scanner(new File("Data\\Server Settings.txt"));
			

			String s = fromFile.nextLine();
			lineScanner = new Scanner(s);
			serverRemoveTimer = lineScanner.nextLong();
			clientRemoveTimer = 150;
			
			s = fromFile.nextLine();
			lineScanner = new Scanner(s);
			String soundLocation = lineScanner.next();
			try
			{
				URL fileLocation = new URL("file:" + soundLocation);
				questionSound = Applet.newAudioClip(fileLocation);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			while(fromFile.hasNextLine())
			{
				s = fromFile.nextLine();
				lineScanner = new Scanner(s).useDelimiter("[,]");
				int computerNumber = lineScanner.nextInt();
				String refName = lineScanner.next();
				computers.add(new Computer(refName,computerNumber));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error with Server Information file.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private long getServerRemoveTimer()
	{	return serverRemoveTimer;	}
	
	private long getClientRemoveTimer()
	{	return clientRemoveTimer;	}
	
	public String toString()
	{
		String s = "Waiting Time: "+serverRemoveTimer+"\n\n";
		s = "Question Sound: "+questionSound+"\n\n";
		
		s = "Users:\n";
		for(User u:users)
			s+=u+"\n";
			
		s += "\nComptuers:\n";
		for(Computer c:computers)
			s+=c+"\n";
			
		return s;
	}
	
}