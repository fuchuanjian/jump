
package loon.action.sprite.node;

import java.util.ArrayList;

import loon.utils.StringUtils;

public class DefAnimation extends DefinitionObject {

	public String uniqueID;

	private LNAnimation animation;

	public LNAnimation get() {
		return animation;
	}

	@Override
	public void definitionObjectDidFinishParsing() {
		super.definitionObjectDidFinishParsing();
		if (animation != null) {
			LNDataCache.setAnimation(this, this.uniqueID);
		}
	}

	@Override
	public void definitionObjectDidReceiveString(String v) {
		super.definitionObjectDidReceiveString(v);
		ArrayList<String> result = getResult(v);
		float time = 3f;
		for (String list : result) {
			if (list.length() > 2) {
				String[] values = StringUtils.split(list, "=");
				String name = values[0];
				String value = values[1];
				if ("id".equalsIgnoreCase(name)) {
					this.uniqueID = value;
				} else if ("animationid".equalsIgnoreCase(name)) {
					this.uniqueID = value;
				} else if ("time".equalsIgnoreCase(name)) {
					time = Float.parseFloat(value);
				} else if ("duration".equalsIgnoreCase(name)) {
					time = Float.parseFloat(value);
				} else if ("list".equalsIgnoreCase(name)) {
					if (animation == null) {
						animation = new LNAnimation();
					}
					animation._name = uniqueID;
					String[] lists = StringUtils.split(list, ",");
					for (int i = 0; i < lists.length; i++) {
						animation.addFrameStruct(lists[i], time);
					}
				}
			}
		}
		result.clear();
	}

}
