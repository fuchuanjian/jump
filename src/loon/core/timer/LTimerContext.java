package loon.core.timer;


public class LTimerContext {

	public long timeSinceLastUpdate, millisSleepTime;

	public LTimerContext() {
		timeSinceLastUpdate = 0;
	}

	public synchronized void setTimeSinceLastUpdate(long timeSinceLastUpdate) {
		this.timeSinceLastUpdate = timeSinceLastUpdate;
	}

	public synchronized long getTimeSinceLastUpdate() {
		return timeSinceLastUpdate;
	}

	public float getMilliseconds() {
		return timeSinceLastUpdate / 1000f;
	}

	public long getSleepTimeMicros() {
		return millisSleepTime * 1000;
	}

}
