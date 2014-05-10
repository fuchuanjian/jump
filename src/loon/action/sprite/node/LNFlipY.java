
package loon.action.sprite.node;

public class LNFlipY extends LNAction {

	protected boolean _flipY;
	
	LNFlipY(){
		
	}

	public static LNFlipY Action(boolean fy) {
		LNFlipY flipy = new LNFlipY();
		flipy._flipY = fy;
		return flipy;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		if (super._target instanceof LNSprite) {
			((LNSprite) super._target).setFlipY(_flipY);
		}
	}

	@Override
	public void update(float t) {
		super._isEnd = true;
		if (super._target instanceof LNSprite) {
			((LNSprite) super._target).setFlipY(_flipY);
		}
	}

	@Override
	public LNAction copy() {
		return Action(_flipY);
	}
	
	public LNFlipY reverse() {
		return LNFlipY.Action(!_flipY);
	}
}
