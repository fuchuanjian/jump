
package loon.action.sprite;

import loon.action.map.TileMap;

public abstract class SimpleObject extends SpriteBatchObject {

	public SimpleObject(float x, float y, float w, float h,
			Animation animation, TileMap tiles) {
		super(x, y, w, h, animation, tiles);
	}

	public SimpleObject(float x, float y, Animation animation, TileMap tiles) {
		super(x, y, animation, tiles);
	}

	@Override
	public void update(long elapsedTime) {
		animation.update(elapsedTime);
	}
}
