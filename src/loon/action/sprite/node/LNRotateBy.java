
package loon.action.sprite.node;

public class LNRotateBy extends LNRotateTo
{
	LNRotateBy(){
		
	}
	
    public static LNRotateBy Action(float duration, float angle)
    {
    	LNRotateBy by = new LNRotateBy();
        by._diff = angle;
        by._duration = duration;
        return by;
    }

    @Override
	public void setTarget(LNNode node)
    {
        super._firstTick = true;
        super._isEnd = false;
        super._target = node;
        super._orgAngle = node.getRotation();
        super._tarAngle = super._diff + super._orgAngle;
    }
}
