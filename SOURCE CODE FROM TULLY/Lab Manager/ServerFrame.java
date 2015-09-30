import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.awt.image.*;

public class ServerFrame extends JFrame implements KeyListener, WindowListener
{
	JButton btnExit					= new JButton("Exit");
	JButton btnRemove				= new JButton("Remove");
	JButton btnClear				= new JButton("Clear List");
	
	JTextArea txtQuestions			= new JTextArea();
	JScrollPane	scrollBox			= null;
	
	JScrollPane	screenScrollBox		= null;
	Color background				= new Color(200,200,200);
	
	int height 	=1024;
	int width	=1600;

	ScreenPanel screenPanel 		= null;
	ServerFrame()
	{
		super("Lab Manager");
		setSize(width,height);
		
		setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    	scrollBox		= new JScrollPane(txtQuestions,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		
		screenPanel			= new ScreenPanel((int)(width*.75),(int)(height*.83));
		screenScrollBox		= new JScrollPane(screenPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		
		int point = (int)(getHeight()/10);
		Font f = new Font("Courier",Font.PLAIN,point);
		txtQuestions.setFont(f);
		
		point = (int)(getHeight()/50);
		f = new Font("Times New Roman",Font.BOLD,point);
		btnExit.setFont(f);
		btnClear.setFont(f);
		btnRemove.setFont(f);
		
    	txtQuestions.setEditable(false);
    	
    	btnExit.setBounds((int)(width*.85),(int)(height*.90),(int)(width*.10),(int)(height*.05));
    	btnClear.setBounds((int)(width*.85),(int)(height*.83),(int)(width*.10),(int)(height*.05));
    	btnRemove.setBounds((int)(width*.85),(int)(height*.76),(int)(width*.10),(int)(height*.05));
    	scrollBox.setBounds((int)(width*.85),(int)(height*.05),(int)(width*.10),(int)(height*.70));
    	screenScrollBox.setBounds((int)(width*.05),(int)(height*.05),(int)(width*.75),(int)(height*.83));
 		setLayout(null);
 		add(scrollBox);
 		add(screenScrollBox);
 		add(btnRemove);
 		add(btnExit);
 		add(btnClear);
		
 		btnExit.addActionListener(new java.awt.event.ActionListener()
 		{
 			public void actionPerformed(ActionEvent e)
 			{
 				exit();	
 			}
 		});
 		
 		/*btnClear.addActionListener(new java.awt.event.ActionListener()
 		{
 			public void actionPerformed(ActionEvent e)
 			{
 				clearQuestions();	
 			}
 		});
 		
 		btnRemove.addActionListener(new java.awt.event.ActionListener()
 		{
 			public void actionPerformed(ActionEvent e)
 			{
 				removeQuestion();	
 			}
 		});	*/
 			
		addWindowListener(this);
 		setVisible(true);		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0,0,(int)(width),height);
		g.setColor(Color.white);
		g.drawRect((int)(width*.05),(int)(height*.05),(int)(width*.75),(int)(height*.90));				
		//int point = (int)(height*.9);
		//g.setColor(Color.red);
		//Font f = new Font("Courier",Font.PLAIN,point);
		//g.setFont(f);
		/*if(QuestionList.size()>0)
		{
			if(QuestionList.getFirstQuestion().length() ==1)
			{
				g.drawString(QuestionList.getFirstQuestion(),(int)(width*.23),(int)(height*.80));
			}
			else if(QuestionList.getFirstQuestion().length() ==2)
			{
				g.drawString(QuestionList.getFirstQuestion(),(int)(width*.041),(int)(height*.80));
			}
			else
			{
				
			}
		}*/
		paintComponents(g);	
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
		
	public void keyReleased(KeyEvent e)
	{}
	
	public void keyPressed(KeyEvent e)
	{
		/*if(e.getKeyCode()=='.')
		{
			clearQuestions();
		}
		else if(e.getKeyCode()==e.VK_F5)
		{
			removeQuestion();
		}
		else if(e.getKeyCode()==e.VK_ESCAPE)
		{
			removeQuestion();
		}*/
	}
	public void keyTyped(KeyEvent e)
	{

	}
	
	public void exit()
	{
		System.out.println("closing");
		System.exit(0);
	}
}