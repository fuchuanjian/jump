
package loon.action.sprite.node;

public class LNCallFuncND extends LNAction {
	
	protected Callback _c;

	protected Object _data;
	
	LNCallFuncND(){
		
	}

	public static LNCallFuncND Action(Callback c, Object data) {
		LNCallFuncND cnd = new LNCallFuncND();
		cnd._c = c;
		cnd._data = data;
		return cnd;
	}

	@Override
	public void step(float dt) {
		_c.invoke(super._target, this._data);
		super._isEnd = true;
	}

	public static interface Callback {
		void invoke(LNNode node, Object data);
	}

	@Override
	public LNAction copy() {
		return Action(_c, _data);
	}
}
