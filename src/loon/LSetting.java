
package loon;

import loon.LGame.LMode;
import loon.core.LSystem;

public class LSetting {

	public int width = LSystem.MAX_SCREEN_WIDTH;

	public int height = LSystem.MAX_SCREEN_HEIGHT;

	public int fps = LSystem.DEFAULT_MAX_FPS;

	public String title;

	public boolean showFPS;

	public boolean showMemory;

	public boolean showLogo;

	public boolean landscape;

	public LMode mode = LMode.Fill;

	public Listener listener;

	public interface Listener {

		void onPause();

		void onResume();

		void onExit();
	}

}
