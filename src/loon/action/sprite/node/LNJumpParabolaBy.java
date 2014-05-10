
package loon.action.sprite.node;

import loon.core.geom.Vector2f;

public class LNJumpParabolaBy extends LNAction {
	
	LNJumpParabolaBy(){
		
	}
	
	public float _a;
	
	public float _b;
	
	public float _c;
	
	public Vector2f _delta;
	
	public float _height;
	
	public Vector2f _refPoint;
	
	public Vector2f _startPosition;

	public static LNJumpParabolaBy Action(float duration,
			Vector2f position, Vector2f refPoint) {
		LNJumpParabolaBy by = new LNJumpParabolaBy();
		by._delta = position;
		by._duration = duration;
		by._refPoint = refPoint;
		return by;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		this._startPosition = super._target._position;
		this._a = ((this._refPoint.y / this._refPoint.x) - (this._delta.y / this._delta.x))
				/ (this._refPoint.x - this._delta.x);
		this._b = (this._delta.y / this._delta.x)
				- ((this._delta.x + (2f * this._startPosition.x)) * this._a);
		this._c = (this._startPosition.y - ((this._a * this._startPosition.x) * this._startPosition.x))
				- (this._b * this._startPosition.x);
	}

	@Override
	public void update(float t) {
		float num = (t * this._delta.x) + this._startPosition.x;
		float x = num;
		float y = (((this._a * x) * x) + (this._b * x)) + this._c;
		super._target.setPosition(x, y);
	}

	@Override
	public LNAction copy() {
		return Action(_duration, _delta, _refPoint);
	}
	
	public LNAction reverse() {
		return Action(_duration, _delta.negate(), _refPoint);
	}
}
