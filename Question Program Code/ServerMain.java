import java.io.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class ServerMain
{
	public static int portNumber = 0;

	public static void main(String[] args)
	{

		loadClients();
		ServerFrame sF = new ServerFrame();
		QuestionList.setScreen(sF);
		try
		{
			System.out.println("Server running");

			ServerSocket serverSocket = new ServerSocket(QuestionList.getPortNumber());

			System.out.println("Got a socket");
			while(true)
			{
				Socket connectToClient 		= serverSocket.accept();
				
				System.out.println("client joining " + connectToClient.getInetAddress().getHostName());
				ObjectInputStream isFromClient =
						new ObjectInputStream(connectToClient.getInputStream());
				ObjectOutputStream osToClient =
					new ObjectOutputStream(connectToClient.getOutputStream());
				ClientInformation cI = QuestionList.getClientByComputerName((connectToClient.getInetAddress()).getHostName());
				if(cI!= null)
				{
					cI.connect(isFromClient,osToClient);
					System.out.println(cI.getComputerName()+" connected as " + cI.getRefName());
					ClientManager cM = new ClientManager(cI);
					Thread t = new Thread(cM);
					t.start();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(">"+e.getMessage());
		}
	}

	public static void loadClients()
	{
		Scanner lineScanner;

		try
		{
			Scanner fromFile = new Scanner(new File("Question Settings\\Server Settings.txt"));
			String s = fromFile.nextLine();


			lineScanner = new Scanner(s).useDelimiter("[#]");
			portNumber = lineScanner.nextInt();
			QuestionList.setPortNumber(portNumber);

			s = fromFile.nextLine();
			lineScanner = new Scanner(s).useDelimiter("[#]");
			long delayTimeClient = lineScanner.nextLong();
			ClientInformation.setClientRemoveWaitTime(delayTimeClient);


			s = fromFile.nextLine();
			lineScanner = new Scanner(s).useDelimiter("[#]");
			long delayTimeServer = lineScanner.nextLong();
			ClientInformation.setServerRemoveWaitTime(delayTimeServer);

			s = fromFile.nextLine();
			lineScanner = new Scanner(s).useDelimiter("[#]");
			String soundLocation = lineScanner.next();
			QuestionList.setQuestionSound(soundLocation);

			while(fromFile.hasNextLine())
			{
				s = fromFile.nextLine();
				lineScanner = new Scanner(s).useDelimiter("[,#]");
				String refName = lineScanner.next();
				String computerName = lineScanner.next();
				System.out.println(computerName + " known as " +refName);
				QuestionList.getClients().add(new ClientInformation(computerName,refName));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error with Server Information file.");
			System.exit(0);
		}
	}


}