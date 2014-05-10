package loon.core.graphics.component;

import loon.core.graphics.opengl.GLEx;


public interface ActorListener {

	public void draw(GLEx g);

	public void update(long elapsedTime);

	public void downClick(int x, int y);

	public void upClick(int x, int y);

	public void drag(int x, int y);
}
