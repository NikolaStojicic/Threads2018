/*
 * Created on May 10, 2018
 *
 */
package music;

import javax.swing.JTextArea;

import gui.MainWindow;

public class Synchronizer {

	private static boolean fFlag = false, sFlag = true, tFlag = true;

	public Synchronizer() {
		super();
	}

	public synchronized void singFirstVoice(String lyrics, int delay, boolean skip) {
		while (fFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sing(lyrics, delay, "fFlag", skip);
	}

	public synchronized void singSecondVoice(String lyrics, int delay, boolean skip) {
		while (sFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sing(lyrics, delay, "sFlag", skip);
	}

	public synchronized void singThirdVoice(String lyrics, int delay, boolean skip) {
		while (tFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sing(lyrics, delay, "tFlag", skip);
	}

	public static synchronized void toggleFlag(String flag) {
		if (flag.equals("fFlag")) {
			fFlag = !fFlag;
			sFlag = !sFlag;
		} else if (flag.equals("sFlag")) {
			sFlag = !sFlag;
			tFlag = !tFlag;
		} else if (flag.equals("tFlag")) {
			tFlag = !tFlag;
			fFlag = !fFlag;
		}
	}

	private synchronized void sing(String lyrics, int delay, String flag, boolean skip) {
		if (!skip) {
			// System.out.println(lyrics);
			JTextArea tf = MainWindow.getTextArea();
			tf.setText(tf.getText() + lyrics + "\n");
		} else {
			pause(50, flag);
			return;
		}
		pause(delay, flag);
	}

	private void pause(int delay, String flag) {
		try {
			wait(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toggleFlag(flag);
		notifyAll();
	}
}
