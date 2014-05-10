
package loon.physics;

public abstract class PJoint
{

    boolean rem;
    PJointType type;
    
    public PJoint()
    {
        type = PJointType.NULL_JOINT;
    }

    public PJointType getJointType()
    {
        return type;
    }

    abstract void preSolve(float f);

    public void remove()
    {
        rem = true;
    }

    abstract void solvePosition();

    abstract void solveVelocity(float f);

    abstract void update();

}
