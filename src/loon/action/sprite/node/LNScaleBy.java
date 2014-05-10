
package loon.action.sprite.node;

public class LNScaleBy extends LNAction {
	
	LNScaleBy(){
		
	}
	
	protected float _scaleX;
	
	protected float _scaleY;

	public static LNScaleBy Action(float scale) {
		LNScaleBy action = new LNScaleBy();
		action._scaleX = scale;
		action._scaleY = scale;
		return action;
	}

	public static LNScaleBy Action(float sX, float sY) {
		LNScaleBy action = new LNScaleBy();
		action._scaleX = sX;
		action._scaleY = sY;
		return action;
	}

	@Override
	public void step(float dt) {
		super._target.setScale(this._scaleX, this._scaleY);
		super._isEnd = true;
	}

	@Override
	public LNAction copy() {
		return Action(_scaleX, _scaleY);
	}
}
