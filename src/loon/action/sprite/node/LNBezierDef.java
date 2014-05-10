
package loon.action.sprite.node;

import loon.core.geom.Vector2f;
import loon.utils.MathUtils;

public class LNBezierDef {

	public Vector2f endPosition;

	public Vector2f controlPoint_1;

	public Vector2f controlPoint_2;
	

	public LNBezierDef() {

	}

	public static float bezierAt(float a, float b, float c, float d, float t) {
		return (MathUtils.pow(1 - t, 3) * a + 3 * t * (MathUtils.pow(1 - t, 2))
				* b + 3 * MathUtils.pow(t, 2) * (1 - t) * c + MathUtils.pow(t,
				3) * d);
	}

}
