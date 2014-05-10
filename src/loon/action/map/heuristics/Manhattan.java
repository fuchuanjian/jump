
package loon.action.map.heuristics;

import loon.action.map.AStarFindHeuristic;
import loon.utils.MathUtils;


public class Manhattan implements AStarFindHeuristic {

	@Override
	public float getScore(float sx, float sy, float tx, float ty) {
		return (MathUtils.abs(sx - tx) + MathUtils.abs(sy - ty));
	}

	@Override
	public int getType() {
		return MANHATTAN;
	}

}
