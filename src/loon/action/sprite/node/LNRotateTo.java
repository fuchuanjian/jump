
package loon.action.sprite.node;

public class LNRotateTo extends LNAction {

	LNRotateTo(){
		
	}
	
	protected float _diff;

	protected float _orgAngle;

	protected float _tarAngle;

	public static LNRotateTo Action(float duration, float angle) {
		LNRotateTo to = new LNRotateTo();
		to._tarAngle = angle;
		to._duration = duration;
		return to;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		this._orgAngle = node.getRotation();
		this._diff = this._tarAngle - this._orgAngle;
	}

	@Override
	public void update(float t) {
		if (t == 1f) {
			super._isEnd = true;
			super._target.setRotation(this._tarAngle);
		} else {
			super._target.setRotation((t * this._diff) + this._orgAngle);
		}
	}

	@Override
	public LNAction copy() {
		return Action(_duration, _tarAngle);
	}
}
