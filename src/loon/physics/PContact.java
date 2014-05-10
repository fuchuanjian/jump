
package loon.physics;

import loon.core.geom.Vector2f;

public class PContact {

	float corI;
	PContactData data;
	Vector2f localRel1;
	Vector2f localRel2;
	float massN;
	float massT;
	float norI;
	Vector2f normal;
	float overlap;
	Vector2f pos;
	Vector2f rel1;
	Vector2f rel2;
	Vector2f relPosVel;
	Vector2f relVel;
	Vector2f tangent;
	float tanI;
	float targetVelocity;

	public PContact() {
		rel1 = new Vector2f();
		rel2 = new Vector2f();
		localRel1 = new Vector2f();
		localRel2 = new Vector2f();
		pos = new Vector2f();
		normal = new Vector2f();
		tangent = new Vector2f();
		relVel = new Vector2f();
		relPosVel = new Vector2f();
		data = new PContactData();
	}

	public Vector2f getNormal() {
		return normal.clone();
	}

	public float getOverlap() {
		return overlap;
	}

	public Vector2f getPosition() {
		return pos.clone();
	}

	public Vector2f getRelativeVelocity() {
		return relVel.clone();
	}

	public Vector2f getTangent() {
		return tangent.clone();
	}

}
