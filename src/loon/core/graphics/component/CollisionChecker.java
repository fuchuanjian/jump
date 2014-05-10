package loon.core.graphics.component;

import java.util.Iterator;
import java.util.List;

import loon.core.LRelease;




@SuppressWarnings({ "rawtypes" })
public interface CollisionChecker extends LRelease {

	void initialize(int cellSize);

	void addObject(Actor actor);

	void removeObject(Actor actor);

	void clear();

	void updateObjectLocation(Actor actor, float x, float y);

	void updateObjectSize(Actor actor);

	List getObjectsAt(float x, float y, Class cls);

	List getIntersectingObjects(Actor actor, Class cls);

	List getObjectsInRange(float x, float y, float r, Class cls);

	List getNeighbours(Actor actor, float distance, boolean d, Class cls);

	List getObjects(Class actor);

	List getObjectsList();

	Actor getOnlyObjectAt(Actor actor, float x, float y, Class cls);

	Actor getOnlyIntersectingObject(Actor actor, Class cls);

	Iterator getActorsIterator();

	List getActorsList();
}
