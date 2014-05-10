
package loon.core.graphics.opengl.particle;

import loon.core.graphics.opengl.LTexture;

public interface ParticleEmitter {

	public void update(ParticleSystem system, long delta);

	public boolean completed();
	
	public void wrapUp();
	
	public void updateParticle(Particle particle, long delta);
	
	public boolean isEnabled();
	
	public void setEnabled(boolean enabled);
	
	public boolean useAdditive();
	
	public LTexture getImage();

	public boolean isOriented();
	
	public boolean usePoints(ParticleSystem system);
	
	public void resetState();
}
