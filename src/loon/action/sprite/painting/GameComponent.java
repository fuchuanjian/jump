
package loon.action.sprite.painting;

import loon.action.sprite.SpriteBatch;
import loon.core.LRelease;
import loon.core.timer.GameTime;

public class GameComponent implements IGameComponent, IUpdateable,
		Comparable<GameComponent>, LRelease {
	
	private DrawableScreen _game;
	
	private int _updateOrder;
	
	private boolean _enabled;

	public GameComponent(DrawableScreen game) {
		_game = game;
		setEnabled(true);
	}

	public final DrawableScreen getGame() {
		return _game;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void update(GameTime gameTime) {
	}

	public final SpriteBatch getGraphicsDevice() {
		return _game.getSpriteBatch();
	}

	@Override
	public final boolean getEnabled() {
		return _enabled;
	}

	public DrawableEvent EnabledChanged;

	public final void setEnabled(boolean value) {
		_enabled = value;
		raise(EnabledChanged);
		onEnabledChanged(this);
	}

	@Override
	public final int getUpdateOrder() {
		return _updateOrder;
	}

	public DrawableEvent UpdateOrder;

	public final void setUpdateOrder(int value) {
		_updateOrder = value;
		raise(UpdateOrder);
		onUpdateOrderChanged(this);
	}

	private void raise(DrawableEvent event) {
		if (event != null) {
			event.invoke();
		}
	}

	protected void onUpdateOrderChanged(GameComponent cmp) {
	}

	protected void onEnabledChanged(GameComponent cmp) {
	}

	protected void dispose(boolean disposing) {
	}

	@Override
	public void dispose() {
		dispose(true);
	}

	@Override
	public final int compareTo(GameComponent other) {
		return other.getUpdateOrder() - this.getUpdateOrder();
	}

	public DrawableEvent getEnabledChanged() {
		return EnabledChanged;
	}

	public void setEnabledChanged(DrawableEvent enabledChanged) {
		EnabledChanged = enabledChanged;
	}

	public void setUpdateOrder(DrawableEvent updateOrder) {
		UpdateOrder = updateOrder;
	}

}
