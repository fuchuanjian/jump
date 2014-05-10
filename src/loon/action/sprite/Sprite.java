package loon.action.sprite;

import loon.action.ActionBind;
import loon.action.collision.CollisionHelper;
import loon.action.map.Field2D;
import loon.core.LObject;
import loon.core.LSystem;
import loon.core.geom.Point;
import loon.core.geom.RectBox;
import loon.core.geom.Vector2f;
import loon.core.graphics.LColor;
import loon.core.graphics.device.LTrans;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;
import loon.core.graphics.opengl.TextureUtils;



public class Sprite extends LObject implements ActionBind, ISprite, LTrans {

	
	private static final long serialVersionUID = -1982110847888726016L;

	// 默认每帧刷新时间
	final static private long defaultTimer = 150;

	// 是否可见
	private boolean visible = true;

	// 精灵名称
	private String spriteName;

	// 精灵图片
	private LTexture image;

	// 动画
	private Animation animation = new Animation();

	private int transform;

	private float scaleX = 1, scaleY = 1;

	
	public Sprite() {
		this(0, 0);
	}

	
	public Sprite(float x, float y) {
		this("Sprite" + System.currentTimeMillis(), x, y);
	}

	
	private Sprite(String spriteName, float x, float y) {
		this.setLocation(x, y);
		this.spriteName = spriteName;
		this.visible = true;
		this.transform = LTrans.TRANS_NONE;
	}

	
	public Sprite(String fileName, int row, int col) {
		this(fileName, -1, 0, 0, row, col, defaultTimer);
	}

	
	public Sprite(String fileName, int row, int col, long timer) {
		this(fileName, -1, 0, 0, row, col, timer);
	}

	
	public Sprite(String fileName, float x, float y, int row, int col) {
		this(fileName, x, y, row, col, defaultTimer);
	}

	
	private Sprite(String fileName, float x, float y, int row, int col,
			long timer) {
		this(fileName, -1, x, y, row, col, timer);
	}

	
	public Sprite(String fileName, int maxFrame, float x, float y, int row,
			int col) {
		this(fileName, maxFrame, x, y, row, col, defaultTimer);
	}

	
	public Sprite(String fileName, int maxFrame, float x, float y, int row,
			int col, long timer) {
		this("Sprite" + System.currentTimeMillis(), fileName, maxFrame, x, y,
				row, col, timer);
	}

	
	public Sprite(String spriteName, String fileName, int maxFrame, float x,
			float y, int row, int col, long timer) {
		this(spriteName, TextureUtils.getSplitTextures(fileName, row, col),
				maxFrame, x, y, timer);
	}

	
	public Sprite(String fileName) {
		this(new LTexture(fileName));
	}

	
	public Sprite(final LTexture img) {
		this(new LTexture[] { img }, 0, 0);
	}

	
	public Sprite(LTexture[] images) {
		this(images, 0, 0);
	}

	
	public Sprite(LTexture[] images, float x, float y) {
		this(images, x, y, defaultTimer);
	}

	
	public Sprite(LTexture[] images, long timer) {
		this(images, -1, 0, 0, defaultTimer);
	}

	
	public Sprite(LTexture[] images, float x, float y, long timer) {
		this(images, -1, x, y, timer);
	}

	
	public Sprite(LTexture[] images, int maxFrame, float x, float y, long timer) {
		this("Sprite" + System.currentTimeMillis(), images, maxFrame, x, y,
				timer);
	}

	
	public Sprite(String spriteName, LTexture[] images, int maxFrame, float x,
			float y, long timer) {
		this.setLocation(x, y);
		this.spriteName = spriteName;
		this.setAnimation(animation, images, maxFrame, timer);
		this.visible = true;
		this.transform = LTrans.TRANS_NONE;
	}

	
	public void setRunning(boolean running) {
		animation.setRunning(running);
	}

	
	public int getTotalFrames() {
		return animation.getTotalFrames();
	}

	
	public void setCurrentFrameIndex(int index) {
		animation.setCurrentFrameIndex(index);
	}

	
	public int getCurrentFrameIndex() {
		return animation.getCurrentFrameIndex();
	}

	
	public int centerX(int x) {
		return centerX(this, x);
	}

	
	public static int centerX(Sprite sprite, int x) {
		int newX = x - (sprite.getWidth() / 2);
		if (newX + sprite.getWidth() >= LSystem.screenRect.width) {
			return (LSystem.screenRect.width - sprite.getWidth() - 1);
		}
		if (newX < 0) {
			return x;
		} else {
			return newX;
		}
	}

	
	public int centerY(int y) {
		return centerY(this, y);
	}

	
	public static int centerY(Sprite sprite, int y) {
		int newY = y - (sprite.getHeight() / 2);
		if (newY + sprite.getHeight() >= LSystem.screenRect.height) {
			return (LSystem.screenRect.height - sprite.getHeight() - 1);
		}
		if (newY < 0) {
			return y;
		} else {
			return newY;
		}
	}

	
	private void setAnimation(Animation myAnimation, LTexture[] images,
			int maxFrame, long timer) {
		if (maxFrame != -1) {
			for (int i = 0; i < maxFrame; i++) {
				myAnimation.addFrame(images[i], timer);
			}
		} else {
			for (int i = 0; i < images.length; i++) {
				myAnimation.addFrame(images[i], timer);
			}
		}
	}

	
	public void setAnimation(String fileName, int maxFrame, int row, int col,
			long timer) {
		setAnimation(new Animation(),
				TextureUtils.getSplitTextures(fileName, row, col), maxFrame,
				timer);
	}

	
	public void setAnimation(String fileName, int row, int col, long timer) {
		setAnimation(fileName, -1, row, col, timer);
	}

	
	public void setAnimation(LTexture[] images, int maxFrame, long timer) {
		setAnimation(new Animation(), images, maxFrame, timer);
	}

	
	public void setAnimation(LTexture[] images, long timer) {
		setAnimation(new Animation(), images, -1, timer);
	}

	
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public Animation getAnimation() {
		return animation;
	}

	
	@Override
	public void update(long timer) {
		if (visible) {
			animation.update(timer);
		}
	}

	
	public void updateLocation(Vector2f vector) {
		this.setX(Math.round(vector.getX()));
		this.setY(Math.round(vector.getY()));
	}

