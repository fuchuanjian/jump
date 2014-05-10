
package loon.action.sprite.node;

import loon.core.graphics.opengl.LTexture;

public class LNAtlasNode extends LNNode {

	protected int _itemHeight;

	protected int _itemsPerColumn;

	protected int _itemsPerRow;

	protected int _itemWidth;

	protected LNTextureAtlas _textureAtlas;

	public LNAtlasNode() {
		this._itemsPerRow = 0;
		this._itemsPerColumn = 0;
		this._itemWidth = 0;
		this._itemHeight = 0;
	}

	public LNAtlasNode(String fsName, int tileWidth, int tileHeight) {
		try {
			this._itemWidth = tileWidth;
			this._itemHeight = tileHeight;
			LTexture texture = LNDataCache.getFrameStruct(fsName)._texture;
			this._itemsPerRow = texture.getWidth() / tileWidth;
			this._itemsPerColumn = texture.getHeight() / tileHeight;
			this._textureAtlas = new LNTextureAtlas(texture, this._itemsPerRow
					* this._itemsPerColumn);
		} catch (Exception ex) {
			throw new RuntimeException("LNAtlasNode Exception in the data load : " + fsName);
		}
	}
}
