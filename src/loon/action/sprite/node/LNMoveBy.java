
package loon.action.sprite.node;

import loon.core.geom.Vector2f;

public class LNMoveBy extends LNMoveTo {

	protected float _lastTime;

	LNMoveBy() {

	}

	public static LNMoveBy Action(float duration, Vector2f pos) {
		LNMoveBy by = new LNMoveBy();
		by._diff = pos;
		by._duration = duration;
		by._lastTime = 0f;
		return by;
	}

	public static LNMoveBy Action(float duration, float dx, float dy) {
		return LNMoveBy.Action(duration, new Vector2f(dx, dy));
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		super._orgPos = node.getPosition();
		super._pos = super._orgPos.add(super._diff);
	}

	@Override
	public void update(float t) {
		if (t == 1f) {
			super._isEnd = true;
			super._target.setPosition(super._pos);
		} else {
			Vector2f position = super._target.getPosition();
			super._target.setPosition(super._diff.mul((t - this._lastTime))
					.add(position));
			this._lastTime = t;
		}
	}

	@Override
	public LNMoveBy reverse() {
		return Action(_duration, _diff.negate());
	}
}
