
package loon.action.sprite.node;

import loon.core.geom.Vector2f;

public class LNOffset extends LNAction {
	
	LNOffset(){
		
	}

	protected Vector2f _offset;

	public static LNOffset Action(float sx, float sy) {
		LNOffset off = new LNOffset();
		off._offset = new Vector2f(sx, sy);
		return off;
	}

	public static LNOffset Action(Vector2f v) {
		LNOffset off = new LNOffset();
		off._offset = v;
		return off;
	}

	@Override
	public void step(float t) {
		super._target.setOffset(_offset);
	}

	@Override
	public void update(float time) {
		if (time == 1f) {
			super.reset();
		}
	}

	@Override
	public LNAction copy() {
		return Action(_offset);
	}

	public LNAction reverse() {
		return Action(0, 0);
	}
}
