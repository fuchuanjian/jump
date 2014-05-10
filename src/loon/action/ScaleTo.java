package loon.action;

import loon.utils.MathUtils;


public class ScaleTo extends ActionEvent {

	private float dt;

	private float deltaX, deltaY;

	private float startX, startY;

	private float endX, endY;

	public ScaleTo(float s) {
		this(s, s);
	}

	public ScaleTo(float sx, float sy) {
		this.endX = sx;
		this.endY = sy;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	public void onLoad() {
		if (original != null) {
			startX = original.getScaleX();
			startY = original.getScaleY();
			deltaX = endX - startX;
			deltaY = endY - startY;
		}
	}

	@Override
	public void update(long elapsedTime) {
		if (original != null) {
			synchronized (original) {
				if (original != null) {
					dt += MathUtils.max((elapsedTime / 1000), 0.01f);
					original.setScale(startX + (deltaX * dt), startY
							+ (deltaY * dt));
					isComplete = (deltaX > 0 ? (original.getScaleX() >= endX)
							: (original.getScaleX() <= endX))
							&& (deltaY > 0 ? (original.getScaleY() >= endY)
									: (original.getScaleY() <= endY));
				}
			}
		} else {
			isComplete = true;
		}
	}
}
