
package loon.action.sprite.node;

public class LNFrameAction extends LNAction {
	
	protected String _animName;

	protected LNFrameStruct _fs;

	protected int _index;
	
	LNFrameAction(){
		
	}

	public static LNFrameAction Action(String aName, int idx) {
		LNFrameAction action = new LNFrameAction();
		action._animName = aName;
		action._index = idx;
		return action;
	}

	public static LNFrameAction Action(LNFrameStruct fs) {
		LNFrameAction action = new LNFrameAction();
		action._fs = fs;
		return action;
	}

	@Override
	public void step(float dt) {
		if (this._fs == null) {
			((LNSprite) super._target).setFrame(this._animName, this._index);
		} else {
			((LNSprite) super._target).initWithFrameStruct(_fs);
		}
		super._isEnd = true;
	}

	@Override
	public LNAction copy() {
		return Action(_fs);
	}
}
