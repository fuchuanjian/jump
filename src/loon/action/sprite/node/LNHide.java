
package loon.action.sprite.node;

public class LNHide extends LNAction {
	
	LNHide(){
		
	}

	public static LNHide Action() {
		return new LNHide();
	}

	@Override
	public void step(float dt) {
		super._target._visible = false;
		super._isEnd = true;
	}

	@Override
	public LNAction copy() {
		return Action();
	}

	public LNShow reverse() {
		return LNShow.Action();
	}

}
