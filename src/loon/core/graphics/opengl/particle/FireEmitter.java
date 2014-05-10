
package loon.core.graphics.opengl.particle;

import loon.core.graphics.opengl.LTexture;
import loon.utils.MathUtils;


public class FireEmitter implements ParticleEmitter {
	
	private int x;

	private int y;
	
	private int interval = 50;

	private long timer;

	private float size = 40;
	
	public FireEmitter() {
	}

	public FireEmitter(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public FireEmitter(int x, int y, float size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	@Override
	public void update(ParticleSystem system, long delta) {
		timer -= delta;
		if (timer <= 0) {
			timer = interval;
			Particle p = system.getNewParticle(this, 1000);
			p.setColor(1, 1, 1, 0.5f);
			p.setPosition(x, y);
			p.setSize(size);
			float vx =  (-0.02f + (MathUtils.random() * 0.04f));
			float vy =  (-(MathUtils.random() * 0.15f));
			p.setVelocity(vx,vy,1.1f);
		}
	}

	@Override
	public void updateParticle(Particle particle, long delta) {
		if (particle.getLife() > 600) {
			particle.adjustSize(0.07f * delta);
		} else {
			particle.adjustSize(-0.04f * delta * (size / 40.0f));
		}
		float c = 0.002f * delta;
		particle.adjustColor(0,-c/2,-c*2,-c/4);
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void setEnabled(boolean enabled) {
	}

	@Override
	public boolean completed() {
		return false;
	}

	@Override
	public boolean useAdditive() {
		return false;
	}

	@Override
	public LTexture getImage() {
		return null;
	}

	@Override
	public boolean usePoints(ParticleSystem system) {
		return false;
	}

	@Override
	public boolean isOriented() {
		return false;
	}

	@Override
	public void wrapUp() {
	}

	@Override
	public void resetState() {
	}
}
