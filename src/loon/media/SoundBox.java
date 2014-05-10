
package loon.media;

import java.util.HashMap;

import loon.core.Assets;
import loon.utils.CollectionUtils;

public abstract class SoundBox {

	private HashMap<String, Sound> sounds = new HashMap<String, Sound>(
			CollectionUtils.INITIAL_CAPACITY);

	public void playSound(String path) {
		playSound(path, false);
	}

	public void playSound(String path, boolean loop) {
		Sound sound = sounds.get(path);
		if (sound == null) {
			sound = Assets.getSound(path);
			sounds.put(path, sound);
		} else {
			sound.stop();
		}
		sound.setLooping(loop);
		sound.play();
	}

	public void volume(String path, float volume) {
		Sound sound = sounds.get(path);
		if (sound != null) {
			sound.setVolume(volume);
		}
	}

	public void stopSound(String path) {
		Sound sound = sounds.get(path);
		if (sound != null) {
			sound.stop();
		}
	}

	public void stopSound() {
		for (Sound s : sounds.values()) {
			if (s != null) {
				s.stop();
			}
		}
	}

	public void release() {
		for (Sound s : sounds.values()) {
			if (s != null) {
				s.release();
			}
		}
		sounds.clear();
	}
}
