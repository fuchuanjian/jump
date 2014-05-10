package loon.core.graphics.component;


public class CollisionPointQuery implements CollisionQuery {

	private float x;

	private float y;

	private Class<?> cls;

	public void init(float x, float y, Class<?> cls) {
		this.x = x;
		this.y = y;
		this.cls = cls;
	}

	@Override
	public boolean checkCollision(Actor actor) {
		return this.cls != null && !this.cls.isInstance(actor) ? false : actor
				.containsPoint(this.x, this.y);
	}
}
