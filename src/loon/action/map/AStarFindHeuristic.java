
package loon.action.map;

public interface AStarFindHeuristic {

	public final static int MANHATTAN = 0;

	public final static int MIXING = 1;

	public final static int DIAGONAL = 2;

	public final static int DIAGONAL_SHORT = 3;

	public final static int EUCLIDEAN = 4;

	public final static int EUCLIDEAN_NOSQR = 5;

	public final static int CLOSEST = 6;

	public final static int CLOSEST_SQUARED = 7;

	public float getScore(float sx, float sy, float tx, float ty);

	public int getType();

}
