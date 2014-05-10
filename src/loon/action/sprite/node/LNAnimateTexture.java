
package loon.action.sprite.node;

public class LNAnimateTexture extends LNAction {

	public LNAnimationTexture _ans;

	public String _animName;

	public boolean _restoreOriginalFrame;
	
	LNAnimateTexture(){
		
	}

	public static LNAnimateTexture Action(String fileName, int width, int height) {
		return Action(new LNAnimationTexture(fileName, width, height));
	}

	public static LNAnimateTexture Action(String fileName, int maxFrame,
			int width, int height) {
		return Action(new LNAnimationTexture(fileName, maxFrame, width, height));
	}

	public static LNAnimateTexture Action(String fileName, int maxFrame,
			int width, int height, float duration) {
		return Action(new LNAnimationTexture(fileName, maxFrame, width, height,
				duration));
	}

	public static LNAnimateTexture Action(String aName, String fileName,
			int maxFrame, int width, int height, float duration) {
		return Action(new LNAnimationTexture(aName, fileName, maxFrame, width,
				height, duration));
	}

	public static LNAnimateTexture Action(LNAnimationTexture anim) {
		LNAnimateTexture animate = new LNAnimateTexture();
		animate._ans = anim;
		animate._duration = anim.getDuration();
		animate._animName = anim.getName();
		animate._restoreOriginalFrame = true;
		return animate;
	}

	public static LNAnimateTexture Action(LNAnimationTexture anim,
			boolean restoreOriginalFrame) {
		LNAnimateTexture animate = new LNAnimateTexture();
		animate._ans = anim;
		animate._duration = anim.getDuration();
		animate._animName = anim.getName();
		animate._restoreOriginalFrame = restoreOriginalFrame;
		return animate;
	}

	@Override
	public void setTarget(LNNode node) {
		super._firstTick = true;
		super._isEnd = false;
		super._target = node;
	}

	@Override
	public void update(float t) {
		if (super._target instanceof LNSprite) {
			if (t == 1f) {
				super.reset();
				if (this._restoreOriginalFrame) {
					((LNSprite) super._target)
							.initWithTexture(_ans.getFrame(0));
				}
			} else {
				((LNSprite) super._target).initWithTexture(_ans
						.getFrameByTime(t));
			}
		}
	}

	@Override
	public LNAction copy() {
		return Action(_ans, _restoreOriginalFrame);
	}
}
