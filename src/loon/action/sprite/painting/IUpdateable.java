
package loon.action.sprite.painting;

import loon.core.timer.GameTime;

public interface IUpdateable
{

	void update(GameTime gameTime);

	boolean getEnabled();

	int getUpdateOrder();

}
