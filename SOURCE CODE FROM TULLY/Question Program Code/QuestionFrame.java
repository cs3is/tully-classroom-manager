import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;

public class QuestionFrame extends JFrame implements WindowListener
{
	private JButton btnExit					= new JButton("Exit");
	private JButton btnAddQuestion			= new JButton("Add Question");
	private JButton btnRemoveQuestion		= new JButton("Remove Question");
	private JTextField txtPlaceInLine		= new JTextField();
	private JLabel lblPlaceInLine			= new JLabel("Spot in line: ");
	private Color background		= new Color(200,200,200);
	private ObjectOutputStream osToServer = null;
	
	QuestionFrame(ObjectOutputStream osToServer, ObjectInputStream isFromServer)
	{
		super("Question");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setPreferredSize( new Dimension(150, 135));
    	this.osToServer = osToServer;
    	btnAddQuestion.setEnabled(false);
    	btnRemoveQuestion.setEnabled(false);
    	Container c = getContentPane();
    	c.setSize(200,200);
    	lblPlaceInLine.setBounds(5,10,80,15);
    	txtPlaceInLine.setBounds(90,10,30,15);
    	
    	btnAddQuestion.setBounds(3,35,135,20);
    	btnRemoveQuestion.setBounds(3,60,135,20);
		btnExit.setBounds(40,85,60,20);
		
    	txtPlaceInLine.setEditable(false);
 		c.setBackground(background);
 		c.setLayout(null);
 		c.add(btnExit);
 		c.add(btnAddQuestion);
 		c.add(btnRemoveQuestion);
 		c.add(txtPlaceInLine);
 		c.add(lblPlaceInLine);
 		pack();
 		setVisible(true);
 		setResizable(false);
		addWindowListener(this);
 		
 		Thread listener = new Thread(new ClientListener(isFromServer,this));
		listener.start();
 		
 		btnExit.addActionListener(new java.awt.event.ActionListener()
 		{
 			public void actionPerformed(ActionEvent e)
 			{
 				exit();	
 			}
 		});
 		
 		
 		btnAddQuestion.addActionListener(new java.awt.event.ActionListener()
 		{
 			public void actionPerformed(ActionEvent e)
 			{
 				addQuestion();	
 			}
 		});
 		
 		btnRemoveQuestion.addActionListener(new java.awt.event.ActionListener()
 		{
 			public void actionPerformed(ActionEvent e)
 			{
 				removeQuestion();	
 			}
 		});
	}
	
	public void setPlaceInLine(String s)
	{
		lblPlaceInLine.setText("Spot in line: ");
		txtPlaceInLine.setText(s);
		repaint();
	}
	
	
	public void setWaitTime(String s)
	{
		lblPlaceInLine.setText("Waiting time: ");
		txtPlaceInLine.setText(s);
		repaint();
	}
	
	public void enableQuestion(boolean b)
	{
		btnAddQuestion.setEnabled(b);
	}
	
	public void enableRemoveQuestion(boolean b)
	{
		btnRemoveQuestion.setEnabled(b);
	}

	public void exit()
	{
		System.out.println("Closing down ...");
		System.exit(0);
	}
	
	public void serverExit()
	{
		System.out.println("Server has terminated the connection.");
		System.out.println("Closing down ...");
		JOptionPane.showMessageDialog(null, "Lost connection to server.");
		System.exit(0);	
	}
	public void addQuestion()
	{
		try
		{
			if(osToServer!=null)
			{
				osToServer.writeObject(new Command(Command.ADD_QUESTION));
				btnAddQuestion.enable(true);
    			btnRemoveQuestion.enable(false);
    		}
				
		}
		catch(Exception ex)
		{
			System.out.println("Lost connection to the server.");
			System.out.println("Closing down ...");
			JOptionPane.showMessageDialog(null, "Lost connection to server.");
			System.exit(0);	
		}
	}
	public void removeQuestion()
	{
		try
		{
			if(osToServer!=null)
			{
				osToServer.writeObject(new Command(Command.REMOVE_QUESTION));
				btnAddQuestion.enable(true);
    			btnRemoveQuestion.enable(false);
    		}	
		}
		catch(Exception ex)
		{
			System.out.println("Lost connection to the server.");
			System.out.println("Closing down ...");
			JOptionPane.showMessageDialog(null, "Lost connection to server.");
			System.exit(0);
		}
	}
	public void windowActivated(WindowEvent e) 
	{}
	public void windowDeactivated(WindowEvent e) 
	{}
	public void windowDeiconified(WindowEvent e) 
	{} 
	public void windowIconified(WindowEvent e) 
	{} 
	public void windowOpened(WindowEvent e) 
	{}
	public void windowClosing(WindowEvent e) 
	{
		System.out.println("Closing down ...");
		System.exit(0);	
	}
	
	public void windowClosed(WindowEvent e) 
	{
		
		try
		{
			if(osToServer!=null)
				osToServer.writeObject(new Command(Command.DISCONNECT));	
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());	
		}
		System.out.println("Closing down ...");
		System.exit(0);
	} 
}