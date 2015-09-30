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
		ipAddress 			= "127.0.0.1";
		portNumber 			= 8001;
			
		try
		{
			System.out.println("Attempting to connect to server");
			
			Socket connectToServer = new Socket(ipAddress,portNumber);
			
			ObjectOutputStream osToServer = new ObjectOutputStream(connectToServer.getOutputStream());
			ObjectInputStream isFromServer = new ObjectInputStream(connectToServer.getInputStream());
			
			TrayObject to = new TrayObject(osToServer,isFromServer);
			
			ClientListener cl = new ClientListener(isFromServer,to);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}