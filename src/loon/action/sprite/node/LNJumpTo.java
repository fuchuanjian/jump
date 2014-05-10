
package loon.action.sprite.node;

import loon.core.geom.Vector2f;

public class LNJumpTo extends LNJumpBy {
	
	LNJumpTo(){
		
	}

	public static LNJumpTo Action(float duration, Vector2f delta,
			float height, int jumps) {
		LNJumpTo to = new LNJumpTo();
		to._duration = duration;
		to._delta = delta;
		to._height = height;
		to._jumps = jumps;
		return to;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		super._orgPos = node.getPosition();
		super._delta.set(this._delta.x - this._orgPos.x, this._delta.y
				- this._orgPos.y);
	}
}
