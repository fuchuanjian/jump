
package loon.action.map.heuristics;

import loon.action.map.AStarFindHeuristic;

public class ClosestSquared implements AStarFindHeuristic {

	@Override
	public float getScore(float sx, float sy, float tx, float ty) {
		float dx = tx - sx;
		float dy = ty - sy;
		return ((dx*dx)+(dy*dy));
	}

	@Override
	public int getType() {
		return CLOSEST_SQUARED;
	}

}
