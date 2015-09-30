import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.awt.image.*;

public class ServerFrame extends JFrame
{
	
	Thread listener = null;
	int height 	=0;
	int width	=0;
	ServerPane sP = null;
	ServerFrame()
	{
		super("Question Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
    	
    	sP=new ServerPane(700,500);
    	
    	Insets in = getInsets();
    	
    	int frameWidth = (int)sP.getWidth()
    				+(in.left + in.right);
    	
    	int frameHeight = (int)sP.getHeight()
    				+(in.top + in.bottom);

    	
    	setPreferredSize(new Dimension ((int)frameWidth,(int)frameHeight));
    	
    	setLayout(null);
    	
    	add(sP);
    	
    	pack();
    	setResizable(false);
    	
		//addWindowListener(this);
 		setVisible(true);		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		sP.repaint();
	}
	
	public void updateData()
	{
		sP.updateQuestions();
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
		System.out.println("closing");
		System.exit(0);
	}
	
	public void windowClosed(WindowEvent e) 
	{
		System.out.println("closing");
		System.exit(0);
	} 
		

}