package loon.action;

import loon.action.sprite.ISprite;


public class FadeTo extends ActionEvent {

	public float time;

	public float currentFrame;

	public int type;

	private float opacity;

	public FadeTo(int type, int speed) {
		this.type = type;
		this.setSpeed(speed);
	}

	public int getIType() {
		return type;
	}

	public void setIType(int type) {
		this.type = type;
	}

	void setOpacity(float opacity) {
		this.opacity = opacity;
	}

	public float getOpacity() {
		return opacity;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	public float getSpeed() {
		return time;
	}

	public void setSpeed(int delay) {
		this.time = delay;
		if (type == ISprite.TYPE_FADE_IN) {
			this.currentFrame = this.time;
		} else {
			this.currentFrame = 0;
		}
	}

	@Override
	public void onLoad() {

	}

	@Override
	public void update(long elapsedTime) {
		if (type == ISprite.TYPE_FADE_IN) {
			currentFrame--;
			if (currentFrame == 0) {
				setOpacity(0);
				isComplete = true;
			}
		} else {
			currentFrame++;
			if (currentFrame == time) {
				setOpacity(0);
				isComplete = true;
			}
		}
		setOpacity((currentFrame / time) * 255);
		if (opacity > 0) {
			original.setAlpha((opacity / 255));
		}
	}

}
