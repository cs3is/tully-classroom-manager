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
import java.util.ArrayList;

import client.ClientData;
import client.ClientListener;
import utils.ClientConfig;
import utils.ClientConfigManager;
import utils.ServerLog;

public class AdminTray implements Runnable{
	
	private ArrayList<MenuItem> components = new ArrayList<MenuItem>();
	private MenuItem exit = null;
	private PopupMenu popup = null;
	TrayIcon trayIcon = null;
	final SystemTray tray = SystemTray.getSystemTray();
	
	public AdminTray(){
		init();
	}
	
	
	public void init() {
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
		trayIcon = new TrayIcon(image, "ClientConfig.NAME");

		ServerLog.info(trayIcon.getImage() + "");

	}

	public void createMenuComponents() {
		exit = new MenuItem("Exit");
		components.add(exit);
	}

	public void addMenuComponents() {
		for (int i = 0; i < components.size(); i++) {
			popup.add(components.get(i));
		}
		trayIcon.setPopupMenu(popup);
	}

	public void addActionListeners() {

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		trayIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
//		trayIcon.addMouseListener(new MouseAdapter() {
//		    public void mouseClicked(MouseEvent e) {
//		        if (e.getClickCount() == 1) {
//
//		        }
//		    }
//		}); 
			

	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

