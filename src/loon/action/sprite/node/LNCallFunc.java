
package loon.action.sprite.node;

public class LNCallFunc extends LNAction {

	protected Callback _c;
	
	LNCallFunc(){
		
	}

	public static LNCallFunc Action(Callback c) {
		LNCallFunc func = new LNCallFunc();
		func._c = c;
		return func;
	}

	@Override
	public void step(float dt) {
		this._c.invoke();
		super._isEnd = true;
	}

	public static interface Callback {
		void invoke();
	}

	@Override
	public LNAction copy() {
		return Action(_c);
	}
}
