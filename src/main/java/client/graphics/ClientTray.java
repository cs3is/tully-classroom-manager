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

import client.ClientData;
import client.ClientListener;
import util.Task;
import utils.ClientConfig;
import utils.ClientConfigManager;
import utils.Log;
import utils.ServerLog;

public class ClientTray implements Runnable {
	private ClientData cd = null;
	private ClientConfigManager cfg = null;
	ClientListener cl = null;

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
	

	public ClientTray(ClientData cd) {
		// init objectstreams
		this.cd = cd;
		cl = new ClientListener(cd);

		// get username
		userName = System.getProperty("user.name");
		System.out.println("The user name is: " + userName.trim());
		try {
			cd.getOut().writeObject(userName);
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

		// tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit()
				.getImage("bin/../src/main/../main/resources/../java/client/../../resources/client.png");
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
		for (int i = 0; i < components.size(); i++) {
			popup.add(components.get(i));
		}
		trayIcon.setPopupMenu(popup);
	}

	public void addActionListeners() {

		addQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				qTime = time;
				addQuestion.setEnabled(false);
				addQuestionThread.start();
				
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public void updateMenu() {
		if (!canQuestion)
			if(cd.getCountdownBegin())
				updateQuestion();
	}

	public void updateQuestion() {
		long diff = time - qTime;
		long label = (((120 * 1000000000L) - diff) / 1000000000L);
		// Log.info(time+"");
		// Log.info(qTime+"");
		if (diff > 120 * 1000000000L) {
			addQuestion.setEnabled(true);
			canQuestion = true;
			addQuestion.setLabel("Add Question");
		}
		if (label != prevLabel)
			addQuestion.setLabel("Add Question (" + label + ")");
		prevLabel = label;

		// cd.getOS(Task.REQUEST_SYNC);

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
	/**
	 * THIS ADDS QUESTIONS IF YOU CAN'T READ
	 */
	Thread addQuestionThread = new Thread(new Runnable() {
		@Override
		public void run() {
			Log.info("Adding Question");
			if (canQuestion) {
				canQuestion = false;
				try {
					cd.getOut().writeObject(new Task(Task.ASK_QUESTION));
					Boolean added = false;
					for (int i = 0; i < 5; i++) {
						try {
							Thread.sleep(1000);
							if (cd.getQuestionAdded() != null) {
								cd.setQuestionAdded(null);
								cd.setCountdownBegin(false);
								Log.info("Question has been added!");
								added = true;
								break;
							}
							Log.info("Have not received confirmation from server for "+(3-i)+" seconds, retrying "+i+" more times");
							

						} catch (Exception e) {
							Log.error("error at addQuestion while waiting for response");
							e.printStackTrace();
						}
					}
					if(added){
						added = false;
					}
					else{
						Log.error("server did not respond to addQuestion in 5 seconds");
						//TODO ERROR MESSAGE
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	});
	public void initializeThreads() {
		
	}

}