package loon.action.sprite.effect;

import loon.core.LSystem;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;
import loon.core.graphics.opengl.LTextures;
import loon.utils.MathUtils;



public class SnowKernel implements IKernel {

	private boolean exist;

	private LTexture snow;

	private int id;

	private float offsetX, offsetY, speed, x, y, width, height, snowWidth,
			snowHeight;

	public SnowKernel(int n, int w, int h) {
		snow = LTextures.loadTexture(
				(LSystem.FRAMEWORK_IMG_NAME + "snow_" + n + ".png").intern());
		snowWidth = snow.getWidth();
		snowHeight = snow.getHeight();
		width = w;
		height = h;
		offsetX = 0;
		offsetY = n * 0.6f + 1.9f + MathUtils.random() * 0.2f;
		speed = MathUtils.random();
	}

	@Override
	public int id() {
		return id;
	}

	public void make() {
		exist = true;
		x = MathUtils.random() * width;
		y = -snowHeight;
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
			offsetX += speed;
			speed += (MathUtils.random() - 0.5) * 0.3;
			if (offsetX >= 1.5) {
				offsetX = 1.5f;
			}
			if (offsetX <= -1.5) {
				offsetX = -1.5f;
			}
			if (speed >= 0.2) {
				speed = 0.2f;
			}
			if (speed <= -0.2) {
				speed = -0.2f;
			}
			if (y >= height) {
				y = -snowHeight;
				x = MathUtils.random() * width;
			}
		}
	}

	@Override
	public void draw(GLEx g) {
		if (exist) {
			snow.draw(x, y);
		}
	}

	@Override
	public LTexture get() {
		return snow;
	}

	@Override
	public float getHeight() {
		return snowHeight;
	}

	@Override
	public float getWidth() {
		return snowWidth;
	}

	@Override
	public void dispose() {
		if (snow != null) {
			snow.destroy();
			snow = null;
		}
	}

}
