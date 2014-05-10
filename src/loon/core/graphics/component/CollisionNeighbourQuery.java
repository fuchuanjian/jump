package loon.core.graphics.component;

import loon.utils.MathUtils;


public class CollisionNeighbourQuery implements CollisionQuery {

	private float x;

	private float y;

	private float distance;

	private boolean diag;

	private Class<?> cls;

	public void init(float x, float y, float distance, boolean diag, Class<?> cls) {
		this.x = x;
		this.y = y;
		this.distance = distance;
		this.diag = diag;
		this.cls = cls;
	}

	@Override
	public boolean checkCollision(Actor actor) {
		if (this.cls != null && !this.cls.isInstance(actor)) {
			return false;
		} else {
			float actorX = actor.getX();
			float actorY = actor.getY();
			if (actorX == this.x && actorY == this.y) {
				return false;
			} else {
				float ax = actor.getX();
				float ay = actor.getY();
				float dx;
				float dy;
				if (!this.diag) {
					dx = MathUtils.abs(ax - this.x);
					dy = MathUtils.abs(ay - this.y);
					return dx + dy <= this.distance;
				} else {
					dx = this.x - this.distance;
					dy = this.y - this.distance;
					float x2 = this.x + this.distance;
					float y2 = this.y + this.distance;
					return ax >= dx && ay >= dy && ax <= x2 && ay <= y2;
				}
			}
		}
	}
}
