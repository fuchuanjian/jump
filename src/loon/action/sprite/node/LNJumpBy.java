
package loon.action.sprite.node;

import loon.core.geom.Vector2f;
import loon.utils.MathUtils;

public class LNJumpBy extends LNAction {
	
	LNJumpBy(){
		
	}

	protected Vector2f _delta;

	protected float _height;

	protected int _jumps;

	protected Vector2f _orgPos;

	public static LNJumpBy Action(float duration, float d, float height,
			int jumps) {
		return Action(duration, new Vector2f(d, d), height, jumps);
	}

	public static LNJumpBy Action(float duration, Vector2f delta, float height,
			int jumps) {
		LNJumpBy by = new LNJumpBy();
		by._duration = duration;
		by._delta = delta;
		by._height = height;
		by._jumps = jumps;
		return by;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		this._orgPos = node.getPosition();
	}

	@Override
	public void update(float t) {
		if (t == 1f) {
			super._isEnd = true;
			super._target.setPosition(this._delta.x + this._orgPos.x,
					this._delta.y + this._orgPos.y);
		} else {
			float num = this._height
					* MathUtils.abs(MathUtils
							.sin(((t * 3.141593f) * this._jumps)));
			num += this._delta.y * t;
			float num2 = this._delta.x * t;
			super._target.setPosition(num2 + this._orgPos.x, num
					+ this._orgPos.y);
		}
	}

	@Override
	public LNAction copy() {
		return Action(_duration, _delta, _height, _jumps);
	}

	public LNJumpBy reverse() {
		return Action(_duration, _delta.negate(), _height, _jumps);
	}
}
