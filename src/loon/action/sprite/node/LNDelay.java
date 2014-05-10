
package loon.action.sprite.node;

public class LNDelay extends LNAction
{
	
	LNDelay(){
		
	}
	
    public static LNDelay Action(float duration)
    {
        LNDelay delay = new LNDelay();
        delay._duration = duration;
        return delay;
    }

    @Override
	public void update(float t)
    {
        if (t == 1f)
        {
            super._isEnd = true;
        }
    }

	@Override
	public LNAction copy() {
		return Action(_duration);
	}
}
