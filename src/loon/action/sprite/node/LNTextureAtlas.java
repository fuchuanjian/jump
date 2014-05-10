
package loon.action.sprite.node;

import java.util.ArrayList;

import loon.action.sprite.SpriteBatch;
import loon.core.geom.RectBox;
import loon.core.geom.Vector2f;
import loon.core.graphics.LColor;
import loon.core.graphics.opengl.LTexture;
import loon.utils.MathUtils;

public class LNTextureAtlas {

	private ArrayList<RectBox> _rectList;

	private LTexture _texture;

	private int _totalRect;

	public Vector2f anchor = new Vector2f();

	public LNTextureAtlas(LTexture texture, int capacity) {
		this._texture = texture;
		this._totalRect = capacity;
		this._rectList = new ArrayList<RectBox>();
	}

	public void addRect(RectBox rect) {
		this._rectList.add(rect);
	}

	public void draw(int idx, SpriteBatch batch, Vector2f absPos,
			float rotation, Vector2f scale, LColor color) {
		RectBox rect = this._rectList.get(idx);
		batch.setColor(color);
		batch.draw(_texture, absPos.x, absPos.y, anchor.x, anchor.y,
				rect.width, rect.height, scale.x, scale.y,
				MathUtils.toDegrees(rotation), rect.x, rect.y, rect.width,
				rect.height, false, false);
		batch.resetColor();
	}

	public void draw(int idx, SpriteBatch batch, float x, float y,
			float rotation, float sx, float sy, LColor color) {
		RectBox rect = this._rectList.get(idx);
		batch.setColor(color);
		batch.draw(_texture, x, y, anchor.x, anchor.y, rect.width, rect.height,
				sx, sy, MathUtils.toDegrees(rotation), rect.x, rect.y,
				rect.width, rect.height, false, false);
		batch.resetColor();
	}

	public void resetRect() {
		this._rectList.clear();
	}

	public LTexture getTexture() {
		return this._texture;
	}

	public int getTotalRect() {
		return _totalRect;
	}
}
