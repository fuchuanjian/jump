package loon.core.graphics.component;

import loon.core.graphics.LComponent;
import loon.core.graphics.LContainer;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;



public class LPanel extends LContainer {

	public LPanel(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.customRendering = true;
	}

	@Override
	public String getUIName() {
		return "Panel";
	}

	@Override
	public void createUI(GLEx g, int x, int y, LComponent component,
			LTexture[] buttonImage) {

	}
}
