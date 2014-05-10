
package loon.action.sprite.node;

public class LNRepeatForever extends LNAction
{
	LNRepeatForever(){
		
	}
	
    protected LNAction _action;

    public static LNRepeatForever Action(LNAction action)
    {
    	LNRepeatForever forever = new LNRepeatForever();
        forever._action = action;
        return forever;
    }

    @Override
	public void setTarget(LNNode node)
    {
        super._firstTick = true;
        super._isEnd = false;
        super._target = node;
        this._action.setTarget(super._target);
    }

    @Override
	public void step(float dt)
    {
        if (super._firstTick)
        {
        	super._firstTick = false;
        	super._elapsed = 0f;
        }
        else
        {
        	super._elapsed += dt;
        }
        this._action.step(dt);
        if (this._action.isEnd())
        {
            this._action.start();
            super._elapsed = 0f;
        }
    }

	@Override
	public LNAction copy() {
		return Action(_action);
	}
}
