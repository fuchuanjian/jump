package loon.action.map;

import java.util.LinkedList;

import loon.core.geom.Vector2f;



public interface AStarFinderListener {

	void pathFound(LinkedList<Vector2f> path);
	
}
