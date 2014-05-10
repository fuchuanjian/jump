package loon.core.timer;


public class SystemTimer {

	private long lastTime = 0;

	private long virtualTime = 0;

	public SystemTimer() {
		start();
	}

	public void start() {
		lastTime = System.currentTimeMillis();
		virtualTime = 0;
	}

	public long sleepTimeMicros(long goalTimeMicros) {
		long time = goalTimeMicros - getTimeMicros();
		if (time > 100) {
			try {
				Thread.sleep((time + 100) >> 10);
			} catch (InterruptedException ex) {
			}
		}
		return getTimeMicros();
	}

	public static long sleepTimeMicros(long goalTimeMicros, SystemTimer timer) {
		long time = goalTimeMicros - timer.getTimeMicros();
		if (time > 100) {
			try {
				Thread.sleep((time + 100) >> 10);
			} catch (InterruptedException ex) {
			}
		}
		return timer.getTimeMicros();
	}

	public long getTimeMillis() {
		long time = System.currentTimeMillis();
		if (time > lastTime) {
			virtualTime += time - lastTime;
		}
		lastTime = time;
		return virtualTime;
	}

	public long getTimeMicros() {
		return getTimeMillis() << 10;
	}

	public void stop() {

	}
}
