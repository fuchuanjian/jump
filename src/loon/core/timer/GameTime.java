
package loon.core.timer;

public class GameTime {

	float _elapsedTime;
	float _totalTime;

	boolean _running;

	public GameTime() {
		_elapsedTime = _totalTime = 0f;
	}

	public GameTime(float totalGameTime, float elapsedGameTime) {
		_totalTime = totalGameTime;
		_elapsedTime = elapsedGameTime;
	}

	public GameTime(float totalRealTime, float elapsedRealTime,
			boolean isRunningSlowly) {
		_totalTime = totalRealTime;
		_elapsedTime = elapsedRealTime;
		_running = isRunningSlowly;
	}

	public void update(float elapsed) {
		_elapsedTime = elapsed;
		_totalTime += elapsed;
	}

	public void update(LTimerContext context) {
		update(context.getMilliseconds());
	}

	public void resetElapsedTime() {
		_elapsedTime = 0f;
	}

	public boolean isRunningSlowly() {
		return _running;
	}

	public float getMilliseconds() {
		return _elapsedTime * 1000;
	}

	public float getElapsedGameTime() {
		return _elapsedTime;
	}

	public float getTotalGameTime() {
		return _totalTime;
	}
	
}
