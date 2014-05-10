package loon.core.input;

import loon.action.map.Config;
import loon.action.sprite.ISprite;
import loon.action.sprite.effect.ArcEffect;
import loon.action.sprite.effect.CrossEffect;
import loon.action.sprite.effect.FadeEffect;
import loon.action.sprite.effect.PShadowEffect;
import loon.action.sprite.effect.SplitEffect;
import loon.core.LSystem;
import loon.core.graphics.LColor;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;
import loon.core.graphics.opengl.TextureUtils;
import loon.utils.MathUtils;




public class LTransition {

	
	public final static LTransition newCrossRandom() {
		return newCrossRandom(LColor.black);
	}

	
	public final static LTransition newCrossRandom(LColor c) {
		return newCross(MathUtils.random(0, 1), TextureUtils
				.createTexture(LSystem.screenRect.width,
						LSystem.screenRect.height, c));
	}


	
	public final static LTransition newCross(final int c, final LTexture texture) {

		if (GLEx.self != null) {

			final LTransition transition = new LTransition();

			transition.setTransitionListener(new TransitionListener() {

				final CrossEffect cross = new CrossEffect(c, texture);

				@Override
				public void draw(GLEx g) {
					cross.createUI(g);
				}

				@Override
				public void update(long elapsedTime) {
					cross.update(elapsedTime);
				}

				@Override
				public boolean completed() {
					return cross.isComplete();
				}

				@Override
				public void dispose() {
					cross.dispose();
				}

			});
			transition.setDisplayGameUI(true);
			transition.code = 1;
			return transition;
		}
		return null;
	}


	
	public final static LTransition newArc() {
		return newArc(LColor.black);
	}

	
	public final static LTransition newArc(final LColor c) {

		if (GLEx.self != null) {

			final LTransition transition = new LTransition();

			transition.setTransitionListener(new TransitionListener() {

				final ArcEffect arc = new ArcEffect(c);

				@Override
				public void draw(GLEx g) {
					arc.createUI(g);
				}

				@Override
				public void update(long elapsedTime) {
					arc.update(elapsedTime);
				}

				@Override
				public boolean completed() {
					return arc.isComplete();
				}

				@Override
				public void dispose() {
					arc.dispose();
				}

			});
			transition.setDisplayGameUI(true);
			transition.code = 1;
			return transition;
		}
		return null;
	}


	
	public final static LTransition newSplitRandom(LTexture texture) {
		return newSplit(MathUtils.random(0, Config.TDOWN), texture);
	}

	
	public final static LTransition newSplitRandom(LColor c) {
		return newSplitRandom(TextureUtils.createTexture(
				LSystem.screenRect.width, LSystem.screenRect.height, c));
	}


	
	public final static LTransition newSplit(final int d, final LTexture texture) {

		if (GLEx.self != null) {

			final LTransition transition = new LTransition();

			transition.setTransitionListener(new TransitionListener() {

				final SplitEffect split = new SplitEffect(texture, d);

				@Override
				public void draw(GLEx g) {
					split.createUI(g);
				}

				@Override
				public void update(long elapsedTime) {
					split.update(elapsedTime);
				}

				@Override
				public boolean completed() {
					return split.isComplete();
				}

				@Override
				public void dispose() {
					split.dispose();
				}

			});
			transition.setDisplayGameUI(true);
			transition.code = 1;
			return transition;
		}
		return null;
	}

	
	public final static LTransition newFadeIn() {
		return LTransition.newFade(ISprite.TYPE_FADE_IN);
	}

	
	public final static LTransition newFadeOut() {
		return LTransition.newFade(ISprite.TYPE_FADE_OUT);
	}

	
	public final static LTransition newFade(int type) {
		return LTransition.newFade(type, LColor.black);
	}

	
	public final static LTransition newFade(final int type, final LColor c) {
		if (GLEx.self != null) {
			final LTransition transition = new LTransition();

			transition.setTransitionListener(new TransitionListener() {

				final FadeEffect fade = FadeEffect.getInstance(type, c);

				@Override
				public void draw(GLEx g) {
					fade.createUI(g);
				}

				@Override
				public void update(long elapsedTime) {
					fade.update(elapsedTime);
				}

				@Override
				public boolean completed() {
					return fade.isStop();
				}

				@Override
				public void dispose() {
					fade.dispose();
				}

			});
			transition.setDisplayGameUI(true);
			transition.code = 1;
			return transition;
		}
		return null;
	}

	
	public final static LTransition newPShadow(String fileName, float alhpa) {
		PShadowEffect shadow = new PShadowEffect(fileName);
		shadow.setAlpha(alhpa);
		return newPShadow(shadow);
	}

	
	public final static LTransition newPShadow(String fileName) {
		return newPShadow(fileName, 0.5f);
	}

	
	public final static LTransition newPShadow(final PShadowEffect effect) {
		if (GLEx.self != null) {

			final LTransition transition = new LTransition();

			transition.setTransitionListener(new TransitionListener() {

				@Override
				public void draw(GLEx g) {
					effect.createUI(g);
				}

				@Override
				public void update(long elapsedTime) {
					effect.update(elapsedTime);
				}

				@Override
				public boolean completed() {
					return effect.isComplete();
				}

				@Override
				public void dispose() {
					effect.dispose();
				}

			});
			transition.setDisplayGameUI(true);
			transition.code = 1;
			return transition;
		}
		return null;
	}

	public final static LTransition newEmpty() {

		final LTransition transition = new LTransition();

		transition.setTransitionListener(new TransitionListener() {

			@Override
			public void draw(GLEx g) {
			}

			@Override
			public void update(long elapsedTime) {
			}

			@Override
			public boolean completed() {
				return true;
			}

			@Override
			public void dispose() {
			}

		});

		transition.setDisplayGameUI(true);
		transition.code = 1;
		return transition;

	}

	public static interface TransitionListener {

		public void update(long elapsedTime);

		public void draw(GLEx g);

		public boolean completed();

		public void dispose();
	}

	// 是否在在启动过渡效果同时显示游戏画面（即是否顶层绘制过渡画面，底层同时绘制标准游戏画面）
	boolean isDisplayGameUI;

	int code;

	TransitionListener listener;

	public void setDisplayGameUI(boolean s) {
		this.isDisplayGameUI = s;
	}

	public boolean isDisplayGameUI() {
		return this.isDisplayGameUI;
	}

	public void setTransitionListener(TransitionListener l) {
		this.listener = l;
	}

	public TransitionListener getTransitionListener() {
		return this.listener;
	}

	final void update(long elapsedTime) {
		if (listener != null) {
			listener.update(elapsedTime);
		}
	}

	final void draw(GLEx g) {
		if (listener != null) {
			listener.draw(g);
		}
	}

	final boolean completed() {
		if (listener != null) {
			return listener.completed();
		}
		return false;
	}

	final void dispose() {
		if (listener != null) {
			listener.dispose();
		}
	}
}
