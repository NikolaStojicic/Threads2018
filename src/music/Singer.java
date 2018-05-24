/*
 * Created on May 9, 2018
 *
 */
package music;

public class Singer extends Thread {

	private String singerName;
	private Voice voice;
	private Performance performance;

	private boolean stopIt;
	private Synchronizer synch;
	public static boolean notPlayingP, notPlayingB, notPlayingM;

	public Singer(String singerName, Voice voice, Performance performance, boolean stopIt, Synchronizer synch) {
		super();
		this.singerName = singerName;
		this.voice = voice;
		this.performance = performance;
		this.stopIt = stopIt;
		this.synch = synch;
	}

	public static void togglePatti() {
		notPlayingP = !notPlayingP;
	}

	public static void toggleBruce() {
		notPlayingB = !notPlayingB;
	}

	public static void toggleMichael() {
		notPlayingM = !notPlayingM;
	}

	public Singer(String singerName, Voice voice, Performance performance, boolean stopIt) {
		super();
		this.singerName = singerName;
		this.voice = voice;
		this.performance = performance;
		this.stopIt = stopIt;
	}

	public Singer() {
		super();
	}

	@Override
	public void run() {
		sing();
	}

	private synchronized void sing() {
		while (!stopIt) {
			if (this.voice == Voice.FIRST) {
				this.synch.singFirstVoice(performance.getLyrics(), performance.getDelay(), notPlayingP);
			} else if (this.voice == Voice.SECOND) {
				this.synch.singSecondVoice(performance.getLyrics(), performance.getDelay(), notPlayingB);
			} else {
				this.synch.singThirdVoice(performance.getLyrics(), performance.getDelay(), notPlayingM);
			}
		}
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Performance getPerformance() {
		return performance;
	}

	public void setPerformance(Performance performance) {
		this.performance = performance;
	}

	public boolean isStopIt() {
		return stopIt;
	}

	public void setStopIt(boolean stopIt) {
		this.stopIt = stopIt;
	}

	public Synchronizer getSynch() {
		return synch;
	}

	public void setSynch(Synchronizer synch) {
		this.synch = synch;
	}

}
