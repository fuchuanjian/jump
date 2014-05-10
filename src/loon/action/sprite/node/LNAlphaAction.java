
package loon.action.sprite.node;

public class LNAlphaAction extends LNAction {

	protected float _alpha;

	private float oldAlpha;
	
	LNAlphaAction(){
		
	}

	public static LNAlphaAction Action(float a) {
		LNAlphaAction action = new LNAlphaAction();
		action._alpha = a;
		return action;
	}

	@Override
	public void step(float dt) {
		super._target.setAlpha(this._alpha);
		super._isEnd = true;
		oldAlpha = _target._alpha;
	}

	@Override
	public LNAction copy() {
		return Action(_alpha);
	}

	public LNAction reverse() {
		return Action(oldAlpha);
	}
}
