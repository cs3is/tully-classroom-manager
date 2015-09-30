import java.io.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class ServerMain
{
	public static int portNumber = 0;

	public static void main(String[] args)
	{
		ServerFrame sf = new ServerFrame();
		/*synchronized(ServerDataManager.getData())
		{
			System.out.println(ServerDataManager.getData());
		}
		try
		{
			System.out.println("Server running");
			ServerSocket serverSocket = new ServerSocket(8001);
			System.out.println("Got a socket");
			new ServerFrame();			
			while(true)
			{
				Socket connectToClient 		= serverSocket.accept();
				
				System.out.println("client joining " + connectToClient.getInetAddress().getHostName());
				
				ObjectInputStream isFromClient = new ObjectInputStream(connectToClient.getInputStream());
				ObjectOutputStream osToClient = new ObjectOutputStream(connectToClient.getOutputStream());
			
				ClientManager cM = new ClientManager(isFromClient,osToClient,connectToClient.getInetAddress().getHostName());
				Thread t = new Thread(cM);
				t.start();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}
}