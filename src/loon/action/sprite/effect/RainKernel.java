package loon.action.sprite.effect;

import loon.core.LSystem;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;
import loon.core.graphics.opengl.LTextures;
import loon.utils.MathUtils;



public class RainKernel implements IKernel {

	private boolean exist;

	private LTexture rain;
	
	private int id;

	private float offsetX, offsetY, x, y, width, height, rainWidth, rainHeight;

	public RainKernel(int n, int w, int h) {
		rain = LTextures.loadTexture(
				(LSystem.FRAMEWORK_IMG_NAME + "rain_" + n + ".png").intern());
		rainWidth = rain.getWidth();
		rainHeight = rain.getHeight();
		width = w;
		height = h;
		offsetX = 0;
		offsetY = (5 - n) * 30 + 75 + MathUtils.random() * 15;
	}

	@Override
	public int id() {
		return id;
	}

	public void make() {
		exist = true;
		x = MathUtils.random() * width;
		y = -rainHeight;
	}

	@Override
	public void update() {
		if (!exist) {
			if (MathUtils.random() < 0.002) {
				make();
			}
		} else {
			x += offsetX;
			y += offsetY;
			if (y >= height) {
				x = MathUtils.random() * width;
				y = -rainHeight * MathUtils.random();
			}
		}
	}

	@Override
	public void draw(GLEx g) {
		if (exist) {
			rain.draw(x, y);
		}
	}

	@Override
	public LTexture get() {
		return rain;
	}

	@Override
	public float getHeight() {
		return rainHeight;
	}

	@Override
	public float getWidth() {
		return rainWidth;
	}

	@Override
	public void dispose() {
		if (rain != null) {
			rain.destroy();
			rain = null;
		}
	}

}
