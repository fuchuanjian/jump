
package loon.physics;


public interface PCollider
{
    public abstract int collide(PShape shape, PShape shape1, PContact acontact[]);
}
