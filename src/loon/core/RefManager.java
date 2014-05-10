
package loon.core;

import java.util.ArrayList;
import java.util.Arrays;

import loon.utils.CollectionUtils;
//引用管理器，作用是保存实现了LRelease接口的对象，然后统一释放。
public class RefManager implements LRelease {

	public ArrayList<LRelease> objects = new ArrayList<LRelease>(
			CollectionUtils.INITIAL_CAPACITY);

	public RefManager() {
	}

	public RefManager(LRelease... res) {
		objects.addAll(Arrays.asList(res));
	}

	public RefManager(ArrayList<LRelease> res) {
		objects.addAll(res);
	}

	public boolean add(LRelease res) {
		return objects.add(res);
	}

	@Override
	public void dispose() {
		for (LRelease release : objects) {
			if (release != null) {
				release.dispose();
			}
		}
		objects.clear();
	}

}
