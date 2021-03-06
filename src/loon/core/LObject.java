package loon.core;

import loon.action.map.Config;
import loon.core.geom.RectBox;
import loon.core.geom.Vector2f;
import loon.utils.MathUtils;



public abstract class LObject {

	public Object Tag;

	public float alpha = 1f;

	protected RectBox rect;

	protected String name;

	protected Vector2f location = new Vector2f(0, 0);

	protected int layer;

	protected float rotation;

	public void setTransparency(int alpha) {
		setAlpha(alpha / 255f);
	}

	public int getTransparency() {
		return (int) (alpha * 255);
	}

	public void setAlpha(float a) {
		this.alpha = a;
	}

	public float getAlpha() {
		return this.alpha;
	}

	public void setRotation(float r) {
		this.rotation = r;
		if (rect != null) {
			rect = MathUtils.getBounds(location.x, location.y, getWidth(),
					getHeight(), r, rect);
		}
	}

	public float getRotation() {
		return rotation;
	}

	public abstract void update(long elapsedTime);

	public void centerOnScreen() {
		LObject.centerOn(this, LSystem.screenRect.width,
				LSystem.screenRect.height);
	}

	public void bottomOnScreen() {
		LObject.bottomOn(this, LSystem.screenRect.width,
				LSystem.screenRect.height);
	}

	public void leftOnScreen() {
		LObject.leftOn(this, LSystem.screenRect.width,
				LSystem.screenRect.height);
	}

	public void rightOnScreen() {
		LObject.rightOn(this, LSystem.screenRect.width,
				LSystem.screenRect.height);
	}

	public void topOnScreen() {
		LObject.topOn(this, LSystem.screenRect.width, LSystem.screenRect.height);
	}

	public RectBox getCollisionArea() {
		return getRect(getX(), getY(), getWidth(), getHeight());
	}

	protected RectBox getRect(float x, float y, float w, float h) {
		if (rect == null) {
			rect = new RectBox(x, y, w, h);
		} else {
			rect.setBounds(x, y, w, h);
		}
		return rect;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void move_45D_up() {
		move_45D_up(1);
	}

	public void move_45D_up(int multiples) {
		location.move_multiples(Config.UP, multiples);
	}

	public void move_45D_left() {
		move_45D_left(1);
	}

	public void move_45D_left(int multiples) {
		location.move_multiples(Config.LEFT, multiples);
	}

	public void move_45D_right() {
		move_45D_right(1);
	}

	public void move_45D_right(int multiples) {
		location.move_multiples(Config.RIGHT, multiples);
	}

	public void move_45D_down() {
		move_45D_down(1);
	}

	public void move_45D_down(int multiples) {
		location.move_multiples(Config.DOWN, multiples);
	}

	public void move_up() {
		move_up(1);
	}

	public void move_up(int multiples) {
		location.move_multiples(Config.TUP, multiples);
	}

	public void move_left() {
		move_left(1);
	}

	public void move_left(int multiples) {
		location.move_multiples(Config.TLEFT, multiples);
	}

	public void move_right() {
		move_right(1);
	}

	public void move_right(int multiples) {
		location.move_multiples(Config.TRIGHT, multiples);
	}

	public void move_down() {
		move_down(1);
	}

	public void move_down(int multiples) {
		location.move_multiples(Config.TDOWN, multiples);
	}

	public void move(Vector2f vector2D) {
		location.move(vector2D);
	}

	public void move(float x, float y) {
		location.move(x, y);
	}

	public void setLocation(float x, float y) {
		location.setLocation(x, y);
	}

	public int x() {
		return (int) location.getX();
	}

	public int y() {
		return (int) location.getY();
	}

	public float getX() {
		return location.getX();
	}

	public float getY() {
		return location.getY();
	}

	public void setX(Integer x) {
		location.setX(x.intValue());
	}

	public void setX(float x) {
		location.setX(x);
	}

	public void setY(Integer y) {
		location.setY(y.intValue());
	}

	public void setY(float y) {
		location.setY(y);
	}

	public Vector2f getLocation() {
		return location;
	}

	public void setLocation(Vector2f location) {
		this.location = location;
	}

	public static void centerOn(final LObject object, int w, int h) {
		object.setLocation(w / 2 - object.getWidth() / 2,
				h / 2 - object.getHeight() / 2);
	}

	public static void topOn(final LObject object, int w, int h) {
		object.setLocation(w / 2 - h / 2, 0);
	}

	public static void leftOn(final LObject object, int w, int h) {
		object.setLocation(0, h / 2 - object.getHeight() / 2);
	}

	public static void rightOn(final LObject object, int w, int h) {
		object.setLocation(w - object.getWidth(), h / 2 - object.getHeight()
				/ 2);
	}

	public static void bottomOn(final LObject object, int w, int h) {
		object.setLocation(w / 2 - object.getWidth() / 2,
				h - object.getHeight());
	}

	public void centerOn(final LObject object) {
		centerOn(object, getWidth(), getHeight());
	}

	public void topOn(final LObject object) {
		topOn(object, getWidth(), getHeight());
	}

	public void leftOn(final LObject object) {
		leftOn(object, getWidth(), getHeight());
	}

	public void rightOn(final LObject object) {
		rightOn(object, getWidth(), getHeight());
	}

	public void bottomOn(final LObject object) {
		bottomOn(object, getWidth(), getHeight());
	}

	public abstract int getWidth();

	public abstract int getHeight();

}
