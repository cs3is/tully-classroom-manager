package theClient.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import shared.utils.Log;
import theClient.res.ClientConfigManager;

public class LockFrame extends JFrame {
	BufferedImage image;

	public LockFrame(BufferedImage i) {
		super();
		image = i;
		Log.debug("scren lucked");
		setSize(ClientConfigManager.getXResolution(), ClientConfigManager.getYResolution());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		new AltTabStopper(this);

	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);

	}

}
