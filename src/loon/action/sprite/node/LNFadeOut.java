
package loon.action.sprite.node;

public class LNFadeOut extends LNAction {

	protected float _diff;

	protected float _orgOpacity;

	protected float _tarOpacity;
	
	LNFadeOut(){
		
	}

	public static LNFadeOut Action(float duartion) {
		LNFadeOut outs = new LNFadeOut();
		outs._tarOpacity = 0f;
		outs._duration = duartion;
		return outs;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
		this._orgOpacity = node._alpha * 255f;
		this._diff = this._tarOpacity - this._orgOpacity;
	}

	@Override
	public void update(float t) {
		if (t == 1f) {
			super._isEnd = true;
			super._target.setAlpha(1f);
		} else {
			super._target
					.setAlpha(((t * this._diff) + this._orgOpacity) / 255f);
		}
	}

	@Override
	public LNAction copy() {
		return Action(_duration);
	}

	public LNFadeIn reverse() {
		return LNFadeIn.Action(_duration);
	}
}
