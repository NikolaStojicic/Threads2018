/*
 * Created on May 10, 2018
 *
 */
package music;

import javax.swing.JTextArea;

import gui.MainWindow;

public class Synchronizer {

	private boolean firstVoiceFlag;

	public Synchronizer(boolean firstVoiceFlag) {
		super();
		this.firstVoiceFlag = firstVoiceFlag;
	}

	public synchronized void singFirstVoice(String lyrics, int delay) {
		while (!firstVoiceFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sing(lyrics, delay);
	}

	public synchronized void singSecondVoice(String lyrics, int delay) {
		while (firstVoiceFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sing(lyrics, delay);
	}

	private void sing(String lyrics, int delay) {
		System.out.println(lyrics);
		JTextArea tf = MainWindow.getTextArea();
		tf.setText(tf.getText() + lyrics + "\n");
		try {
			wait(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		firstVoiceFlag = !firstVoiceFlag;
		notifyAll();
	}

}
