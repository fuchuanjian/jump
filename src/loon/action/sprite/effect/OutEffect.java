package loon.action.sprite.effect;

import loon.action.map.Config;
import loon.action.sprite.ISprite;
import loon.core.LObject;
import loon.core.LSystem;
import loon.core.geom.RectBox;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;



public class OutEffect extends LObject implements ISprite {

	
	private static final long serialVersionUID = 1L;

	private LTexture texture;

	private boolean visible, complete;

	private int width, height;

	private int type, multiples;

	private RectBox limit;

	public OutEffect(String fileName, int code) {
		this(new LTexture(fileName), code);
	}

	public OutEffect(LTexture t, int code) {
		this(t, LSystem.screenRect, code);
	}

	public OutEffect(LTexture t, RectBox limit, int code) {
		this.texture = t;
		this.type = code;
		this.width = t.getWidth();
		this.height = t.getHeight();
		this.multiples = 1;
		this.limit = limit;
		this.visible = true;
	}

	@Override
	public void update(long elapsedTime) {
		if (!complete) {
			switch (type) {
			case Config.DOWN:
				move_45D_down(multiples);
				break;
			case Config.UP:
				move_45D_up(multiples);
				break;
			case Config.LEFT:
				move_45D_left(multiples);
				break;
			case Config.RIGHT:
				move_45D_right(multiples);
				break;
			case Config.TDOWN:
				move_down(multiples);
				break;
			case Config.TUP:
				move_up(multiples);
				break;
			case Config.TLEFT:
				move_left(multiples);
				break;
			case Config.TRIGHT:
				move_right(multiples);
				break;
			}
			if (!limit.intersects(x(), y(), width, height)) {
				complete = true;
			}
		}
	}

	public boolean isComplete() {
		return complete;
	}

	@Override
	public int getHeight() {
		return width;
	}

	@Override
	public int getWidth() {
		return height;
	}

	@Override
	public void createUI(GLEx g) {
		if (!visible) {
			return;
		}
		if (!complete) {
			if (alpha > 0 && alpha < 1) {
				g.setAlpha(alpha);
			}
			g.drawTexture(texture, x(), y());
			if (alpha > 0 && alpha < 1) {
				g.setAlpha(1);
			}
		}
	}

	@Override
	public LTexture getBitmap() {
		return texture;
	}

	@Override
	public RectBox getCollisionBox() {
		return getRect(x(), y(), width, height);
	}

	public int getMultiples() {
		return multiples;
	}

	public void setMultiples(int multiples) {
		this.multiples = multiples;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public void dispose() {
		if (texture != null) {
			texture.destroy();
			texture = null;
		}
	}

}

