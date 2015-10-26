package graphics;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import utils.ClientConfig;
import utils.ClientConfigManager;
import utils.Log;
import utils.ServerLog;

public class ClientTray implements Runnable {
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private ClientConfigManager cfg = null;

	private ArrayList<MenuItem> components = new ArrayList<MenuItem>();
	private String userName = "";
	/**
	 * need to change to use server/database sometime
	 */
	private boolean canQuestion = true;
	private long time = 0;
	private long prevLabel = 0;
	long qTime = 0;
	
	private MenuItem addQuestion = null;
	private MenuItem exit = null;

	private PopupMenu popup = null;
	TrayIcon trayIcon = null;

	final SystemTray tray = SystemTray.getSystemTray();

	public ClientTray(ObjectOutputStream out, ObjectInputStream in) {
		// init objectstreams
		this.out = out;
		this.in = in;

		// get username
		userName = System.getProperty("user.name");
		System.out.println("The user name is: " + userName.trim());
		try {
			out.writeObject(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		init();

	}

	public void init() {
		cfg = new ClientConfigManager();
		createMenu();
		createMenuComponents();
		trayIcon.setPopupMenu(popup);
		addMenuComponents();
		addActionListeners();

		Thread t = new Thread(this);
		t.start();

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}

		ServerLog.info("ClientTray initialized");
	}

	public void createMenu() {
		popup = new PopupMenu();
		
		//tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().getImage("bin/../src/main/../main/resources/../java/client/../../resources/client.jpg");
		trayIcon = new TrayIcon(image, ClientConfig.NAME);
		
		ServerLog.info(trayIcon.getImage() + "");

	}

	public void createMenuComponents() {
		addQuestion = new MenuItem("Add Question");
		exit = new MenuItem("Exit");
		components.add(addQuestion);
		components.add(exit);
	}

	public void addMenuComponents() {
		for(int i = 0;i<components.size();i++){
			popup.add(components.get(i));
		}
		trayIcon.setPopupMenu(popup); 
	}

	public void addActionListeners() {

		addQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canQuestion = false;
				qTime = time;
				addQuestion.setEnabled(false);
				// TODO AddQuestion.add(); or something
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public void updateMenu(){
		if(!canQuestion) updateQuestion();
	}
	
	public void updateQuestion(){
		long diff = time-qTime;
		long label = (((120*1000000000L)-diff)/1000000000L);
		//Log.info(time+"");
		//Log.info(qTime+"");
		if(diff>120*1000000000L) {
			addQuestion.setEnabled(true);
			canQuestion = true;
			addQuestion.setLabel("Add Question");
		}
		if(label != prevLabel)
		addQuestion.setLabel("Add Question ("+label+")");
		prevLabel = label;
	}
	
	@Override
	public void run() {
		
		while (true) {
			time = System.nanoTime();
			updateMenu();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

/*@formatter:off

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

*/