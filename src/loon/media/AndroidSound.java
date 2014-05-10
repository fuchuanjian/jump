
package loon.media;

abstract class AndroidSound<I> extends SoundImpl<I> {

	abstract void onPause();

	abstract void onResume();
}