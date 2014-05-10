
package loon.physics;

public class PPolygonizer {

	int numPolygons;
	int numTriangles;
	PPolygon[] polygons;
	PPolygon[] triangles;

	public PPolygonizer() {
	}

	private PPolygon addTriangleToPolygon(PPolygon base, PPolygon triangle) {
		if (triangle.numVertices != 3)
			return null;
		int connectIndex = -1;
		int triangle2;
		int triangle1 = triangle2 = -1;
		for (int i = 0; i < base.numVertices; i++) {
			for (int j = 0; j < 3; j++) {
				if (base.xs[i] != triangle.xs[j]
						|| base.ys[i] != triangle.ys[j]) {
					continue;
				}
				if (connectIndex == -1 || connectIndex == 0
						&& i == base.numVertices - 1)
					connectIndex = i;
				if (triangle1 == -1) {
					triangle1 = j;
				} else {
					triangle2 = j;
				}
				break;
			}

		}

		if (triangle2 == -1) {
			return null;
		}
		int shouldAddIndex = 0;
		if (triangle1 == shouldAddIndex || triangle2 == shouldAddIndex) {
			shouldAddIndex = 1;
		}
		if (triangle1 == shouldAddIndex || triangle2 == shouldAddIndex) {
			shouldAddIndex = 2;
		}
		float xs[] = new float[base.numVertices + 1];
		float ys[] = new float[base.numVertices + 1];
		int count = 0;
		for (int i = 0; i < base.numVertices; i++) {
			xs[count] = base.xs[i];
			ys[count] = base.ys[i];
			count++;
			if (i == connectIndex) {
				xs[count] = triangle.xs[shouldAddIndex];
				ys[count] = triangle.ys[shouldAddIndex];
				count++;
			}
		}

		return new PPolygon(xs, ys);
	}

	private void polygonize() {
		do {
			PPolygon base = null;
			for (int i = 0; i < numTriangles; i++) {
				if (triangles[i].polygonized){
					continue;
				}
				base = triangles[i];
				base.polygonized = true;
				break;
			}

			if (base != null) {
				for (int i = 0; i < numTriangles; i++)
					if (!triangles[i].polygonized) {
						PPolygon next = addTriangleToPolygon(base, triangles[i]);
						if (next != null && next.isConvex()) {
							triangles[i].polygonized = true;
							base = next;
						}
					}

				polygons[numPolygons++] = base;
			} else {
				return;
			}
		} while (true);
	}

	public void polygonize(PPolygon[] triangles, int numTriangles) {
		this.triangles = triangles;
		this.numTriangles = numTriangles;
		polygons = new PPolygon[triangles.length];
		polygonize();
	}

}
