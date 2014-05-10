
package loon.action.sprite.node;

public class LNShow extends LNAction {

	LNShow(){
		
	}
	
	public static LNShow Action() {
		return new LNShow();
	}

	@Override
	public void step(float dt) {
		super._target._visible = true;
		super._isEnd = true;
	}

	@Override
	public LNAction copy() {
		return Action();
	}

	public LNHide reverse() {
		return LNHide.Action();
	}
}
