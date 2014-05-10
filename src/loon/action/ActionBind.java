
package loon.action;

import loon.action.map.Field2D;
import loon.core.geom.RectBox;


public interface ActionBind {
	
	public Field2D getField2D();

	public int x();

	public int y();
	
	public float getX();

	public float getY();

	public float getScaleX();

	public float getScaleY();

	public void setScale(float sx, float sy);

	public float getRotation();

	public void setRotation(float r);

	public int getWidth();

	public int getHeight();

	public float getAlpha();

	public void setAlpha(float a);

	public void setLocation(float x, float y);

	public boolean isBounded();

	public boolean isContainer();

	public boolean inContains(int x, int y, int w, int h);
	
	public RectBox getRectBox();

	public int getContainerWidth();

	public int getContainerHeight();
}
