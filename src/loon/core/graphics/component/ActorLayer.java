package loon.core.graphics.component;

import java.util.Iterator;
import java.util.List;

import loon.action.ActionBind;
import loon.action.ActionControl;
import loon.action.ActionEvent;
import loon.action.ArrowTo;
import loon.action.CircleTo;
import loon.action.FadeTo;
import loon.action.FireTo;
import loon.action.JumpTo;
import loon.action.MoveTo;
import loon.action.RotateTo;
import loon.action.ScaleTo;
import loon.action.map.Field2D;
import loon.action.sprite.ISprite;
import loon.core.LSystem;
import loon.core.geom.RectBox;
import loon.core.graphics.LContainer;
import loon.core.input.LInput;
import loon.utils.MathUtils;





@SuppressWarnings("rawtypes")
public abstract class ActorLayer extends LContainer {

	private final static int min_size = 48;

	private Field2D tmpField;

	private boolean isBounded;

	protected int cellSize;

	CollisionChecker collisionChecker;

	ActorTreeSet objects;

	long elapsedTime;

	public ActorLayer(int x, int y, int layerWidth, int layerHeight,
			int cellSize) {
		this(x, y, layerWidth, layerHeight, cellSize, true);
	}

	public ActorLayer(int x, int y, int layerWidth, int layerHeight,
			int cellSize, boolean bounded) {
		super(x, y, layerWidth, layerHeight);
		this.collisionChecker = new CollisionManager();
		this.objects = new ActorTreeSet();
		this.cellSize = cellSize;
		this.initialize(layerWidth, layerHeight, cellSize);
		this.isBounded = bounded;
	}

	private void initialize(int width, int height, int cellSize) {
		this.cellSize = cellSize;
		this.collisionChecker.initialize(cellSize);
	}

	public LInput screenInput() {
		return input;
	}

	public int getCellSize() {
		return this.cellSize;
	}

