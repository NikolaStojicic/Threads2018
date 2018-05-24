package gui.controller;

import java.awt.EventQueue;

import gui.MainWindow;
import test.Test;

public class GUIController {

	public static Test t = new Test();

	/**
	 * Launch the application.
	 */
	public static synchronized void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					t.initializeSingingInThreads();
					t.pattiSmith.start();
					t.bruceSpringsteen.start();
					t.michaelStipe.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
