import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.awt.image.*;

public class ServerPane extends JPanel implements Runnable,  KeyListener
{
	JButton btnExit				= new JButton("Exit");
	JButton btnRemove			= new JButton("Remove");
	JButton btnClear			= new JButton("Clear");
	JTextArea txtQuestions		= new JTextArea();
	JScrollPane	scrollBox		= null;
	Color background			= new Color(200,200,200);
	int height 	=0;
	int width	=0;
	BufferedImage img;
	
	ServerPane(int width,int height)
	{
		super();
		this.width = width;
		this.height = height;
		setSize(width, height);
    	JScrollPane	scrollBox		= new JScrollPane(txtQuestions,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		
		int point = (int)(height/10);
		Font f = new Font("Courier",Font.PLAIN,point);
		txtQuestions.setFont(f);
		
		point = (int)(height/50);
		f = new Font("Times New Roman",Font.BOLD,point);
		btnExit.setFont(f);
		btnClear.setFont(f);
		btnRemove.setFont(f);
		
    	setBackground(Color.black);
    	txtQuestions.setEditable(false);
    	btnExit.setBounds((int)(width*.85),(int)(height*.90),(int)(width*.10),(int)(height*.05));
    	btnClear.setBounds((int)(width*.85),(int)(height*.80),(int)(width*.10),(int)(height*.05));
    	btnRemove.setBounds((int)(width*.85),(int)(height*.70),(int)(width*.10),(int)(height*.05));
    	scrollBox.setBounds((int)(width*.85),(int)(height*.05),(int)(width*.10),(int)(height*.60));
 		setLayout(null);
 		add(scrollBox);
 		add(btnRemove);
 		add(btnExit);
 		add(btnClear);
		setIgnoreRepaint(true);
 		btnExit.addActionListener(new java.awt.event.ActionListener()
 		{
 			public void actionPerformed(ActionEvent e)
 			{
 				exit();	
 			}
 		});
 		
 		btnClear.addActionListener(new java.awt.event.ActionListener()
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
 		});	
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		updateQuestions();
		
		g.setColor(Color.black);
		g.fillRect(0,0,(int)(width*.80),height);
		g.setColor(Color.white);
		g.drawRect((int)(width*.05),(int)(height*.05),(int)(width*.75),(int)(height*.90));				
		int point = (int)(height*.9);
		g.setColor(Color.red);
		Font f = new Font("Courier",Font.PLAIN,point);
		g.setFont(f);
		if(QuestionList.size()>0)
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
		}	
	}	
	
	public void draw(Graphics g, BufferedImage img)
	{
		g.drawImage(img,0,0,null);

	}
	
	public void run()
	{
		/*while(true)
		{
			img = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
			render(img.getGraphics());
			draw(this.getGraphics(), img);
			img = null;
			try
			{
				Thread.sleep(40);	
			}
			catch(Exception e)
			{
				System.out.println("Error in serverPane run: " + e.getMessage());	
			}
			
		}*/	
		
		while(true)
		{
			updateClients();
			try
			{
				Thread.sleep(300);	
			}
			catch(Exception e)
			{
				System.out.println("Error in serverPane run: " + e.getMessage());	
			}
			
		}
	}
	
	public void exit()
	{
		System.out.println("closing");
		System.exit(0);
	}
	
	public void removeQuestion()
	{
		String s = QuestionList.serverRemoveFirstQuestion();
		requestFocus();	
		updateClients();
	}
	
	public void updateClients()
	{
		
		ArrayList<ClientInformation> clients = QuestionList.getClients();
		for(int i=0; i< clients.size(); i++)
		{
			ClientInformation cI= clients.get(i);
			if(cI.getConnected())
			{
				cI.updateClient();	
			}	
		}
		repaint();
		
	}
	
	public void clearQuestions()
	{
		QuestionList.clearQuestions();
		requestFocus();	
		updateClients();
	}

	public void updateQuestions()
	{

		String newText 	= "";
		if(QuestionList.size() > 0)
		{
			ArrayList<String> questions = QuestionList.getQuestions();
			for(int i = 1; i<questions.size();i++)
			{
				String q = questions.get(i);
				
				if(q.length()==1)
					q = " " + q;
				
				if(i==1)
					newText += q;
				else
					newText += "\n"+q;
			}
		}
		txtQuestions.setText(newText);

	}
	
	public void addNotify()
	{
		super.addNotify();
		Thread t = new Thread(this);
		t.start();
		addKeyListener(this);
		requestFocus();	
	}
	
	public void keyReleased(KeyEvent e)
	{}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()=='.')
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
		}
	}
	public void keyTyped(KeyEvent e)
	{

	}
	
}