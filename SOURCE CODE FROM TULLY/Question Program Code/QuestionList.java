import java.util.*;
import java.applet.*;
import java.net.URL;
import javax.swing.*;

public class QuestionList
{
	private static ArrayList<ClientInformation> clients = new ArrayList<ClientInformation>();
	private static ArrayList<String> questions = new ArrayList<String>();
	private static AudioClip questionSound = null;
	private static int portNumber = 0;
	private static JFrame screen = null;
	
	public static void setScreen(JFrame newScreen)
	{
		screen = newScreen;
	}
	
	public static String getFirstQuestion()
	{
		if(questions.size() == 0)
			return null;
		else
			return questions.get(0);
	}
	
	public static void setQuestionSound(String soundLocation)
	{
			try
			{
				URL fileLocation = new URL("file:" + soundLocation);
				questionSound = Applet.newAudioClip(fileLocation);
			}
			catch(Exception ex)
			{
				System.out.println("Can not find: " + soundLocation);	
			}	
	}
	
	public static void playQuestionSound()
	{
		if(questionSound != null)
		{
			questionSound.play();
		}
				
	}
	
	public static String getSpotInLine(String refName)
	{
		if(questions.size() == 0)
			return "";
		else
		{
			for(int i=0; i<questions.size();i++)
			{
				if(questions.get(i).equals(refName))
				{
					return ""+(i+1);
				}
			}
			return "";
		}
	}
	
	public static String serverRemoveFirstQuestion()
	{
		if(questions.size() == 0)
			return null;
		else
		{
			String s = (String)questions.get(0);
			
			if(questions.remove(s))
			{
				ClientInformation cI = getClientByRefName(s);
				if(cI != null)
				{
					System.out.println("Fixing client old");
					cI.setCanRemove(false);
					cI.setCanAsk(true,ClientInformation.SERVER_WAIT);
	
				}
				String newFirstQuestion = getFirstQuestion();
				if(newFirstQuestion!=null)
				{
					//System.out.println("new first Question");
					ClientInformation newFirstCI = getClientByRefName(newFirstQuestion);
					if(newFirstCI != null)
					{
						//System.out.println("Fixing client old");
						newFirstCI.setCanRemove(false);
						newFirstCI.setCanAsk(false,ClientInformation.NO_WAIT);
					}
				}
				screen.repaint();
				return s;
			}
			else
				return null;
		}
	}
	
	public static ArrayList<String> getQuestions()
	{
		return questions;	
	}
	
	public static String clientRemoveQuestion(String refName)
	{
		if(questions.size() == 0 || questions.get(0).equals(refName))
			return null;
		else
		{
			for(int i=0; i<questions.size();i++)
			{
				if(questions.get(i).equals(refName))
				{
					screen.repaint();
					return questions.remove(i);
				}
			}
			return null;
		}
	}
	
	public static void clearQuestions()
	{
		questions.clear();
		for(int i=0; i<clients.size();i++)
		{
			ClientInformation cI = clients.get(i);
			cI.setCanAsk(true,ClientInformation.NO_WAIT);
			cI.setCanRemove(false);
			cI.setWaitTime(0);
		}
		screen.repaint();
	}
	
	public static void addQuestion(String refName)
	{
		if(questions.size() ==0)
		{
			playQuestionSound();	
		}
		else
		{
			ClientInformation ci = getClientByRefName(refName);
			if(ci != null)
			{
				ci.setCanRemove(true);
			}
		}
		questions.add(refName);
		screen.repaint();
	}
	
	public static ClientInformation getClientByComputerName(String computerName)
	{
		if(clients.size() == 0)
			return null;
		else
		{
			for(int i=0; i<clients.size();i++)
			{
				if(clients.get(i).getComputerName().equals(computerName))
				{
					return clients.get(i);
				}
			}
			return null;
		}
	}
	
	
	public static ClientInformation getClientByRefName(String refName)
	{
		if(clients.size() == 0)
			return null;
		else
		{
			for(int i=0; i<clients.size();i++)
			{
				if(clients.get(i).getRefName().equals(refName))
				{
					return clients.get(i);
				}
			}
			return null;
		}
	}
	
	public static int size()
	{
		return questions.size();	
	}
	
	public static ArrayList<ClientInformation> getClients()
	{
		return clients;	
	}
	
	public static void setPortNumber(int portNum)
	{
		portNumber = portNum;	
	}
	
	public static int getPortNumber()
	{
		return portNumber;	
	}
}