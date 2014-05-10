
package loon.physics;

import loon.core.geom.Circle;

public class PCircleShape extends PShape {

	float rad;
	private Circle mcircle;

	public PCircleShape(float px, float py, float radius, float angle,
			float density) {
		_dens = density;
		_type = PShapeType.CIRCLE_SHAPE;
		_localPos.set(px, py);
		rad = radius;
		_localAng = angle;
		mm = rad * rad * 3.141593F * _dens;
		ii = (mm * rad * rad * 2.0F) / 5F;
		this.mcircle = new Circle(px, py, radius);
	}

	@Override
	void calcAABB() {
		if (_parent == null) {
			return;
		} else {
			_aabb.set(_pos.x - rad, _pos.y - rad, _pos.x + rad, _pos.y + rad);
			return;
		}
	}

	public float getRadius() {
		return rad;
	}

	@Override
	void update() {
	}

	public Circle getCircle() {
		return mcircle;
	}

}
