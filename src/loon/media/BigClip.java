
package loon.media;

import android.media.MediaPlayer;

public class BigClip extends AndroidSound<MediaPlayer> {

	private final Audio audio;
	private final Audio.Resolver<MediaPlayer> resolver;
	private int position;

	public BigClip(Audio audio,
			Audio.Resolver<MediaPlayer> resolver) {
		this.audio = audio;
		this.resolver = resolver;
		resolve();
	}

	@Override
	public void onLoaded(MediaPlayer impl) {
		super.onLoaded(impl);
		impl.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				audio.onStopped(BigClip.this);
			}
		});
	}

	@Override
	protected boolean playingImpl() {
		return impl.isPlaying();
	}

	@Override
	protected boolean playImpl() {
		audio.onPlaying(this);
		impl.seekTo(position);
		impl.start();
		position = 0;
		return true;
	}

	@Override
	protected void stopImpl() {
		audio.onStopped(this);
		impl.pause();
	}

	@Override
	protected void setLoopingImpl(boolean looping) {
		impl.setLooping(looping);
	}

	@Override
	protected void setVolumeImpl(float volume) {
		impl.setVolume(volume, volume);
	}

	@Override
	protected void releaseImpl() {
		if (impl.isPlaying())
			impl.stop();
		impl.release();
	}

	private void resolve() {
		resolver.resolve(BigClip.this);
	}

	@Override
	void onPause() {
		if (impl != null) {
			if (impl.isPlaying()) {
				position = impl.getCurrentPosition();
			}
			impl.release();
			impl = null;
		}
	}

	@Override
	void onResume() {
		resolve();
	}
}