	public void setCellSize(int cellSize) {
		synchronized (collisionChecker) {
			this.cellSize = cellSize;
			this.collisionChecker.initialize(cellSize);
		}
	}

	
	public void addActionEvent(ActionEvent action, ActionBind obj, boolean paused) {
		ActionControl.getInstance().addAction(action, obj, paused);
	}

	
	public void addActionEvent(ActionEvent action, ActionBind obj) {
		ActionControl.getInstance().addAction(action, obj);
	}

	
	public void removeActionEvents(ActionBind actObject) {
		ActionControl.getInstance().removeAllActions(actObject);
	}

	
	public int getActionEventCount() {
		return ActionControl.getInstance().getCount();
	}

	
	public void removeActionEvent(Object tag, ActionBind actObject) {
		ActionControl.getInstance().removeAction(tag, actObject);
	}

	
	public void removeActionEvent(ActionEvent action) {
		ActionControl.getInstance().removeAction(action);
	}

	
	public ActionEvent getActionEvent(Object tag, ActionBind actObject) {
		return ActionControl.getInstance().getAction(tag, actObject);
	}

	
	public void stopActionEvent(ActionBind actObject) {
		ActionControl.getInstance().stop(actObject);
	}

	
	public void pauseActionEvent(boolean pause, ActionBind actObject) {
		ActionControl.getInstance().paused(pause, actObject);
	}

	
	public void pauseActionEvent(boolean pause) {
		ActionControl.getInstance().setPause(pause);
	}

	
	public boolean isPauseActionEvent() {
		return ActionControl.getInstance().isPause();
	}

	
	public void startActionEvent(ActionBind actObject) {
		ActionControl.getInstance().start(actObject);
	}

	
	public void stopActionEvent() {
		ActionControl.getInstance().stop();
	}

	
	public MoveTo callMoveTo(Field2D field, ActionBind o, boolean flag, int x, int y) {
		if (isClose) {
			return null;
		}
		MoveTo move = new MoveTo(field, x, y, flag);
		addActionEvent(move, o);
		return move;
	}

	
	public MoveTo callMoveTo(Field2D field, ActionBind o, int x, int y) {
		return callMoveTo(field, o, true, x, y);
	}

	
	public MoveTo callMoveTo(ActionBind o, boolean flag, int x, int y, int w, int h) {
		if (isClose) {
			return null;
		}
		if (tmpField == null) {
			tmpField = createArrayMap(w, h);
		}
		MoveTo move = new MoveTo(tmpField, x, y, flag);
		addActionEvent(move, o);
		return move;
	}

	
	public MoveTo callMoveTo(ActionBind o, int x, int y, int w, int h) {
		return callMoveTo(o, true, x, y, w, h);
	}

	
	public MoveTo callMoveTo(ActionBind o, int x, int y) {
		return callMoveTo(o, x, y, 32, 32);
	}

	
	public MoveTo callMoveTo(ActionBind o, int x, int y, boolean flag) {
		return callMoveTo(o, flag, x, y, 32, 32);
	}

	
	public FadeTo callFadeTo(ActionBind o, int type, int speed) {
		if (isClose) {
			return null;
		}
		FadeTo fade = new FadeTo(type, speed);
		addActionEvent(fade, o);
		return fade;
	}

	
	public FadeTo callFadeInTo(ActionBind o, int speed) {
		return callFadeTo(o, ISprite.TYPE_FADE_IN, speed);
	}

	
	public FadeTo callFadeOutTo(ActionBind o, int speed) {
		return callFadeTo(o, ISprite.TYPE_FADE_OUT, speed);
	}

	
	public RotateTo callRotateTo(ActionBind o, float angle, float speed) {
		if (isClose) {
			return null;
		}
		RotateTo rotate = new RotateTo(angle, speed);
		addActionEvent(rotate, o);
		return rotate;
	}

	
	public JumpTo callJumpTo(ActionBind o, int j, float g) {
		if (isClose) {
			return null;
		}
		JumpTo jump = new JumpTo(j, g);
		addActionEvent(jump, o);
		return jump;
	}

	
	public CircleTo callCircleTo(ActionBind o, int radius, int velocity) {
		if (isClose) {
			return null;
		}
		CircleTo circle = new CircleTo(radius, velocity);
		addActionEvent(circle, o);
		return circle;
	}

	
	public FireTo callFireTo(ActionBind o, int x, int y, double speed) {
		if (isClose) {
			return null;
		}
		FireTo fire = new FireTo(x, y, speed);
		addActionEvent(fire, o);
		return fire;
	}

	
	public ScaleTo callScaleTo(ActionBind o, float sx, float sy) {
		if (isClose) {
			return null;
		}
		ScaleTo scale = new ScaleTo(sx, sy);
		addActionEvent(scale, o);
		return scale;
	}

	
	public ScaleTo callScaleTo(ActionBind o, float s) {
		return callScaleTo(o, s, s);
	}

	
	public ArrowTo callArrowTo(ActionBind o, float tx, float ty) {
		if (isClose) {
			return null;
		}
		ArrowTo arrow = new ArrowTo(tx, ty);
		addActionEvent(arrow, o);
		return arrow;
	}
	
	
	public Field2D createArrayMap(int tileWidth, int tileHeight) {
		if (isClose) {
			return null;
		}
		tmpField = new Field2D(new int[getHeight() / tileHeight][getWidth()
				/ tileWidth], tileWidth, tileHeight);
		return tmpField;
	}

	
	public void setField2D(Field2D field) {
		if (isClose) {
			return;
		}
		if (field == null) {
			return;
		}
		if (tmpField != null) {
			if ((field.getMap().length == tmpField.getMap().length)
					&& (field.getTileWidth() == tmpField.getTileWidth())
					&& (field.getTileHeight() == tmpField.getTileHeight())) {
				tmpField.set(field.getMap(), field.getTileWidth(),
						field.getTileHeight());
			}
		} else {
			tmpField = field;
		}
	}

	
	@Override
	public Field2D getField2D() {
		return tmpField;
	}

	
	public void addObject(Actor object, float x, float y) {
		if (isClose) {
			return;
		}
		synchronized (objects) {
			if (this.objects.add(object)) {
				object.addLayer(x, y, this);
				this.collisionChecker.addObject(object);
				object.addLayer(this);
			}
		}
	}

	
	public void addObject(Actor object) {
		if (isClose) {
			return;
		}
		addObject(object, object.x(), object.y());
	}

	
	void sendToFront(Actor actor) {
		if (isClose) {
			return;
		}
		if (objects != null) {
			synchronized (objects) {
				if (objects != null) {
					objects.sendToFront(actor);
				}
			}
		}
	}

	
	void sendToBack(Actor actor) {
		if (isClose) {
			return;
		}
		if (objects != null) {
			synchronized (objects) {
				if (objects != null) {
					objects.sendToBack(actor);
				}
			}
		}
	}

	
	public RectBox[] getRandomLayerLocation(int nx, int ny, int nw, int nh,
			int count) {
		if (isClose) {
			return null;
		}
		if (count <= 0) {
			throw new RuntimeException("count <= 0 !");
		}
		int layerWidth = getWidth();
		int layerHeight = getHeight();
		int actorWidth = nw > min_size ? nw : min_size;
		int actorHeight = nh > min_size ? nh : min_size;
		int x = nx / actorWidth;
		int y = ny / actorHeight;
		int row = layerWidth / actorWidth;
		int col = layerHeight / actorHeight;
		RectBox[] randoms = new RectBox[count];
		int oldRx = 0, oldRy = 0;
		int index = 0;
		for (int i = 0; i < count * 100; i++) {
			if (index >= count) {
				return randoms;
			}
			int rx = LSystem.random.nextInt(row);
			int ry = LSystem.random.nextInt(col);
			if (oldRx != rx && oldRy != ry && rx != x && ry != y
					&& rx * actorWidth != nx && ry * actorHeight != ny) {
				boolean stop = false;
				for (int j = 0; j < index; j++) {
					if (randoms[j].x == rx && randoms[j].y == ry && oldRx != x
							&& oldRy != y && rx * actorWidth != nx
							&& ry * actorHeight != ny) {
						stop = true;
						break;
					}
				}
				if (stop) {
					continue;
				}
				randoms[index] = new RectBox(rx * actorWidth, ry * actorHeight,
						actorWidth, actorHeight);
				oldRx = rx;
				oldRy = ry;
				index++;
			}
		}
		return null;
	}

	
	public RectBox[] getRandomLayerLocation(int actorWidth, int actorHeight,
			int count) {
		if (isClose) {
			return null;
		}
		return getRandomLayerLocation(0, 0, actorWidth, actorHeight, count);
	}

	
	public RectBox[] getRandomLayerLocation(Actor actor, int count) {
		if (isClose) {
			return null;
		}
		RectBox rect = actor.getRectBox();
		return getRandomLayerLocation((int) rect.x, (int) rect.y, rect.width,
				rect.height, count);
	}

	
	public RectBox getRandomLayerLocation(Actor actor) {
		if (isClose) {
			return null;
		}
		RectBox[] rects = getRandomLayerLocation(actor, 1);
		if (rects != null) {
			return rects[0];
		}
		return null;
	}

	
	public void removeObject(Actor object) {
		if (isClose) {
			return;
		}
		if (objects.size() == 0) {
			return;
		}
		synchronized (objects) {
			if (this.objects.remove(object)) {
				this.collisionChecker.removeObject(object);
			}
			removeActionEvents(object);
			object.setLayer((ActorLayer) null);
		}
	}

	
	public void removeObject(Class clazz) {
		if (isClose) {
			return;
		}
		if (objects.size() == 0) {
			return;
		}
		synchronized (objects) {
			Iterator it = objects.iterator();
			while (it.hasNext()) {
				Actor actor = (Actor) it.next();
				if (actor == null) {
					continue;
				}
				Class cls = actor.getClass();
				if (clazz == null || clazz == cls || clazz.isInstance(actor)
						|| clazz.equals(cls)) {
					if (this.objects.remove(actor)) {
						this.collisionChecker.removeObject(actor);
					}
					removeActionEvents(actor);
					actor.setLayer((ActorLayer) null);
				}
			}
		}
	}

	
	public void removeObjects(List objects) {
		if (isClose) {
			return;
		}
		synchronized (objects) {
			Iterator iter = objects.iterator();
			while (iter.hasNext()) {
				Actor actor = (Actor) iter.next();
				this.removeObject(actor);
			}
		}
	}

	
	public List getCollisionObjects(Actor actor) {
		if (isClose) {
			return null;
		}
		return getCollisionObjects(actor.getClass());
	}

	
	public void reset() {
		if (isClose) {
			return;
		}
		if (objects != null) {
			synchronized (objects) {
				if (objects != null) {
					objects.clear();
					objects = null;
				}
				if (collisionChecker != null) {
					collisionChecker.dispose();
					collisionChecker.clear();
					collisionChecker = null;
				}
				this.collisionChecker = new CollisionManager();
				this.objects = new ActorTreeSet();
			}
		}
	}

	
	public List getCollisionObjects(Class cls) {
		if (isClose) {
			return null;
		}
		return this.collisionChecker.getObjects(cls);
	}

	
	public List getCollisionObjectsAt(float x, float y, Class cls) {
		if (isClose) {
			return null;
		}
		return this.collisionChecker.getObjectsAt(x, y, cls);
	}

	
	public Actor getOnlyCollisionObjectsAt(float x, float y) {
		if (isClose) {
			return null;
		}
		return objects.getOnlyCollisionObjectsAt(x, y);
	}

	
	public Actor getOnlyCollisionObjectsAt(float x, float y, Object tag) {
		if (isClose) {
			return null;
		}
		return objects.getOnlyCollisionObjectsAt(x, y, tag);
	}

	
	public int size() {
		if (isClose) {
			return 0;
		}
		return this.objects.size();
	}

