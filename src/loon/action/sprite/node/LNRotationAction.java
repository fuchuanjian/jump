
package loon.action.sprite.node;

public class LNRotationAction extends LNAction {

	LNRotationAction(){
		
	}
	
	protected float _rotation;

	public static LNRotationAction Action(float r) {
		LNRotationAction action = new LNRotationAction();
		action._rotation = r;
		return action;
	}

	@Override
	public void step(float dt) {
		super._target.setRotation(this._rotation);
		super._isEnd = true;
	}

	@Override
	public LNAction copy() {
		return Action(_rotation);
	}
}
