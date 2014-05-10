package loon.core.graphics.opengl;

import loon.core.LRelease;
import loon.core.graphics.LImage;


import android.graphics.Bitmap.Config;


public abstract class LTextureData implements LRelease {

	android.graphics.Bitmap.Config config;

	public static boolean ALL_ALPHA = false;

	int width, height;

	int texWidth, texHeight;

	boolean hasAlpha, multipyAlpha = ALL_ALPHA;

	int[] source;

	String fileName;

	public abstract LTextureData copy();

	public abstract void createTexture();

	public int getHeight() {
		return height;
	}

	public int getTexHeight() {
		return texHeight;
	}

	public int getTexWidth() {
		return texWidth;
	}

	public boolean hasAlpha() {
		return hasAlpha;
	}

	public int getWidth() {
		return width;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean isMultipyAlpha() {
		return multipyAlpha;
	}

	public void setMultipyAlpha(boolean multipyAlpha) {
		this.multipyAlpha = multipyAlpha;
	}

	final static LImage createPixelImage(int[] pixels, int texWidth,
			int texHeight, int width, int height, Config config) {
		LImage image = new LImage(texWidth, texHeight, config);
		image.setPixels(pixels, texWidth, texHeight);
		if (texWidth != width || texHeight != height) {
			LImage temp = image.getSubImage(0, 0, width, height);
			if (temp != image) {
				if (image != null) {
					image.dispose();
					image = null;
				}
				image = temp;
			}
		}
		return image;
	}

	@Override
	public void dispose() {
		source = null;
	}
}
