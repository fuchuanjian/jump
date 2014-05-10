package loon.core.geom;

import java.io.Serializable;

public interface Triangle extends Serializable {

	public int getTriangleCount();
	
	public float[] getTrianglePoint(int t, int i);
	
	public void addPolyPoint(float x, float y);
	
	public void startHole();
	
	public boolean triangulate();
}
