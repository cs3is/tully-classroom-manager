import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;

public class ClientMain
{
	public static void main(String[] args)
	{
		String ipAddress= "";
		int portNumber=0;
		try
		{
			
			Scanner fromFile 	= new Scanner(new File("Question Settings\\Client Settings.txt"));
			Scanner line 		= new Scanner(fromFile.nextLine()).useDelimiter(",");	
			ipAddress 			= line.next();
			portNumber 			= line.nextInt();
			
			System.out.println("ip" + ipAddress);
			System.out.println("port" + portNumber);
		}
		catch(Exception e)
		{
			System.out.println("Error with Client Information file.");
			System.exit(0);
		}
		int tries = 5;
		
		try
		{
			System.out.println("Attempting to connect to server");
			Socket connectToServer = new Socket(ipAddress,portNumber);
			ObjectOutputStream osToServer =
			new ObjectOutputStream(connectToServer.getOutputStream());
			ObjectInputStream isFromServer = 
				new ObjectInputStream(connectToServer.getInputStream());
			
			new QuestionFrame(osToServer,isFromServer);
			//break;	
		}
		catch(Exception e)
		{
			System.out.println("Attempt failed: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Attempt failed. ");
			System.exit(0);
		}
	}
}