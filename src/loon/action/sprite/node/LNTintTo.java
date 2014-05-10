
package loon.action.sprite.node;

import loon.core.graphics.LColor;

public class LNTintTo extends LNAction {

	LNTintTo(){
		
	}
	
	protected LColor _to;

	protected LColor _from;

	public static LNTintTo Action(float t, LColor c) {
		LNTintTo tint = new LNTintTo();
		tint._duration = t;
		tint._to = c;
		return tint;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		_from = node.getColor();
	}

	@Override
	public void update(float t) {
		final int fred = _from.getRed();
		final int fgreen = _from.getGreen();
		final int fblue = _from.getBlue();
		final int tred = _to.getRed();
		final int tgreen = _to.getGreen();
		final int tblue = _to.getBlue();
		final int r = (int) (fred + (tred - fred) * t);
		final int g = (int) (fgreen + (tgreen - fgreen) * t);
		final int b = (int) (fblue + (tblue - fblue) * t);
		if (r == tred && g == tgreen && b == tblue) {
			super._isEnd = true;
		} else {
			super._target.setColor(r, g, b);
		}
	}

	@Override
	public LNAction copy() {
		return Action(_duration, _to);
	}
}
