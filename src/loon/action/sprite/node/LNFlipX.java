
package loon.action.sprite.node;

public class LNFlipX extends LNAction {

	protected boolean _flipX;
	
	LNFlipX(){
		
	}

	public static LNFlipX Action(boolean fx) {
		LNFlipX flipx = new LNFlipX();
		flipx._flipX = fx;
		return flipx;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		if (super._target instanceof LNSprite) {
			((LNSprite) super._target).setFlipX(_flipX);
		}
	}

	@Override
	public void update(float t) {
		super._isEnd = true;
		if (super._target instanceof LNSprite) {
			((LNSprite) super._target).setFlipX(_flipX);
		}
	}

	@Override
	public LNAction copy() {
		return Action(_flipX);
	}

	public LNFlipX reverse() {
		return LNFlipX.Action(!_flipX);
	}
}
