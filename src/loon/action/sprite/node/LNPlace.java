
package loon.action.sprite.node;

import loon.core.geom.Vector2f;

public class LNPlace extends LNAction
{
	LNPlace(){
		
	}
	
    protected Vector2f _pos;

    public static LNPlace Action(Vector2f pos)
    {
    	LNPlace place = new LNPlace();
        place._pos = pos;
        return place;
    }

    @Override
	public void step(float dt)
    {
        super._target.setPosition(this._pos);
        super._isEnd = true;
    }

	@Override
	public LNAction copy() {
		return Action(_pos);
	}
}
