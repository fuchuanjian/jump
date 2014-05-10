
package loon.physics;

import loon.core.geom.Vector2f;

public class PVertexLoop {

	public boolean crossPoint;
	public Vector2f epsilon;
	public PVertexLoop next;
	public PVertexLoop pair;
	public PVertexLoop prev;
	public Vector2f v;

	public PVertexLoop(float x, float y) {
		this.v = new Vector2f(x, y);
	}

	public PVertexLoop(Vector2f v1) {
		this.v = v1;
	}

	PVertexLoop() {
		this.v = new Vector2f();
	}
}
