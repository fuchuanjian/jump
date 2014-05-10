
package loon.action.sprite.node;

import loon.action.sprite.SpriteBatch;
import loon.utils.MathUtils;

public class LNProgressBar extends LNSprite {
	private float _percent;
	private BarType _type;

	public LNProgressBar() {
		this._type = BarType.PROGRESS_BAR_LEFT;
	}

	public LNProgressBar(String fsName) {
		super(fsName);
		this._type = BarType.PROGRESS_BAR_LEFT;
		this._percent = 1f;
	}

	private float[] pos;

	private float rotation;

	private float[] scale;

	@Override
	public void draw(SpriteBatch batch) {
		if (super._visible && (super._texture != null)) {
			pos = super.convertToWorldPos();
			rotation = super.convertToWorldRot();
			scale = super.convertToWorldScale();
			batch.setColor(super._color.r, super._color.g, super._color.b,
					super._alpha);
			if (this._type == BarType.PROGRESS_BAR_LEFT) {
				batch.draw(_texture, pos[0], pos[1], _anchor.x, _anchor.y,
						super._size_width, super._size_height, scale[0],
						scale[1], MathUtils.toDegrees(rotation), super._left,
						super._top, super._orig_width * this._percent,
						super._orig_height, _flipX, _flipY);
			} else if (this._type == BarType.PROGRESS_BAR_RIGHT) {
				int offsetX = ((int) (super._orig_width * (1f - this._percent)));
				batch.draw(_texture, pos[0] + offsetX, pos[1], _anchor.x,
						_anchor.y, super._size_width, super._size_height,
						scale[0], scale[1], MathUtils.toDegrees(rotation),
						super._left + offsetX, super._top,
						(int) (super._orig_width * this._percent),
						super._orig_height, _flipX, _flipY);
			} else if (this._type == BarType.PROGRESS_BAR_TOP) {
				batch.draw(_texture, pos[0], pos[1], _anchor.x, _anchor.y,
						super._size_width, super._size_height, scale[0],
						scale[1], MathUtils.toDegrees(rotation), super._left,
						super._top, super._orig_width,
						(super._orig_height * this._percent), _flipX, _flipY);
			} else if (this._type == BarType.PROGRESS_BAR_BOTTOM) {
				int offsetY = ((int) (super._orig_height * (1f - this._percent)));
				batch.draw(_texture, pos[0], pos[1] + offsetY, _anchor.x,
						_anchor.y, super._size_width, super._size_height,
						scale[0], scale[1], MathUtils.toDegrees(rotation),
						super._left, super._top
								+ ((int) (super._orig_height * this._percent)),
						super._orig_width,
						(int) (super._orig_height * this._percent), _flipX,
						_flipY);

			}
			batch.resetColor();
		}
	}

	public void setType(BarType type) {
		this._type = type;
	}

	public float getPercent() {
		return this._percent;
	}

	public void setPercent(float p) {
		this._percent = p;
	}

	public enum BarType {
		PROGRESS_BAR_LEFT, PROGRESS_BAR_RIGHT, PROGRESS_BAR_TOP, PROGRESS_BAR_BOTTOM
	}
}
