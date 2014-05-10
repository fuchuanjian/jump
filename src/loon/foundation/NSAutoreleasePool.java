
package loon.foundation;

public class NSAutoreleasePool extends NSObject {

	boolean _enable = true;

	static int _id;

	static NSAutoreleasePool _instance;

	final NSArray _arrays;

	public NSAutoreleasePool() {
		_arrays = new NSArray();
		init();
	}

	public NSAutoreleasePool getMainAutoreleasePool() {
		return _instance;
	}

	private void init() {
		if (_instance == null) {
			_instance = this;
		}
		_id++;
	}

	public void addObject(NSObject o) {
		this._arrays.addObject(o);
	}

	public void removeObject(NSObject o) {
		this._arrays.removeObject(o);
	}

	public void enableFreedObjectCheck(boolean enable) {
		this._enable = enable;
	}

	public boolean isEnableFreedObjectCheck() {
		return _enable;
	}

	public String drain() {
		String res = toSequence();
		_arrays._list.clear();
		return res;
	}

	public static int getID() {
		return _id;
	}

	@Override
	public void dispose() {
		drain();
	}

	@Override
	protected void addSequence(StringBuilder sbr, String indent) {
		_arrays.addSequence(sbr, indent + "  ");
	}

}
