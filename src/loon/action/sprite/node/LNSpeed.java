
package loon.action.sprite.node;

public class LNSpeed extends LNAction {

	LNSpeed(){
		
	}
	
	protected LNAction _other;

	protected float _speed;

	public static LNSpeed Action(LNAction action, float s) {
		LNSpeed speed = new LNSpeed();
		speed._other = action;
		speed._speed = s;
		return speed;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		_other.setTarget(node);
	}

	@Override
	public void step(float dt) {
		super.step(dt);
		_other.step(dt * _speed);
	}

	@Override
	public void update(float t) {
		_other.update(t);
		if (_other._isEnd) {
			super._isEnd = true;
		}
	}

	public float getSpeed() {
		return _speed;
	}

	public void setSpeed(float speed) {
		this._speed = speed;
	}

	@Override
	public LNAction copy() {
		return Action(_other, _speed);
	}

}
