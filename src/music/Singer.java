/*
 * Created on May 9, 2018
 *
 */
package music;

public class Singer extends Thread {

	private String singerName;
	private Voice voice;
	private Performance performance;

	private boolean notPlaying;

	public boolean isNotPlaying() {
		return notPlaying;
	}

	public void setNotPlaying(boolean notPlaying) {
		this.notPlaying = notPlaying;
	}

	private boolean stopIt;
	private Synchronizer synch;

	public Singer(String singerName, Voice voice, Performance performance, boolean stopIt, Synchronizer synch,
			boolean notPlaying) {
		super();
		this.singerName = singerName;
		this.voice = voice;
		this.performance = performance;
		this.stopIt = stopIt;
		this.synch = synch;
		this.notPlaying = notPlaying;
	}

	public Singer(String singerName, Voice voice, Performance performance, boolean stopIt, boolean notPlaying) {
		super();
		this.singerName = singerName;
		this.voice = voice;
		this.performance = performance;
		this.stopIt = stopIt;
		this.notPlaying = notPlaying;
	}

	public Singer() {
		super();
	}

	@Override
	public void run() {
		sing();
	}

	private synchronized void sing() {
		if (notPlaying)
			return;
		while (!stopIt) {
			if (this.voice == Voice.FIRST) {
				this.synch.singFirstVoice(performance.getLyrics(), performance.getDelay(), notPlaying);
			} else if (this.voice == Voice.SECOND) {
				this.synch.singSecondVoice(performance.getLyrics(), performance.getDelay(), notPlaying);
			} else {
				this.synch.singThirdVoice(performance.getLyrics(), performance.getDelay(), notPlaying);
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
