
package loon.physics;

import loon.core.geom.AABB;

public class PSortableAABB {

	AABB aabb;
	PSortableObject beginX;
	PSortableObject beginY;
	PSortableObject endX;
	PSortableObject endY;
	PShape parent;
	PSweepAndPrune sap;
	boolean set;

	public PSortableAABB() {
	}

	void remove() {
		if (!set) {
			return;
		} else {
			sap.removeObject(beginX, beginY);
			sap.removeObject(endX, endY);
			return;
		}
	}

	public void set(PSweepAndPrune sap, PShape s, AABB aabb) {
		set = true;
		this.sap = sap;
		parent = s;
		this.aabb = aabb;
		beginX = new PSortableObject(s, this, aabb.minX, true);
		beginY = new PSortableObject(s, this, aabb.minY, true);
		endX = new PSortableObject(s, this, aabb.maxX, false);
		endY = new PSortableObject(s, this, aabb.maxY, false);
		sap.addObject(beginX, beginY);
		sap.addObject(endX, endY);
	}

	void update() {
		if (!set) {
			return;
		} else {
			beginX.value = aabb.minX;
			beginY.value = aabb.minY;
			endX.value = aabb.maxX;
			endY.value = aabb.maxY;
			return;
		}
	}

}
