
package loon.physics;

public class PPolygonDrawer {

	private final int maxVertices = 1024;
	public int numVertices;
	public float[] xs;
	public float[] ys;

	public PPolygonDrawer() {
		this(1024);
	}

	public PPolygonDrawer(int size) {
		xs = new float[size];
		ys = new float[size];
	}

	public void reset() {
		numVertices = 0;
	}

	public void addVertex(float wx, float wy) {
		if (numVertices == maxVertices) {
			return;
		} else {
			xs[numVertices] = wx;
			ys[numVertices] = wy;
			numVertices++;
			return;
		}
	}

}
