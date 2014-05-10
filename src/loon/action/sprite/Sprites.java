package loon.action.sprite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import loon.core.LObject;
import loon.core.LRelease;
import loon.core.LSystem;
import loon.core.geom.RectBox;
import loon.core.geom.Point.Point2i;
import loon.core.graphics.opengl.GLEx;
import loon.utils.CollectionUtils;
import loon.utils.MathUtils;



public class Sprites implements Serializable, LRelease {

	public static interface SpriteListener {

		public void update(ISprite spr);

	}

	private static final long serialVersionUID = 7460335325994101982L;

	private int viewX;

	private int viewY;

	private boolean isViewWindowSet, visible;

	private SpriteListener sprListerner;

	private static final Comparator<Object> DEFAULT_COMPARATOR = new Comparator<Object>() {
		@Override
		public int compare(Object o1, Object o2) {
			return ((ISprite) o2).getLayer() - ((ISprite) o1).getLayer();
		}
	};

	private Comparator<Object> comparator = Sprites.DEFAULT_COMPARATOR;

	private int capacity = 1000;

	private ISprite[] sprites;

	private int size;

	private int width, height;

	public Sprites(int w, int h) {
		this.visible = true;
		this.width = w;
		this.height = h;
		this.sprites = new ISprite[capacity];
	}

	public Sprites() {
		this.visible = true;
		this.width = LSystem.screenRect.width;
		this.height = LSystem.screenRect.height;
		this.sprites = new ISprite[capacity];
	}

	
	public void sendToFront(ISprite sprite) {
		if (this.size <= 1 || this.sprites[0] == sprite) {
			return;
		}
		if (sprites[0] == sprite) {
			return;
		}
		for (int i = 0; i < this.size; i++) {
			if (this.sprites[i] == sprite) {
				this.sprites = (ISprite[]) CollectionUtils.cut(this.sprites, i);
				this.sprites = (ISprite[]) CollectionUtils.expand(this.sprites,
						1, false);
				this.sprites[0] = sprite;
				this.sortSprites();
				break;
			}
		}
	}

	
	public void sendToBack(ISprite sprite) {
		if (this.size <= 1 || this.sprites[this.size - 1] == sprite) {
			return;
		}
		if (sprites[this.size - 1] == sprite) {
			return;
		}
		for (int i = 0; i < this.size; i++) {
			if (this.sprites[i] == sprite) {
				this.sprites = (ISprite[]) CollectionUtils.cut(this.sprites, i);
				this.sprites = (ISprite[]) CollectionUtils.expand(this.sprites,
						1, true);
				this.sprites[this.size - 1] = sprite;
				this.sortSprites();
				break;
			}
		}
	}

	
	public void sortSprites() {
		Arrays.sort(this.sprites, this.comparator);
	}

	
	private void expandCapacity(int capacity) {
		if (sprites.length < capacity) {
			ISprite[] bagArray = new ISprite[capacity];
			System.arraycopy(sprites, 0, bagArray, 0, size);
			sprites = bagArray;
		}
	}

	
	private void compressCapacity(int capacity) {
		if (capacity + this.size < sprites.length) {
			ISprite[] newArray = new ISprite[this.size + 2];
			System.arraycopy(sprites, 0, newArray, 0, this.size);
			sprites = newArray;
		}
	}

	
	public synchronized ISprite find(int x, int y) {
		ISprite[] snapshot = sprites;
		for (int i = snapshot.length - 1; i >= 0; i--) {
			ISprite child = snapshot[i];
			RectBox rect = child.getCollisionBox();
			if (rect != null && rect.contains(x, y)) {
				return child;
			}
		}
		return null;
	}

	
	public synchronized ISprite find(String name) {
		ISprite[] snapshot = sprites;
		for (int i = snapshot.length - 1; i >= 0; i--) {
			ISprite child = snapshot[i];
			if (child instanceof LObject) {
				String childName = ((LObject) child).getName();
				if (name.equalsIgnoreCase(childName)) {
					return child;
				}
			}
		}
		return null;
	}

	
	public synchronized boolean add(int index, ISprite sprite) {
		if (sprite == null) {
			return false;
		}
		if (index > this.size) {
			index = this.size;
		}
		if (index == this.size) {
			this.add(sprite);
		} else {
			System.arraycopy(this.sprites, index, this.sprites, index + 1,
					this.size - index);
			this.sprites[index] = sprite;
			if (++this.size >= this.sprites.length) {
				expandCapacity((size + 1) * 2);
			}
		}
		return sprites[index] != null;
	}

