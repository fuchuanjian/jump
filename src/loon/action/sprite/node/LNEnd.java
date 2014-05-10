
package loon.action.sprite.node;

public class LNEnd extends LNAction {

	LNEnd() {

	}

	public static LNEnd Action() {
		LNEnd action = new LNEnd();
		return action;
	}

	@Override
	public void step(float dt) {
		super._isEnd = true;
		_target.stopAllAction();
	}

	@Override
	public LNAction copy() {
		return Action();
	}

	public LNAction reverse() {
		return Action();
	}
}
