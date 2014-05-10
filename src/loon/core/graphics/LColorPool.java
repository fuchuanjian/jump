
package loon.core.graphics;

import java.util.HashMap;

import loon.core.LRelease;
import loon.core.LSystem;

public class LColorPool implements LRelease {

	private static LColorPool pool;

	public static LColorPool $() {
		synchronized (LColorPool.class) {
			if (pool == null) {
				pool = new LColorPool();
			}
		}
		return pool;
	}
	
	private final LColor AlphaColor = new LColor(0f, 0f, 0f, 0f);

	private HashMap<Integer, LColor> ColorMap = new HashMap<Integer, LColor>();

	public LColor getColor(float r, float g, float b, float a) {
		if (a <= 0.1f) {
			return AlphaColor;
		}
		int hashCode = 1;
		hashCode = LSystem.unite(hashCode, r);
		hashCode = LSystem.unite(hashCode, g);
		hashCode = LSystem.unite(hashCode, b);
		hashCode = LSystem.unite(hashCode, a);
		LColor color = ColorMap.get(hashCode);
		if (color == null) {
			color = new LColor(r, g, b, a);
			ColorMap.put(hashCode, color);
		}
		return color;
	}

	public LColor getColor(int c) {
		LColor color = ColorMap.get(c);
		if (color == null) {
			color = new LColor(c);
			ColorMap.put(c, color);
		}
		return color;
	}

	public LColor getColor(float r, float g, float b) {
		return getColor(r, g, b, 1f);
	}

	public LColor getColor(int r, int g, int b, int a) {
		if (a <= 10) {
			return AlphaColor;
		}
		int hashCode = 1;
		hashCode = LSystem.unite(hashCode, r);
		hashCode = LSystem.unite(hashCode, g);
		hashCode = LSystem.unite(hashCode, b);
		hashCode = LSystem.unite(hashCode, a);
		LColor color = ColorMap.get(hashCode);
		if (color == null) {
			color = new LColor(r, g, b, a);
			ColorMap.put(hashCode, color);
		}
		return color;
	}

	public LColor getColor(int r, int g, int b) {
		return getColor(r, g, b, 1f);
	}

	@Override
	public void dispose() {
		if (ColorMap != null) {
			ColorMap.clear();
		}
	}
}
