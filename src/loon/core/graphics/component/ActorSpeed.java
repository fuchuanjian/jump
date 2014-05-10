package loon.core.graphics.component;



public abstract class ActorSpeed extends Actor {

	private Speed speed = new Speed();

	protected float x;

	protected float y;

	public ActorSpeed() {
	}

	public ActorSpeed(Speed speed) {
		this.speed = speed;
	}

	public void move() {
		this.x += this.speed.getX();
		this.y += this.speed.getY();
		if (this.x >= getLLayer().getWidth()) {
			this.x = 0.0f;
		}
		if (this.x < 0.0f) {
			this.x = (getLLayer().getWidth() - 1);
		}
		if (this.y >= getLLayer().getHeight()) {
			this.y = 0.0f;
		}
		if (this.y < 0.0f) {
			this.y = (getLLayer().getHeight() - 1);
		}
		setLocation(this.x, this.y);
	}

	@Override
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
		super.setLocation(x, y);
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		super.setLocation(x, y);
	}

	public void increaseSpeed(Speed s) {
		this.speed.add(s);
	}

	public Speed getSpeed() {
		return this.speed;
	}
}