	public LTexture getImage() {
		return animation.getSpriteImage();
	}

	@Override
	public int getWidth() {
		LTexture si = animation.getSpriteImage();
		if (si == null) {
			return -1;
		}
		return (int) (si.getWidth() * scaleX);
	}

	@Override
	public int getHeight() {
		LTexture si = animation.getSpriteImage();
		if (si == null) {
			return -1;
		}
		return (int) (si.getHeight() * scaleY);
	}

	
	public Point getMiddlePoint() {
		return new Point(getLocation().x() + getWidth() / 2, getLocation().y()
				+ getHeight() / 2);
	}

	
	public float getDistance(Sprite second) {
		return this.getMiddlePoint()
				.distanceTo(second.getMiddlePoint());
	}

	
	@Override
	public RectBox getCollisionBox() {
		return getRect(getLocation().x(), getLocation().y(), getWidth(),
				getHeight());
	}

	
	public boolean isRectToRect(Sprite sprite) {
		return CollisionHelper.isRectToRect(this.getCollisionBox(),
				sprite.getCollisionBox());
	}

	
	public boolean isCircToCirc(Sprite sprite) {
		return CollisionHelper.isCircToCirc(this.getCollisionBox(),
				sprite.getCollisionBox());
	}

	
	public boolean isRectToCirc(Sprite sprite) {
		return CollisionHelper.isRectToCirc(this.getCollisionBox(),
				sprite.getCollisionBox());
	}

	private LColor filterColor;

	@Override
	public void createUI(GLEx g) {
		if (!visible) {
			return;
		}
		image = animation.getSpriteImage();
		if (image == null) {
			return;
		}
		float width = (image.getWidth() * scaleX);
		float height = (image.getHeight() * scaleY);
		if (filterColor == null) {
			if (alpha > 0 && alpha < 1) {
				g.setAlpha(alpha);
			}
			if (LTrans.TRANS_NONE == transform) {
				g.drawTexture(image, x(), y(), width, height, rotation);
			} else {
				g.drawRegion(image, 0, 0, getWidth(), getHeight(), transform,
						x(), y(), LTrans.TOP | LTrans.LEFT);
			}
			if (alpha > 0 && alpha < 1) {
				g.setAlpha(1);
			}
			return;
		} else {
			if (alpha > 0 && alpha < 1) {
				g.setAlpha(alpha);
			}
			if (LTrans.TRANS_NONE == transform) {
				g.drawTexture(image, x(), y(), width, height, rotation,
						filterColor);
			} else {
				g.drawRegion(image, 0, 0, getWidth(), getHeight(), transform,
						x(), y(), LTrans.TOP | LTrans.LEFT, filterColor);
			}
			if (alpha > 0 && alpha < 1) {
				g.setAlpha(1);
			}
			return;
		}
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getSpriteName() {
		return spriteName;
	}

	public void setSpriteName(String spriteName) {
		this.spriteName = spriteName;
	}

	public int getTransform() {
		return transform;
	}

	public void setTransform(int transform) {
		this.transform = transform;
	}

	public LColor getFilterColor() {
		return filterColor;
	}

	public void setFilterColor(LColor filterColor) {
		this.filterColor = filterColor;
	}

	@Override
	public LTexture getBitmap() {
		return this.image;
	}

	@Override
	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	@Override
	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	@Override
	public void dispose() {
		this.visible = false;
		if (image != null) {
			image.dispose();
			image = null;
		}
		if (animation != null) {
			animation.dispose();
			animation = null;
		}
	}

	@Override
	public Field2D getField2D() {
		return null;
	}

	@Override
	public boolean isBounded() {
		return false;
	}

	@Override
	public boolean isContainer() {
		return false;
	}

	@Override
	public boolean inContains(int x, int y, int w, int h) {
		return false;
	}

	@Override
	public RectBox getRectBox() {
		return getCollisionBox();
	}

	@Override
	public int getContainerWidth() {
		return 0;
	}

	@Override
	public int getContainerHeight() {
		return 0;
	}

	@Override
	public void setScale(float sx, float sy) {
		this.scaleX = sx;
		this.scaleY = sy;
	}
}
