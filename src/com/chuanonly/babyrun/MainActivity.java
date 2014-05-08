package com.chuanonly.babyrun;

import loon.LGame;
import loon.LSetting;
import loon.core.graphics.opengl.LTexture;

public class MainActivity extends LGame {

	@Override
	public void onGamePaused() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameResumed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMain() {
		LTexture.ALL_LINEAR = true;
		LSetting setting = new LSetting();
		setting.width = 800;
		setting.height = 480;
		setting.fps = 20;
		setting.landscape = true;
		setting.showFPS = false;
		register(setting, MainGame.class);
	}

}
