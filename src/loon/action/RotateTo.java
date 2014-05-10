package loon.action;


public class RotateTo extends ActionEvent {

	private float dstAngle;

	private float diffAngle;

	private float startAngle;

	private float speed;

	public RotateTo(float dstAngle, float speed) {
		this.dstAngle = dstAngle;
		if (this.dstAngle > 360) {
			this.dstAngle = 360;
		} else if (this.dstAngle < 0) {
			this.dstAngle = 0;
		}
		this.speed = speed;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	public void onLoad() {
		startAngle = original.getRotation();
		diffAngle = 1;
	}

	@Override
	public void update(long elapsedTime) {
		startAngle += diffAngle * speed;
		original.setRotation(startAngle);
		if (startAngle >= dstAngle) {
			isComplete = true;
		}
	}

}
