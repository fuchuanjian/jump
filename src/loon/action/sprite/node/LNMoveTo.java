
package loon.action.sprite.node;

import loon.core.geom.Vector2f;

public class LNMoveTo extends LNAction {

	protected Vector2f _diff;

	protected Vector2f _orgPos;

	protected Vector2f _pos;

	LNMoveTo() {

	}

	public static LNMoveTo Action(float duration, Vector2f pos) {
		LNMoveTo to = new LNMoveTo();
		to._pos = pos;
		to._duration = duration;
		return to;
	}

	public static LNMoveTo Action(float duration, float dx, float dy) {
		return LNMoveTo.Action(duration, new Vector2f(dx, dy));
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		this._orgPos = node.getPosition();
		this._diff = this._pos.sub(this._orgPos);
	}

	@Override
	public void update(float t) {
		if (t == 1f) {
			super._isEnd = true;
			super._target.setPosition(this._pos);
		} else {
			super._target.setPosition(this._diff.mul(t).add(this._orgPos));
		}
	}

	@Override
	public LNAction copy() {
		return Action(_duration, _pos);
	}

	public LNAction reverse() {
		return Action(_duration, -_pos.x, -_pos.y);
	}
}
