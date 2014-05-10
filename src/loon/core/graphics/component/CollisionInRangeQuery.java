package loon.core.graphics.component;

import loon.core.geom.RectBox;
import loon.utils.MathUtils;



public class CollisionInRangeQuery implements CollisionQuery {

	private float dx;

	private float dy;

	private float dist;

	private float x;

	private float y;

	private float r;

	private RectBox object;

	public void init(float x, float y, float r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	@Override
	public boolean checkCollision(Actor actor) {

		object = actor.getRectBox();

		dx = MathUtils.abs(object.getCenterX() - x);
		dy = MathUtils.abs(object.getCenterY() - y);

		dist = MathUtils.sqrt(dx * dx + dy * dy);

		return dist <= this.r;
	}
}
