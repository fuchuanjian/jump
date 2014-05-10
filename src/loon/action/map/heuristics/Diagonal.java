
package loon.action.map.heuristics;

import loon.action.map.AStarFindHeuristic;
import loon.utils.MathUtils;


public class Diagonal implements AStarFindHeuristic {

	@Override
	public float getScore(float sx, float sy, float tx, float ty) {
		float dx = MathUtils.abs(tx - sx);
		float dy = MathUtils.abs(ty - sy);
		float dz = MathUtils.max(dx, dy);
		return dz;
	}

	@Override
	public int getType() {
		return DIAGONAL;
	}

}
