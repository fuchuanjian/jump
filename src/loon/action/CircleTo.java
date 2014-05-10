package loon.action;

import loon.utils.MathUtils;


public class CircleTo extends ActionEvent {

	private int x;

	private int y;

	private int cx;

	private int cy;

	private int radius;

	private int velocity;

	private float dt;

	public CircleTo(int radius, int velocity) {
		this.radius = radius;
		this.velocity = velocity;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	public void onLoad() {
		this.cx = (int) original.getX();
		this.cy = (int) original.getY();
		this.x = (cx + radius);
		this.y = cy;
	}

	@Override
	public void update(long elapsedTime) {
		dt += MathUtils.max((elapsedTime / 1000), 0.05f);
		this.x = (int) (this.cx + this.radius
				* MathUtils.cos(MathUtils.toRadians(this.velocity * dt)));
		this.y = (int) (this.cy + this.radius
				* MathUtils.sin(MathUtils.toRadians(this.velocity * dt)));
		synchronized (original) {
			original.setLocation(x + offsetX, y + offsetY);
		}
	}

}
