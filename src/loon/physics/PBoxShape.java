
package loon.physics;


public class PBoxShape extends PConvexPolygonShape {

	private float width, height;

	public PBoxShape(float px, float py, float width, float height,
			float angle, float density) {
		super(new float[] { px - width * 0.5F, px + width * 0.5F,
				px + width * 0.5F, px - width * 0.5F }, new float[] {
				py - height * 0.5F, py - height * 0.5F, py + height * 0.5F,
				py + height * 0.5F }, density);
		this._type = PShapeType.BOX_SHAPE;
		this._localAng = angle;
		this.width = width;
		this.height = height;

	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
