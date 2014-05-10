
package loon.action.sprite.node;

import loon.action.sprite.SpriteBatch;
import loon.core.geom.RectBox;

public class LNLabelAtlas extends LNAtlasNode {
	
	private int _charWidth;
	
	private char _startchar;
	
	private String _text;
	
	private LabelType _type;
	
	public LNFrameStruct fs;

	public LNLabelAtlas() {
		this._type = LabelType.TEXT_ALIGNMENT_LEFT;
	}

	public LNLabelAtlas(String fsName, LabelType type, String text,
			char startchar, int itemWidth, int itemHeight, int charWidth) {
		super(fsName, itemWidth, itemHeight);
		this.fs = LNDataCache.getFrameStruct(fsName);
		super._left = (int) this.fs._textCoords.x;
		super._top = (int) this.fs._textCoords.y;
		this._type = type;
		this._charWidth = charWidth;
		this._startchar = startchar;
		this.setString(text);
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
			int size = _text.length();
			if (this._type == LabelType.TEXT_ALIGNMENT_LEFT) {
				for (int i = 0; i < size; i++) {
					super._textureAtlas.draw(i, batch, pos[0] + i
							* this._charWidth, pos[1], rotation, scale[0],
							scale[1], batch.getColor());
				}
			} else if (this._type == LabelType.TEXT_ALIGNMENT_RIGHT) {
				for (int j = 0; j < size; j++) {
					super._textureAtlas.draw(j, batch, pos[0]
							- (size * this._charWidth) + (j * this._charWidth),
							pos[1], rotation, scale[0], scale[1],
							batch.getColor());
				}
			} else {
				for (int k = 0; k < size; k++) {
					super._textureAtlas.draw(k, batch,
					pos[0] - ((size * this._charWidth) / 2)
							+ (k * this._charWidth), pos[1], rotation,
							scale[0], scale[1], batch.getColor());
				}
			}
		}
	}

	public void setString(String text) {
		this._text = text;
		super._textureAtlas.resetRect();
		for (int i = 0; i < this._text.length(); i++) {
			int num2 = this._text.charAt(i) - this._startchar;
			super._textureAtlas.addRect(new RectBox((super._left)
					+ (num2 * super._itemWidth), super._top,
					super._itemWidth, super._itemHeight));
		}
	}

	public enum LabelType {
		TEXT_ALIGNMENT_LEFT, TEXT_ALIGNMENT_RIGHT, TEXT_ALIGNMENT_CENTER
	}
}
