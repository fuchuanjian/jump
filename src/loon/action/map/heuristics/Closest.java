
package loon.action.map.heuristics;

import loon.action.map.AStarFindHeuristic;
import loon.utils.MathUtils;


public class Closest implements AStarFindHeuristic {

	@Override
	public float getScore(float sx, float sy, float tx, float ty) {
		float dx = tx - sx;
		float dy = ty - sy;
		float result = MathUtils.sqrt((dx * dx) + (dy * dy));
		return result;
	}

	@Override
	public int getType() {
		return CLOSEST;
	}

}