	public synchronized ISprite getSprite(int index) {
		if (index < 0 || index > size || index >= sprites.length) {
			return null;
		}
		return sprites[index];
	}

	
	public synchronized ISprite getTopSprite() {
		if (size > 0) {
			return sprites[0];
		}
		return null;
	}

	
	public synchronized ISprite getBottomSprite() {
		if (size > 0) {
			return sprites[size - 1];
		}
		return null;
	}

	
	public synchronized ArrayList<ISprite> getSprites(
			Class<? extends ISprite> clazz) {
		if (clazz == null) {
			return null;
		}
		ArrayList<ISprite> l = new ArrayList<ISprite>(size);
		for (int i = size; i > 0; i--) {
			ISprite sprite = sprites[i - 1];
			Class<? extends ISprite> cls = sprite.getClass();
			if (clazz == null || clazz == cls || clazz.isInstance(sprite)
					|| clazz.equals(cls)) {
				l.add(sprite);
			}
		}
		return l;
	}

	
	public synchronized boolean add(ISprite sprite) {
		if (contains(sprite)) {
			return false;
		}

		if (this.size == this.sprites.length) {
			expandCapacity((size + 1) * 2);
		}
		return (sprites[size++] = sprite) != null;
	}

	
	public void append(ISprite sprite) {
		add(sprite);
	}

	
	public synchronized boolean contains(ISprite sprite) {
		if (sprite == null) {
			return false;
		}
		if (sprites == null) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (sprites[i] != null && sprite.equals(sprites[i])) {
				return true;
			}
		}
		return false;
	}

	
	public synchronized ISprite remove(int index) {
		ISprite removed = this.sprites[index];
		int size = this.size - index - 1;
		if (size > 0) {
			System.arraycopy(this.sprites, index + 1, this.sprites, index, size);
		}
		this.sprites[--this.size] = null;
		if (size == 0) {
			sprites = new ISprite[0];
		}
		return removed;
	}

	
	public synchronized void removeAll() {
		clear();
		this.sprites = new ISprite[0];
	}

	
	public synchronized void remove(Class<? extends ISprite> clazz) {
		if (clazz == null) {
			return;
		}
		for (int i = size; i > 0; i--) {
			ISprite sprite = sprites[i - 1];
			Class<? extends ISprite> cls = sprite.getClass();
			if (clazz == null || clazz == cls || clazz.isInstance(sprite)
					|| clazz.equals(cls)) {
				size--;
				sprites[i - 1] = sprites[size];
				sprites[size] = null;
				if (size == 0) {
					sprites = new ISprite[0];
				} else {
					compressCapacity(2);
				}
			}
		}
	}

	
	public synchronized boolean remove(ISprite sprite) {
		if (sprite == null) {
			return false;
		}
		if (sprites == null) {
			return false;
		}
		boolean removed = false;

		for (int i = size; i > 0; i--) {
			if (sprite.equals(sprites[i - 1])) {
				removed = true;
				size--;
				sprites[i - 1] = sprites[size];
				sprites[size] = null;
				if (size == 0) {
					sprites = new ISprite[0];
				} else {
					compressCapacity(2);
				}
				return removed;
			}
		}
		return removed;
	}

	
	public synchronized void remove(int startIndex, int endIndex) {
		int numMoved = this.size - endIndex;
		System.arraycopy(this.sprites, endIndex, this.sprites, startIndex,
				numMoved);
		int newSize = this.size - (endIndex - startIndex);
		while (this.size != newSize) {
			this.sprites[--this.size] = null;
		}
		if (size == 0) {
			sprites = new ISprite[0];
		}
	}

	public Point2i getMinPos() {
		Point2i p = new Point2i(0, 0);
		for (int i = 0; i < size; i++) {
			ISprite sprite = sprites[i];
			p.x = MathUtils.min(p.x, sprite.x());
			p.y = MathUtils.min(p.y, sprite.y());
		}
		return p;
	}

	public Point2i getMaxPos() {
		Point2i p = new Point2i(0, 0);
		for (int i = 0; i < size; i++) {
			ISprite sprite = sprites[i];
			p.x = MathUtils.max(p.x, sprite.x());
			p.y = MathUtils.max(p.y, sprite.y());
		}
		return p;
	}

	
	public synchronized void clear() {
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = null;
		}
		size = 0;
	}

	
	public void update(long elapsedTime) {
		boolean listerner = (sprListerner != null);
		for (int i = size - 1; i >= 0; i--) {
			ISprite child = sprites[i];
			if (child.isVisible()) {
				child.update(elapsedTime);
				if (listerner) {
					sprListerner.update(child);
				}
			}
		}
	}

	
	public void createUI(final GLEx g) {
		createUI(g, 0, 0);
	}

	
	public void createUI(final GLEx g, final int x, final int y) {
		if (!visible) {
			return;
		}
		int minX, minY, maxX, maxY;
		int clipWidth = g.getClipWidth();
		int clipHeight = g.getClipHeight();
		if (this.isViewWindowSet) {
			g.setClip(x, y, this.width, this.height);
			minX = this.viewX;
			maxX = minX + this.width;
			minY = this.viewY;
			maxY = minY + this.height;
		} else {
			minX = x;
			maxX = x + clipWidth;
			minY = y;
			maxY = y + clipHeight;
		}
		g.translate(x - this.viewX, y - this.viewY);
		for (int i = 0; i < this.size; i++) {

			ISprite spr = sprites[i];
			if (spr.isVisible()) {

				int layerX = spr.x();
				int layerY = spr.y();

				int layerWidth = spr.getWidth();
				int layerHeight = spr.getHeight();

				if (layerX + layerWidth < minX || layerX > maxX
						|| layerY + layerHeight < minY || layerY > maxY) {
					continue;
				}

				spr.createUI(g);
			}

		}
		g.translate(-(x - this.viewX), -(y - this.viewY));
		if (this.isViewWindowSet) {
			g.clearClip();
		}
	}

	
	public void setViewWindow(int x, int y, int width, int height) {
		this.isViewWindowSet = true;
		this.viewX = x;
		this.viewY = y;
		this.width = width;
		this.height = height;
	}

	
	public void setLocation(int x, int y) {
		this.isViewWindowSet = true;
		this.viewX = x;
		this.viewY = y;
	}

	public ISprite[] getSprites() {
		return this.sprites;
	}

	public int size() {
		return this.size;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public SpriteListener getSprListerner() {
		return sprListerner;
	}

	public void setSprListerner(SpriteListener sprListerner) {
		this.sprListerner = sprListerner;
	}

	@Override
	public void dispose() {
		this.visible = false;
		for (ISprite spr : sprites) {
			if (spr != null) {
				spr.dispose();
				spr = null;
			}
		}
	}

}
