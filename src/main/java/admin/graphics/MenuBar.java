package graphics;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import utils.AdminLog;

public class MenuBar {

	public static JMenuBar menu;
	JMenu file;
	JMenu selectProfile;
	ArrayList<JMenuItem> teachers = new ArrayList<JMenuItem>();

	public MenuBar() {
		AdminLog.debug("creating the MenuBar");

		menu = new JMenuBar();
		file = new JMenu("file");
		selectProfile = new JMenu("Select Profile");

		menu.add(file);

		file.add(selectProfile);

	}

}
