
package loon.action.sprite.painting;

import loon.action.sprite.SpriteBatch;
import loon.core.timer.GameTime;

public interface IDrawable {

	int getDrawOrder();

	boolean getVisible();

	void draw(SpriteBatch batch, GameTime gameTime);

}
