package loon.core.graphics.component;

public class CollisionBaseQuery implements CollisionQuery {

	private Class<?> cls;

	private Actor compareObject;

	public void init(Class<?> cls, Actor actor) {
		this.cls = cls;
		this.compareObject = actor;
	}

	public boolean checkOnlyCollision(Actor other) {
		return (this.compareObject == null ? true : other
				.intersects(this.compareObject));
	}

	@Override
	public boolean checkCollision(Actor other) {
		return this.cls != null && !this.cls.isInstance(other) ? false
				: (this.compareObject == null ? true : other
						.intersects(this.compareObject));
	}
}
