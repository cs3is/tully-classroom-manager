package graphics;

import javax.swing.JFrame;

import utils.AdminConfigManager;
import utils.AdminLog;

public class AdminFrame extends JFrame {

	public AdminFrame() {
		super("Classroom Manager");
		AdminLog.debug("opening AdminFrame");
		setSize(AdminConfigManager.getXResolution(), AdminConfigManager.getYResolution());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		setLayout(null);
		
		setVisible(true);
		
	}

}
