package gui.controller;

import java.awt.EventQueue;

import gui.MainWindow;
import music.Singer;
import test.Test;

public class GUIController {

	public static Test t = new Test();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					initThreads();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static void initThreads() {
		t.initializeSingingInThreads();
	}

	public static void toggleSinger(Singer singer) {
		if (!singer.isAlive()) {
			System.out.println(singer);
			singer.start();
			return;
		}
		boolean stop = !singer.isStopIt();
		singer.setStopIt(stop);
	}

}
