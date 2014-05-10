
package loon.core.event;

import loon.core.graphics.opengl.GLEx;
import loon.core.input.LKey;
import loon.core.input.LTouch;

public interface ScreenListener {
	
	public void draw(GLEx g);
	
	public void update(long elapsedTime);

	public void pressed(LTouch e);

	public void released(LTouch e);

	public void move(LTouch e);

	public void drag(LTouch e);
	
	public void pressed(LKey e);

	public void released(LKey e);
	
	public void dispose();
}
