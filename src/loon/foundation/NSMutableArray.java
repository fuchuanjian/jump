
package loon.foundation;

import loon.utils.collection.ArrayList;

public class NSMutableArray extends NSArray {

	public static NSMutableArray array() {
		return new NSMutableArray();
	}

	public static NSMutableArray arrayWithObject(NSObject o) {
		return new NSMutableArray(o);
	}

	public static NSMutableArray arrayWithObjects(NSObject... objects) {
		return new NSMutableArray(objects);
	}

	public static NSMutableArray arrayWithArray(NSArray array) {
		return new NSMutableArray(array);
	}

	public static NSMutableArray arrayWithCapacity(int capacity) {
		return new NSMutableArray(capacity);
	}

	public NSMutableArray(NSArray array) {
		this._list = array._list;
	}

	public NSMutableArray(NSObject... objects) {
		_list = new ArrayList(objects.length);
		for (NSObject o : objects) {
			_list.add(o);
		}
	}

	public NSMutableArray(NSObject o) {
		_list = new ArrayList(1);
		_list.add(o);
	}

	public NSMutableArray(int capacity) {
		_list = new ArrayList(capacity);
	}

	public NSMutableArray() {
		_list = new ArrayList();
	}

	public void removeAllObjects() {
		_list.clear();
	}

	public void addObjectsFromArray(NSArray a) {
		_list.addAll(a._list);
	}

	public void replaceObject(int index, NSObject o) {
		_list.set(index, o);
	}
}
