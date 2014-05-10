
package loon.action.map.heuristics;

import loon.action.map.AStarFindHeuristic;
import loon.utils.MathUtils;


public class DiagonalShort implements AStarFindHeuristic {

	@Override
	public float getScore(float sx, float sy, float tx, float ty) {
		float diagonal = MathUtils.min(MathUtils.abs(sx - tx),
				MathUtils.abs(sy - ty));
		float straight = (MathUtils.abs(sx - tx) + MathUtils.abs(sy - ty));
		return 2 * diagonal + (straight - 2 * diagonal);
	}

	@Override
	public int getType() {
		return DIAGONAL_SHORT;
	}

}
