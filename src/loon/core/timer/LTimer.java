package loon.core.timer;

import java.io.Serializable;


public class LTimer implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private boolean active = true;

	private long delay;

	private long currentTick;

	public LTimer() {
		this(450);
	}

	public LTimer(long delay) {
		this.delay = delay;
	}

	public boolean action(long elapsedTime) {
		if (this.active) {
			this.currentTick += elapsedTime;
			if (this.currentTick >= this.delay) {
				this.currentTick -= this.delay;
				return true;
			}
		}

		return false;
	}
	
	public boolean action(LTimerContext context) {
		if (this.active) {
			this.currentTick += context.getTimeSinceLastUpdate();
			if (this.currentTick >= this.delay) {
				this.currentTick -= this.delay;
				return true;
			}
		}
		return false;
	}
	
	public void refresh() {
		this.currentTick = 0;
	}

	public void setEquals(LTimer other) {
		this.active = other.active;
		this.delay = other.delay;
		this.currentTick = other.currentTick;
	}

	public boolean isActive() {
		return this.active;
	}
	
	public void start() {
		this.active = true;
	}
	
	public void stop() {
		this.active = false;
	}
	
	public void setActive(boolean bool) {
		this.active = bool;
		this.refresh();
	}

	public long getDelay() {
		return this.delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
		this.refresh();
	}

	public long getCurrentTick() {
		return this.currentTick;
	}

	public void setCurrentTick(long tick) {
		this.currentTick = tick;
	}
}
