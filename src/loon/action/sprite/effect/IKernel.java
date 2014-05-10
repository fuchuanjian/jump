package loon.action.sprite.effect;

import loon.core.LRelease;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;



public interface IKernel extends LRelease {

	public int id();

	public void draw(GLEx g);

	public void update();

	public LTexture get();

	public float getHeight();

	public float getWidth();

}
