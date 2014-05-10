
package loon.action.map.heuristics;

import loon.action.map.AStarFindHeuristic;
import loon.utils.MathUtils;


public class Mixing implements AStarFindHeuristic {

	@Override
	public float getScore(float sx, float sy, float tx, float ty) {
		float nx = MathUtils.abs(tx - sx);
		float ny = MathUtils.abs(ty - sy);
		float orthogonal = MathUtils.abs(nx - ny);
		float diagonal = MathUtils.abs(((nx + ny) - orthogonal) / 2);
		return diagonal + orthogonal + nx + ny;
	}

	@Override
	public int getType() {
		return MIXING;
	}

}
