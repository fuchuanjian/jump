
package loon.action.sprite.painting;

import loon.action.sprite.SpriteBatch;
import loon.core.timer.GameTime;

public class DrawableGameComponent extends GameComponent implements IDrawable {
	
	private boolean _isInitialized;
	private boolean _isVisible;
	private int _drawOrder;

	public DrawableGameComponent(DrawableScreen game) {
		super(game);
		setVisible(true);
	}

	@Override
	public void initialize() {
		if (!_isInitialized) {
			_isInitialized = true;
			loadContent();
		}
	}

	protected void loadContent() {
	}

	protected void unloadContent() {
	}

	@Override
	public final int getDrawOrder() {
		return _drawOrder;
	}

	private ComponentEvent DrawOrder;

	public final void setDrawOrder(int value) {
		_drawOrder = value;
		if (DrawOrder != null) {
			DrawOrder.invoke(this);
		}
	}

	@Override
	public final boolean getVisible() {
		return _isVisible;
	}

	private ComponentEvent Visible;

	public final void setVisible(boolean value) {
		if (_isVisible != value) {
			_isVisible = value;
			if (Visible != null) {
				Visible.invoke(this);
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch, GameTime gameTime) {
	}

	public void setDrawOrder(ComponentEvent drawOrder) {
		DrawOrder = drawOrder;
	}

	public void setVisible(ComponentEvent visible) {
		Visible = visible;
	}

}
