/*
package graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import utils.ConfigManager;

public class MainFrame extends JFrame {

	public MainPanel p = new MainPanel();
	
	public MainFrame() {
		super("Frame");
		setSize(ConfigManager.getWidth(), ConfigManager.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, (dim.height / 2 - this.getSize().height / 2) - 25);

		add(p);

		setUndecorated(true);
		setVisible(true);
	}
}
*/