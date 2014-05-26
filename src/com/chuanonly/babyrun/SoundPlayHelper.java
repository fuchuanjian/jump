package com.chuanonly.babyrun;
import java.io.IOException;
import java.util.HashMap;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

public class SoundPlayHelper {

	// 音效的音量
	int streamVolume;

	// 定义SoundPool 对象
	private SoundPool soundPool;
	private MediaPlayer mMediaPlayer;
	// 定义HASH表
	private HashMap<Integer, Integer> soundPoolMap;

	
	public void initSounds(Context context) {
		// 初始化soundPool 对象,第一个参数是允许有多少个声音流同时播放,第2个参数是声音类型,第三个参数是声音的品质
		soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 100);

		// 初始化HASH表
		soundPoolMap = new HashMap<Integer, Integer>();

		// 获得声音设备和设备音量
		AudioManager mgr = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		mMediaPlayer = MediaPlayer.create(context, R.raw.music_w3);
		mMediaPlayer.setLooping(true);
	}

	
	public void loadSfx(Context context, int raw, int ID) {
		soundPoolMap.put(ID, soundPool.load(context, raw, 1));
	}

	
	public void play(int sound, int uLoop) {
		soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1,
				uLoop, 1f);
	}
	public  void release()
	{
		soundPool.release();
		soundPool = null;
		mMediaPlayer.stop();
		mMediaPlayer.release();
		mMediaPlayer = null;
	}

	public void playBGMusic() {
		if (!mMediaPlayer.isPlaying())
		{			
			mMediaPlayer.start();
		}
	}

	public void stopBGMusic() {
		if (mMediaPlayer.isPlaying())
		{			
			mMediaPlayer.pause();
		}
	}
}

