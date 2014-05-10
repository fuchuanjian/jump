package loon.action.collision;

import loon.action.ActionBind;
import loon.core.LRelease;



// 自0.3.2版起新增类，用以绑定任意一个LGame对象进行简单的重力牵引。
public class Gravity implements LRelease {

	Object object;

	ActionBind bind;

	boolean enabled;

	float bounce;

	float gadd;

	float g;

	float accelerationX;

	float accelerationY;

	float velocityX;

	float velocityY;

	float angularVelocity;

	String name;

	public Gravity(String name, ActionBind o) {
		this.name = name;
		this.object = o;
		this.bind = o;
		this.enabled = true;
	}

	public Gravity(ActionBind o) {
		this.object = o;
		this.bind = o;
		this.enabled = true;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public float getVelocityX() {
		return this.velocityX;
	}

	public float getVelocityY() {
		return this.velocityY;
	}

	public void setVelocityX(final float velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(final float velocityY) {
		this.velocityY = velocityY;
	}

	public void setVelocity(final float velocity) {
		this.velocityX = velocity;
		this.velocityY = velocity;
	}

	public void setVelocity(final float velocityX, final float velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	public float getAccelerationX() {
		return this.accelerationX;
	}

	public float getAccelerationY() {
		return this.accelerationY;
	}

	public void setAccelerationX(final float accelerationX) {
		this.accelerationX = accelerationX;
	}

	public void setAccelerationY(final float accelerationY) {
		this.accelerationY = accelerationY;
	}

	public void setAcceleration(final float accelerationX,
			final float accelerationY) {
		this.accelerationX = accelerationX;
		this.accelerationY = accelerationY;
	}

	public void setAcceleration(final float acceleration) {
		this.accelerationX = acceleration;
		this.accelerationY = acceleration;
	}

	public void accelerate(final float accelerationX, final float accelerationY) {
		this.accelerationX += accelerationX;
		this.accelerationY += accelerationY;
	}

	public float getAngularVelocity() {
		return this.angularVelocity;
	}

	public void setAngularVelocity(final float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	public ActionBind getBind() {
		return bind;
	}

	public Object getObject() {
		return object;
	}

	public String getName() {
		return name;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getBounce() {
		return bounce;
	}

	public void setBounce(float bounce) {
		this.bounce = bounce;
	}

	public void reset() {
		this.accelerationX = 0;
		this.accelerationY = 0;
		this.gadd = 0;
		this.g = 0;
		this.bounce = 0;
		this.velocityX = 0;
		this.velocityY = 0;
		this.angularVelocity = 0;
	}

	@Override
	public void dispose() {
		this.enabled = false;
		this.bind = null;
	}

}
