package theClient.graphics;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class AltTabStopper implements Runnable, KeyListener {
	private boolean working = true;
	private JFrame frame;

	public AltTabStopper(JFrame frame) {
		this.frame = frame;
		frame.addKeyListener(this);
		Thread t = new Thread(this);
		t.start();
		// working = false;

	}

	public void stop() {
		working = false;
	}

	// public static AltTabStopper create(JFrame frame)
	// {
	// AltTabStopper stopper = new AltTabStopper(frame);
	//
	// new Thread(stopper, "Alt-Tab Stopper").start();
	// return stopper;
	// }

	public void run() {
		try {
			Robot robot = new Robot();
			while (working) {
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_WINDOWS);
				frame.requestFocus();
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'x') {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}