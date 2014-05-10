package loon.action.map;

import java.util.Iterator;
import java.util.LinkedList;

import loon.core.geom.Vector2f;



public class AStarFinderPool implements Runnable {

	private Field2D field;

	private Thread pathfinderThread;

	private boolean running;

	private TaskQueue pathQueue = new TaskQueue();

	public AStarFinderPool(int[][] maps) {
		this(new Field2D(maps));
	}

	public AStarFinderPool(Field2D field) {
		this.field = field;
		this.running = true;
		this.pathfinderThread = new Thread(this,"AStarThread");
		this.pathfinderThread.start();
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException ex) {
			}
			emptyPathQueue();
		}
	}

	private void emptyPathQueue() {
		AStarFinder task;
		for (; (task = pathQueue.poll()) != null;) {
			task.run();
		}
	}

	public void stop() {
		running = true;
		pathfinderThread.interrupt();
	}

	public void search(AStarFindHeuristic heuristic, int startx, int starty,
			int endx, int endy, boolean flying, boolean flag,
			AStarFinderListener callback) {
		AStarFinder pathfinderTask = new AStarFinder(heuristic, field, startx,
				starty, endx, endy, flying, flag, callback);
		AStarFinder existing = pathQueue.contains(pathfinderTask);
		if (existing != null) {
			existing.update(pathfinderTask);
		} else {
			pathQueue.add(pathfinderTask);
		}
		pathfinderThread.interrupt();
	}

	public void search(AStarFindHeuristic heuristic, int startx, int starty,
			int endx, int endy, boolean flying, AStarFinderListener callback) {
		search(heuristic, startx, starty, endx, endy, flying, false, callback);
	}

	public LinkedList<Vector2f> search(AStarFindHeuristic heuristic,
			int startX, int startY, int endX, int endY, boolean flying,
			boolean flag) {
		return new AStarFinder(heuristic, field, startX, startY, endX, endY,
				flying, flag).findPath();
	}

	public LinkedList<Vector2f> search(AStarFindHeuristic heuristic,
			int startX, int startY, int endX, int endY, boolean flying) {
		return new AStarFinder(heuristic, field, startX, startY, endX, endY,
				flying, false).findPath();
	}

	class TaskQueue {

		private LinkedList<AStarFinder> queue = new LinkedList<AStarFinder>();

		public synchronized AStarFinder contains(AStarFinder element) {
			for (Iterator<AStarFinder> it = queue.iterator(); it.hasNext();) {
				AStarFinder af = it.next();
				if (af.equals(element)) {
					return af;
				}
			}
			return null;
		}

		public synchronized AStarFinder poll() {
			return queue.poll();
		}

		public synchronized void add(AStarFinder t) {
			queue.add(t);
		}
	}
}
