
package loon.physics;

public class PSortableObject {

	PSortableAABB aabb;
	boolean begin;
	PShape parent;
	float value;

	public PSortableObject(PShape s, PSortableAABB aabb, float value,
			boolean begin) {
		this.parent = s;
		this.aabb = aabb;
		this.value = value;
		this.begin = begin;
	}

}
