
package loon.action.sprite.node;

import loon.core.graphics.LColor;

public class LNTintBy extends LNAction {

	LNTintBy(){
		
	}
	
	protected LColor _delta;

	protected LColor _from;

	public static LNTintBy Action(float t, LColor c) {
		LNTintBy tint = new LNTintBy();
		tint._duration = t;
		tint._delta = c;
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
		final int dred = _delta.getRed();
		final int dgreen = _delta.getGreen();
		final int dblue = _delta.getBlue();
		final int r = (int) (fred + dred * t);
		final int g = (int) (fgreen + dgreen * t);
		final int b = (int) (fblue + dblue * t);
		super._target.setColor(r, g, b);
	}

	@Override
	public LNAction copy() {
		return Action(_duration, _delta);
	}

	public LNTintBy reverse() {
		return Action(_duration, new LColor(-_delta.r, -_delta.g, -_delta.b));
	}
}
