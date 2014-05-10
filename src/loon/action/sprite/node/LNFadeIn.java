
package loon.action.sprite.node;

public class LNFadeIn extends LNAction {

	protected float _diff;

	protected float _orgOpacity;

	protected float _tarOpacity;
	
	LNFadeIn(){
		
	}

	public static LNFadeIn Action(float duration) {
		LNFadeIn ins = new LNFadeIn();
		ins._tarOpacity = 255f;
		ins._duration = duration;
		return ins;
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

	public LNFadeOut reverse() {
		return LNFadeOut.Action(_duration);
	}
}
