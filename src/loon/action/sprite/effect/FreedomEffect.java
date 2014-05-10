package loon.action.sprite.effect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import loon.action.sprite.ISprite;
import loon.core.LObject;
import loon.core.LSystem;
import loon.core.geom.RectBox;
import loon.core.graphics.opengl.GLEx;
import loon.core.graphics.opengl.LTexture;
import loon.core.timer.LTimer;
import loon.utils.MathUtils;



public class FreedomEffect extends LObject implements ISprite {

	
	private static final long serialVersionUID = 1L;

	private int x, y, width, height, count, layer;

	private LTimer timer;

	private LTexture texture;

	private IKernel[] kernels;

	private boolean visible = true, dirty;

	private ArrayList<LTexture> tex2ds = new ArrayList<LTexture>(10);

	
	public static FreedomEffect getSnowEffect() {
		return FreedomEffect.getSnowEffect(60);
	}

	
	public static FreedomEffect getSnowEffect(int count) {
		return FreedomEffect.getSnowEffect(count, 0, 0);
	}

	
	public static FreedomEffect getSnowEffect(int count, int x, int y) {
		return FreedomEffect.getSnowEffect(count, x, y,
				LSystem.screenRect.width, LSystem.screenRect.height);
	}

	
	public static FreedomEffect getSnowEffect(int count, int x, int y, int w,
			int h) {
		return new FreedomEffect(SnowKernel.class, count, 4, x, y, w, h);
	}

	
	public static FreedomEffect getRainEffect() {
		return FreedomEffect.getRainEffect(60);
	}

	
	public static FreedomEffect getRainEffect(int count) {
		return FreedomEffect.getRainEffect(count, 0, 0);
	}

	
	public static FreedomEffect getRainEffect(int count, int x, int y) {
		return FreedomEffect.getRainEffect(count, x, y,
				LSystem.screenRect.width, LSystem.screenRect.height);
	}

	
	public static FreedomEffect getRainEffect(int count, int x, int y, int w,
			int h) {
		return new FreedomEffect(RainKernel.class, count, 3, x, y, w, h);
	}

	
	public static FreedomEffect getPetalEffect() {
		return FreedomEffect.getPetalEffect(25);
	}

	
	public static FreedomEffect getPetalEffect(int count) {
		return FreedomEffect.getPetalEffect(count, 0, 0);
	}

	
	public static FreedomEffect getPetalEffect(int count, int x, int y) {
		return FreedomEffect.getPetalEffect(count, x, y,
				LSystem.screenRect.width, LSystem.screenRect.height);
	}

	
	public static FreedomEffect getPetalEffect(int count, int x, int y, int w,
			int h) {
		return new FreedomEffect(PetalKernel.class, count, 1, x, y, w, h);
	}

	public FreedomEffect(Class<?> clazz, int count, int limit) {
		this(clazz, count, limit, 0, 0);
	}

	public FreedomEffect(Class<?> clazz, int count, int limit, int x, int y) {
		this(clazz, count, limit, x, y, LSystem.screenRect.width,
				LSystem.screenRect.height);
	}

	public FreedomEffect(Class<?> clazz, int count, int limit, int x, int y,
			int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.count = count;
		this.timer = new LTimer(80);
		this.kernels = (IKernel[]) Array.newInstance(clazz, count);
		try {
			Constructor<?> constructor = clazz
					.getDeclaredConstructor(new Class[] { int.class, int.class,
							int.class });
			for (int i = 0; i < count; i++) {
				int no = MathUtils.random(0, limit);
				kernels[i] = (IKernel) constructor.newInstance(new Object[] {
						new Integer(no), new Integer(w), new Integer(h) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(long elapsedTime) {
		if (visible && timer.action(elapsedTime)) {
			for (int i = 0; i < count; i++) {
				kernels[i].update();
			}
			dirty = true;
		}
	}

	@Override
	public void createUI(GLEx g) {
		if (visible) {
			if (dirty) {
				tex2ds.clear();
				for (int i = 0; i < count; i++) {
					texture = kernels[i].get();
					if (!tex2ds.contains(texture)) {
						tex2ds.add(texture);
						texture.glBegin();
					}
					kernels[i].draw(g);
				}
				for (int i = 0; i < tex2ds.size(); i++) {
					texture = tex2ds.get(i);
					texture.newBatchCache(true);
					texture.postLastBatchCache();
				}
				dirty = false;
			} else {
				for (int i = 0; i < tex2ds.size(); i++) {
					texture = tex2ds.get(i);
					texture.postLastBatchCache();
				}
			}
		}
	}

	public long getDelay() {
		return timer.getDelay();
	}

	public void setDelay(long delay) {
		timer.setDelay(delay);
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public IKernel[] getKernels() {
		return kernels;
	}

	public void setKernels(IKernel[] kernels) {
		this.kernels = kernels;
	}

	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public void setLayer(int layer) {
		this.layer = layer;
	}

	@Override
	public RectBox getCollisionBox() {
		return getRect(x, y, width, height);
	}

	@Override
	public int x() {
		return x;
	}

	@Override
	public int y() {
		return y;
	}

	@Override
	public LTexture getBitmap() {
		return null;
	}

	@Override
	public void dispose() {
		this.visible = false;
		if (kernels != null) {
			for (int i = 0; i < kernels.length; i++) {
				kernels[i].dispose();
				kernels[i] = null;
			}
		}
		tex2ds.clear();
	}
}
