
package loon.action.sprite.node;

import loon.action.sprite.SpriteBatch;
import loon.core.geom.Vector2f;
import loon.core.graphics.LFont;
import loon.utils.MathUtils;

public class LNLabel extends LNNode {

	private LFont _spriteFont;

	private String _text;

	private LabelType _type;

	public LNLabel() {
		this._type = LabelType.TEXT_ALIGNMENT_LEFT;
		this._spriteFont = LFont.getDefaultFont();
		this.setNodeSize(1, 1);
	}

	public LNLabel(String text) {
		this(text, LabelType.TEXT_ALIGNMENT_LEFT);
	}

	public LNLabel(String text, LabelType type) {
		this(text, type, LFont.getDefaultFont());
	}

	public LNLabel(String text, LabelType type, LFont spriteFont) {
		super();
		this._spriteFont = spriteFont;
		this._type = type;
		setString(text);
		this.setNodeSize(_spriteFont.stringWidth(text), _spriteFont.getHeight());
	}

	private float[] pos;

	private float[] scale;

	private float rotation;

	@Override
	public void draw(SpriteBatch batch) {
		if (super._visible) {
			pos = super.convertToWorldPos();
			scale = super.convertToWorldScale();
			rotation = super.convertToWorldRot();
			batch.setColor(super._color.r, super._color.g, super._color.b,
					super._alpha);
			LFont font = batch.getFont();
			batch.setFont(_spriteFont);
			batch.drawString(this._text, pos[0], pos[1], scale[0], scale[1],
					_anchor.x, _anchor.y, MathUtils.toDegrees(rotation),
					batch.getColor());
			batch.setFont(font);
			batch.resetColor();
		}
	}

	public void setText(String text) {
		setString(text);
	}

	public void setString(String text) {
		this._text = text;
		if (this._type == LabelType.TEXT_ALIGNMENT_LEFT) {
			super._anchor = new Vector2f(0f,
					this._spriteFont.stringWidth(this._text) / 2f);
		} else if (this._type == LabelType.TEXT_ALIGNMENT_RIGHT) {
			super._anchor = new Vector2f(
					this._spriteFont.stringWidth(this._text),
					this._spriteFont.stringWidth(this._text) / 2f);
		} else if (this._type == LabelType.TEXT_ALIGNMENT_CENTER) {
			super._anchor = new Vector2f(
					this._spriteFont.stringWidth(this._text) / 2f,
					this._spriteFont.getHeight() / 2f);
		}
	}

	public enum LabelType {
		TEXT_ALIGNMENT_LEFT, TEXT_ALIGNMENT_RIGHT, TEXT_ALIGNMENT_CENTER
	}
}
