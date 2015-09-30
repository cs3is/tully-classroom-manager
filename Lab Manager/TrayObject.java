import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import java.util.*;

public class TrayObject implements Runnable
{
	private ObjectOutputStream osToServer 	= null;
	private ObjectInputStream isFromServer 	= null;
	private ClientData data 				= null;
	
	private String userName					= "";
	
	private MenuItem addQuestion 			= null;
	private PopupMenu popup					= null;
	TrayIcon trayIcon 						= null;
	
	public TrayObject(ObjectOutputStream osToServer, ObjectInputStream isFromServer)
	{
		this.osToServer = osToServer;
		this.isFromServer = isFromServer;
		
		PopupMenu popup = new PopupMenu();
        trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("data/LabClient.gif"));
        final SystemTray tray = SystemTray.getSystemTray();
         
        // Create a popup menu components
        addQuestion = new MenuItem("Add Question");
         
        userName = System.getProperty("user.name");
        System.out.println("The user name is: " +userName.trim());
        
        data = new ClientData();
        
        //Add components to popup menu
        popup.add(addQuestion);
                
        trayIcon.setPopupMenu(popup);
        
        
        try
        {
        	osToServer.writeObject(new Command(Command.LOGIN, userName));
        	
        	Command c = (Command)isFromServer.readObject();
        	
        	if(c.getCommand() == Command.LOGIN_SUCCESSFUL)
        	{
        		//sends the spot int line, lastAsked -1 if they Never, screen shot delay, 
					// boolean internet, boolean lockdown
        		ArrayList<Object> receivedData = (ArrayList<Object>)(c.getCommandData());
           		tray.add(trayIcon);
        		Thread t = new Thread(this);
        		t.start();
        	}
        	else
        	{
        		if(c.getCommand() == Command.DUPLICATE_USER)
        			JOptionPane.showMessageDialog(null,"You are already logged in at another station.");
        		else if(c.getCommand() == Command.DUPLICATE_COMPUTER)
        			JOptionPane.showMessageDialog(null,"This computer is already logged in.");
        		else if(c.getCommand() == Command.UNKNOWN_USER)
        			JOptionPane.showMessageDialog(null,"Invalid user account");
        		else if(c.getCommand() == Command.UNKNOWN_COMPUTER)
        			JOptionPane.showMessageDialog(null,"This computer is not registered.");
        		else if(c.getCommand() == Command.INCORRECT_SEAT)
        			JOptionPane.showMessageDialog(null,"This is not your assigned seat.");
        		System.exit(0);
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.exit(0);
        }
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(200);
				synchronized(data)
				{
					data.update();
					//trayIcon.displayMessage("Lab Manager","Test"+(int)(Math.random()*10),TrayIcon.MessageType.INFO);
					addQuestion.setLabel("Test"+(int)(Math.random()*10));
						
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}