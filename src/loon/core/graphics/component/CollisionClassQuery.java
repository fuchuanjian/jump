package loon.core.graphics.component;

public class CollisionClassQuery implements CollisionQuery {

	private Class<?> cls;

	private CollisionQuery subQuery;

	public CollisionClassQuery(Class<?> cls, CollisionQuery subQuery) {
		this.cls = cls;
		this.subQuery = subQuery;
	}

	@Override
	public boolean checkCollision(Actor actor) {
		return this.cls.isInstance(actor) ? this.subQuery.checkCollision(actor)
				: false;
	}
}
