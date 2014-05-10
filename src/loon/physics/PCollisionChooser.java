
package loon.physics;

public class PCollisionChooser implements PCollider {

	@Override
	public int collide(PShape s1, PShape s2, PContact[] cs) {
		PCollider collider = null;
		boolean flip = false;
		switch (s1._type) {
		case BOX_SHAPE:
		case CONVEX_SHAPE:
			switch (s2._type) {
			case BOX_SHAPE:
			case CONVEX_SHAPE:
				collider = new PPolygonPolygonCollider();
				break;
			case CIRCLE_SHAPE:
				collider = new PCirclePolygonCollider();
				flip = true;
			case CONCAVE_SHAPE:
			default:
				break;
			}
			break;
		case CIRCLE_SHAPE:
			switch (s2._type) {
			case BOX_SHAPE:
			case CONVEX_SHAPE:
				collider = new PCirclePolygonCollider();
				break;
			case CIRCLE_SHAPE:
				collider = new PCircleCirlceCollider();
			case CONCAVE_SHAPE:
			default:
				break;
			}
			break;
		case CONCAVE_SHAPE:
		default:
			break;
		}
		if (collider == null) {
			return 0;
		}
		if (flip) {
			int res = collider.collide(s2, s1, cs);
			if (res > 0) {
				for (int i = 0; i < res; i++) {
					cs[i].normal.negateLocal();
				}
			}
			return res;
		}
		return collider.collide(s1, s2, cs);
	}
}