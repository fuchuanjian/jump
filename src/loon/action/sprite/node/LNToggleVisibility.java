
package loon.action.sprite.node;

public class LNToggleVisibility extends LNAction {

	LNToggleVisibility(){
		
	}
	
	public static LNToggleVisibility Action() {
		return new LNToggleVisibility();
	}

	@Override
	public void step(float dt) {
		boolean visible = super._target._visible;
		super._target._visible = !visible;
		super._isEnd = true;
	}

	@Override
	public LNAction copy() {
		return Action();
	}

}
