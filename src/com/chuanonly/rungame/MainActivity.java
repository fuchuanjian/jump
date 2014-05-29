package com.chuanonly.rungame;

import java.lang.ref.WeakReference;

import loon.LGame;
import loon.LSetting;
import loon.core.graphics.opengl.LTexture;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends LGame {

	private AdView mAdView;
	private static WeakReference<Handler> mHandlerRef;
	private SoundPlayHelper mSoundPlay;

	public static final int MSG_SHOW_AD = 0;
	public static final int MSG_HIDE_AD = 1;
	public static final int MSG_SOUND = 2;

	public static final int MUSIC_START = 3;
	public static final int MUSIC_STOP = 4;

	// soundid
	public static final int SOUND_BUTTON = 0;
	public static final int SOUND_JUMPS = 1;
	public static final int SOUND_JUMPB = 11;
	public static final int SOUND_SUCEESS = 2;
	public static final int SOUND_COLLOD = 3;
	// public static final int SOUND_LAND = 4;
	public static final int SOUND_SPEED_UP = 5;
	public static final int SOUND_PICK_STAR = 6;

	@Override
	public void onGamePaused() {
		mHandler.sendEmptyMessage(MUSIC_STOP);
	}

	@Override
	public void onGameResumed() {
		mHandler.sendEmptyMessageDelayed(MUSIC_START, 1500);
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

		if (Util.isNetworkAvailable(APP.getContext())) {
			loadAd();
		}
		_bottomLayout.bringToFront();
		_bottomLayout.setVisibility(View.GONE);
		mHandlerRef = new WeakReference<Handler>(mHandler);
		mSoundPlay = new SoundPlayHelper();
		mSoundPlay.initSounds(this);
		mSoundPlay.loadSfx(this, R.raw.button, SOUND_BUTTON);
		mSoundPlay.loadSfx(this, R.raw.jumps, SOUND_JUMPS);
		mSoundPlay.loadSfx(this, R.raw.jumpb, SOUND_JUMPB);
		mSoundPlay.loadSfx(this, R.raw.win, SOUND_SUCEESS);
		mSoundPlay.loadSfx(this, R.raw.speed_up, SOUND_SPEED_UP);
		mSoundPlay.loadSfx(this, R.raw.sfx_hit, SOUND_COLLOD);
		mSoundPlay.loadSfx(this, R.raw.star_pickup, SOUND_PICK_STAR);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == MSG_SHOW_AD) {
				int cnt = Util.getLoginCnt();
				if (cnt >= 0) {
					if (mAdView == null) {
						loadAd();
					}
					_bottomLayout.setVisibility(View.VISIBLE);
				}
			} else if (msg.what == MSG_HIDE_AD) {
				_bottomLayout.setVisibility(View.GONE);
			} else if (msg.what == MSG_SOUND) {
				int soundId = msg.arg1;
				mSoundPlay.play(soundId, 0);
			} else if (msg.what == MUSIC_START) {
				if (Util.isSoundSettingOn()) {
					mSoundPlay.playBGMusic();
				}
			} else if (msg.what == MUSIC_STOP) {
				mHandler.removeMessages(MUSIC_START);
				mSoundPlay.stopBGMusic();
			}
		};
	};

	private void loadAd() {
		try {
			// mAdView = new AdView(this, AdSize.BANNER, "a1534d6f6acb6ed");
			mAdView = new AdView(this);
			mAdView.setAdUnitId("a153848e9db1b45");
			mAdView.setAdSize(AdSize.BANNER);
			_bottomLayout.addView(mAdView);
			AdRequest adRequest = new AdRequest.Builder().build();
			mAdView.loadAd(adRequest);
			mAdView.setAdListener(new AdListener() {
				@Override
				public void onAdClosed() {
					_bottomLayout.setVisibility(View.GONE);
					MainGame mainGame = MainGame.get();
					if (mainGame != null) {
						int stars = Util.getIntFromSharedPref(Util.TOKEN, 10);
						stars += 20;
						Util.setIntToSharedPref(Util.TOKEN, stars);
						mainGame.m_iWorldTokens = stars;
						Util.showToast("+20 Stars");
					}
					super.onAdClosed();
				}
			});
			// mAdView.loadAd(new AdRequest());
			// mAdView.setAdListener(new AdListener() {
			// @Override
			// public void onReceiveAd(Ad arg0) {
			// }
			//
			// @Override
			// public void onPresentScreen(Ad arg0) {
			// }
			//
			// @Override
			// public void onLeaveApplication(Ad arg0) {
			// }
			//
			// @Override
			// public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
			// }
			//
			// @Override
			// public void onDismissScreen(Ad arg0) {
			// //// Util.setLoginCnt(-3);
			// _bottomLayout.setVisibility(View.GONE);
			// MainGame mainGame = MainGame.get();
			// if (mainGame != null)
			// {
			// int stars = Util.getIntFromSharedPref(Util.TOKEN, 10);
			// stars += 5;
			// Util.setIntToSharedPref(Util.TOKEN, stars);
			// mainGame.m_iWorldTokens = stars;
			// Util.showToast("+5 Stars");
			// }
			// }
			// });
		} catch (Exception e) {
		}
		
	}

	public static void showAd() {
		if (Util.isNetworkAvailable(APP.getContext())) {
			Handler h = mHandlerRef.get();
			if (h != null) {
				h.sendEmptyMessage(MSG_SHOW_AD);
			}
		}
	}

	public static void hideAd() {
		Handler h = mHandlerRef.get();
		if (h != null) {
			h.sendEmptyMessage(MSG_HIDE_AD);
		}
	}

	public static void playSound(int soundID) {
		if (Util.isSoundSettingOn()) {
			Handler h = mHandlerRef.get();
			if (h != null) {
				Message msg = h.obtainMessage();
				msg.what = MSG_SOUND;
				msg.arg1 = soundID;
				msg.sendToTarget();
			}
		}
	}

	public static void handlerMessage(int mgsWhat) {
		Handler h = mHandlerRef.get();
		if (h != null) {
			h.sendEmptyMessage(mgsWhat);
		}
	}

	@Override
	public void onPause() {
		if (mAdView != null) {
			mAdView.pause();
		}
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mAdView != null) {
			mAdView.resume();
		}
	}

	@Override
	public void onDestroy() {
		if (mAdView != null) {
			mAdView.destroy();
		}
		super.onDestroy();
	}
}
