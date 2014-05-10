
package loon.action.sprite.node;

public class LNEase extends LNAction {

	protected LNAction _action;

	LNEase() {

	}

	
	public static LNEase Action(Easing e, LNAction act) {
		LNEase action = new LNEase();
		action._duration = act._duration;
		action._action = act;
		act._easing = e;
		return action;
	}

	@Override
	public void setTarget(LNNode node) {
		super.setTarget(node);
		if (_action != null) {
			_action.setTarget(node);
		}
	}

	@Override
	public void step(float dt) {
		if (_action != null) {
			_action.step(dt);
			_isEnd = _action.isEnd();
		}
	}

	@Override
	public LNAction copy() {
		return Action(_easing, _action);
	}

}
