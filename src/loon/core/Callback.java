
package loon.core;

public interface Callback<T> {

	public static abstract class Chain<T> implements Callback<T> {

		private Callback<T> _failure;

		public Chain(Callback<T> f) {
			this._failure = f;
		}

		@Override
		public void onFailure(Throwable t) {
			if (_failure != null) {
				_failure.onFailure(t);
			}
		}
	}

	void onSuccess(T result);

	void onFailure(Throwable t);
}
