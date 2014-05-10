
package loon.action.sprite.node;

import loon.utils.MathUtils;

public abstract class LNAction {

	protected Easing _easing;

	protected float _duration;

	protected float _elapsed;

	protected boolean _firstTick = true;

	protected boolean _isEnd;

	protected boolean _isPause = false;

	protected LNNode _target;

	public final void assignTarget(LNNode node) {
		this._target = node;
	}

	public void pause() {
		this._isPause = true;
	}

	public void resume() {
		this._isPause = false;
	}

	public void setTarget(LNNode node) {
		this._firstTick = true;
		this._isEnd = false;
		this._target = node;
	}

	public abstract LNAction copy();

	public void start() {
		this.setTarget(this._target);
	}

	public void step(float dt) {
		if (!this._isPause) {
			if (this._firstTick) {
				this._firstTick = false;
				this._elapsed = 0f;
			} else {
				this._elapsed += dt;
			}
			float fx = 0;
			if (_easing != null) {
				fx = _easing.ease((this._elapsed / this._duration), 1f);
			} else {
				fx = MathUtils.min((this._elapsed / this._duration), 1f);
			}
			this.update(fx);
		}
	}

	public void reset() {
		this._elapsed = 0;
		this._isEnd = false;
	}

	public void stop() {
		this._isEnd = true;
	}

	public void update(float time) {
	}

	public final float getDuration() {
		return this._duration;
	}

	public final float getElapsed() {
		return this._elapsed;
	}

	public final boolean isEnd() {
		return this._isEnd;
	}

	public Easing getEasing() {
		return _easing;
	}

	public void setEasing(Easing e) {
		this._easing = e;
	}
}
