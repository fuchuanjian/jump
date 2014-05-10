
package loon.action.sprite.node;

public class LNBlink extends LNAction {

	protected int _times;
	
	LNBlink(){
		
	}

	public static LNBlink Action(float duration, int times) {
		LNBlink blink = new LNBlink();
		blink._duration = duration;
		blink._times = times;
		return blink;
	}

	@Override
	public void update(float time) {
		float slice = 1.0f / _times;
		float m = time % slice;
		super._target._visible = m > slice / 2 ? true : false;
	}

	@Override
	public LNAction copy() {
		return Action(_duration, _times);
	}
}
