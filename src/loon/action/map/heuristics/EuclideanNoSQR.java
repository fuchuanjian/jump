
package loon.action.map.heuristics;

import loon.action.map.AStarFindHeuristic;
import loon.utils.MathUtils;


public class EuclideanNoSQR implements AStarFindHeuristic {

	@Override
	public float getScore(float sx, float sy, float tx, float ty) {
		return MathUtils.pow((sx - tx), 2) + MathUtils.pow((sy - ty), 2);
	}

	@Override
	public int getType() {
		return EUCLIDEAN_NOSQR;
	}

}
