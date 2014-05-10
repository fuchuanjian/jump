package loon.action.sprite;

import java.io.Serializable;

import loon.core.LRelease;
import loon.core.geom.RectBox;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;



public interface ISprite extends Serializable, LRelease {

	public static final int TYPE_FADE_IN = 0;

	public static final int TYPE_FADE_OUT = 1;

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract float getAlpha();

	public abstract int x();

	public abstract int y();

	public abstract float getX();

	public abstract float getY();

	public abstract void setVisible(boolean visible);

	public abstract boolean isVisible();

	public abstract void createUI(GLEx g);

	public abstract void update(long elapsedTime);

	public abstract int getLayer();

	public abstract void setLayer(int layer);

	public abstract RectBox getCollisionBox();

	public abstract LTexture getBitmap();

}
