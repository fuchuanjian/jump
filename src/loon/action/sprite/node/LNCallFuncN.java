
package loon.action.sprite.node;

public class LNCallFuncN extends LNAction
{
    protected Callback _c;
    
    LNCallFuncN(){
    	
    }

    public static LNCallFuncN Action(Callback c)
    {
        LNCallFuncN cn = new LNCallFuncN();
        cn._c = c;
        return cn;
    }

    @Override
	public void step(float dt)
    {
    	_c.invoke(super._target);
        super._isEnd = true;
    }

	public static interface Callback
	{
		void invoke(LNNode node);
	}

	@Override
	public LNAction copy() {
		return Action(_c);
	}
}
