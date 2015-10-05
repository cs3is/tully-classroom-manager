package main;

import graphics.MainFrame;
import utils.ConfigManager;

public class Main {
	public static MainFrame f;

	public static void main(String[] args) {

		new ConfigManager();

		f = new MainFrame();

	}

}