	public abstract void action(long elapsedTime);

	@Override
	public boolean isBounded() {
		return this.isBounded;
	}

	Actor getSynchronizedObject(float x, float y) {
		if (isClose) {
			return null;
		}
		return objects.getSynchronizedObject(x, y);
	}

	List getIntersectingObjects(Actor actor, Class cls) {
		if (isClose) {
			return null;
		}
		return this.collisionChecker.getIntersectingObjects(actor, cls);
	}

	Actor getOnlyIntersectingObject(Actor object, Class cls) {
		if (isClose) {
			return null;
		}
		return this.collisionChecker.getOnlyIntersectingObject(object, cls);
	}

	List getObjectsInRange(float x, float y, float r, Class cls) {
		if (isClose) {
			return null;
		}
		return this.collisionChecker.getObjectsInRange(x, y, r, cls);
	}

	List getNeighbours(Actor actor, float distance, boolean d, Class cls) {
		if (isClose) {
			return null;
		}
		if (distance < 0) {
			throw new RuntimeException("distance < 0");
		} else {
			return this.collisionChecker.getNeighbours(actor, distance, d, cls);
		}
	}

	int getHeightInPixels() {
		return this.getHeight() * this.cellSize;
	}

	int getWidthInPixels() {
		return this.getWidth() * this.cellSize;
	}

	int toCellCeil(float pixel) {
		return MathUtils.ceil(pixel / this.cellSize);
	}

	int toCellFloor(float pixel) {
		return MathUtils.floor(pixel / this.cellSize);
	}

	float getCellCenter(float c) {
		float cellCenter = (c * this.cellSize) + this.cellSize / 2.0f;
		return cellCenter;
	}

	List getCollisionObjects(float x, float y) {
		if (isClose) {
			return null;
		}
		return collisionChecker.getObjectsAt(x, y, null);
	}

	void updateObjectLocation(Actor object, float oldX, float oldY) {
		if (isClose) {
			return;
		}
		this.collisionChecker.updateObjectLocation(object, oldX, oldY);
	}

	void updateObjectSize(Actor object) {
		if (isClose) {
			return;
		}
		this.collisionChecker.updateObjectSize(object);
	}

	Actor getOnlyObjectAt(Actor object, float dx, float dy, Class cls) {
		if (isClose) {
			return null;
		}
		return this.collisionChecker.getOnlyObjectAt(object, dx, dy, cls);
	}

	ActorTreeSet getObjectsListInPaintO() {
		if (isClose) {
			return null;
		}
		return this.objects;
	}

}
