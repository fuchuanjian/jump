
package loon.action.sprite.node;

public class LNRepeat extends LNAction {
	
	LNRepeat(){
		
	}
	
	protected LNAction _action;

	private int time;

	public static LNRepeat Action(LNAction action, int t) {
		LNRepeat repeat = new LNRepeat();
		repeat.time = t;
		repeat._action = action;
		repeat._duration = t * action.getDuration();
		return repeat;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		this._action.setTarget(super._target);
	}

	@Override
	public void step(float dt) {
		if (super._firstTick) {
			super._firstTick = false;
			super._elapsed = 0f;
		} else {
			super._elapsed += dt;
		}
		this._action.step(dt);
		if (this._action.isEnd()) {
			this._action.start();
		}
		if (super._elapsed > super._duration) {
			super._isEnd = true;
		}
	}

	@Override
	public LNAction copy() {
		return Action(_action, time);
	}
}
