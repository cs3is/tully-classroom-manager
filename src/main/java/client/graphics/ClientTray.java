package graphics;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JSeparator;

import client.ClientData;
import client.ClientListener;
import util.Task;
import util.infoForClientToReceiveAndParseAndProbablyUseToo;
import utils.ClientConfig;
import utils.ClientConfigManager;
import utils.ClientLog;
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
	private boolean firstUp = true;
	private boolean canQuestion = true;
	private long time = 0;
	private long prevLabel = 0;
	long qTime = 0;

	private MenuItem addQuestion = null;
	private MenuItem exit = null;
	private MenuItem submitAssignment = null;
	private MenuItem takeTest = null;

	private PopupMenu popup = null;
	TrayIcon trayIcon = null;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final SystemTray tray = SystemTray.getSystemTray();

	private long timeBetweenQuestions = 0;

	public ClientTray(ClientData cd) {
		// init objectstreams
		this.cd = cd;

		Runtime run;
		Robot robo;
		// TODO MAKE SURE THIS WORKS

		userName = System.getProperty("user.name");
		System.out.println("The user name is: " + userName.trim());
		try {
			cd.getOut().writeObject(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ClientLog.debug("Starting clientlistener...");
		cl = new ClientListener(cd);
		// get username

		init();
		initInit();

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
		takeTest = new MenuItem("Take Test");
		submitAssignment = new MenuItem("Submit Assignment");
		components.add(addQuestion);
		components.add(submitAssignment);
		components.add(takeTest);
		components.add(exit);
	}

	public void addMenuComponents() {
		for (int i = 0; i < components.size(); i++) {
			popup.add(components.get(i));
			if (i == 1) {
				popup.addSeparator();
			}
			if (i == 2) {
				popup.addSeparator();
			}
		}
		trayIcon.setPopupMenu(popup);
	}

	public void addActionListeners() {

		addQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addQuestion.setEnabled(false);
				new Thread(addQuestionThread).start();

			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		submitAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.exit(0);
			}
		});

		takeTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.exit(0);
			}
		});

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

	public void updateMenu() {
		if (!canQuestion)
			if (cd.getCountdownBegin()) {
				if (firstUp) {
					qTime = time;
					firstUp = false;
				}
				updateQuestion();
			}
	}

	public void updateQuestion() {
		long diff = time - qTime;
		long label = (((timeBetweenQuestions * 1000000000L) - diff) / 1000000000L);
		ClientLog.info(time + "");
		ClientLog.info(qTime + "");
		ClientLog.info(diff + "");
		ClientLog.info(label + "");
		ClientLog.info(timeBetweenQuestions + "");
		ClientLog.info("______---------========^^^^^^^^^^    ^_^");
		if (diff > timeBetweenQuestions * 1000000000L) {
			addQuestion.setEnabled(true);
			canQuestion = true;
			addQuestion.setLabel("Add Question");
			firstUp = true;
			cd.setCountdownBegin(false);
		}
		if (label != prevLabel)
			addQuestion.setLabel("Add Question (" + label + ")");
		prevLabel = label;

		// cd.getOS(Task.REQUEST_SYNC);

	}

	/**
	 * THIS ADDS QUESTIONS IF YOU CAN'T READ
	 */
	Runnable addQuestionThread = new Runnable() {
		@Override
		public void run() {
			ClientLog.info("Adding Question");
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
								ClientLog.info("Question has been added!");
								added = true;
								break;
							}
							ClientLog.info("Have not received confirmation from server for " + (5 - i)
									+ " seconds, retrying " + i + " more times");

						} catch (Exception e) {
							ClientLog.error("error at addQuestion while waiting for response");
							e.printStackTrace();
						}
					}
					if (added) {
						added = false;
					} else {
						ClientLog.error("server did not respond to addQuestion in 5 seconds");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

	public void initializeThreads() {

	}

	public void initInit() {
		timeBetweenQuestions = ((infoForClientToReceiveAndParseAndProbablyUseToo) cl.getConfig())
				.getTimeBetweenQuestions();
	}

	public ArrayList<String> listTasks() {
		ArrayList<String> processes = new ArrayList<String>();
		try {
			String line;
			Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (!line.trim().equals("")) {
					// keep only the process name
					line = line.substring(1);
					// System.out.println(line.substring(0,
					// line.indexOf("\"")));
					processes.add(line.substring(0, line.indexOf("\"")));
				}

			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return processes;
	}
}
