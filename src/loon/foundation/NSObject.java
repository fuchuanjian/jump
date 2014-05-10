
package loon.foundation;

import loon.core.LRelease;
import loon.core.LSystem;

//不完全的仿制cocoa库，可将相关对象序列化为xml文档，便于非标准Java环境保存
public abstract class NSObject implements LRelease {

	protected static final boolean YES = true;
	protected static final boolean NO = false;

	NSObject() {
		if (NSAutoreleasePool._instance != null
				&& NSAutoreleasePool._instance._enable && isArray()) {
			NSAutoreleasePool._instance.addObject(this);
		}
	}

	public boolean isEqual(NSObject o) {
		return super.equals(o);
	}

	public boolean isArray() {
		return NSArray.class.isInstance(this)
				|| NSDictionary.class.isInstance(this);
	}

	protected abstract void addSequence(StringBuilder sbr, String indent);

	public String toSequence() {
		StringBuilder sbr = new StringBuilder(512);
		sbr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sbr.append(LSystem.LS);
		sbr.append("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">");
		sbr.append(LSystem.LS);
		sbr.append("<plist version=\"1.0\">");
		sbr.append(LSystem.LS);
		addSequence(sbr, "");
		sbr.append(LSystem.LS);
		sbr.append("</plist>");
		return sbr.toString();
	}

	@Override
	public String toString() {
		return toSequence();
	}

	@Override
	public void dispose() {
		if (NSAutoreleasePool._instance != null
				&& NSAutoreleasePool._instance._enable && isArray()) {
			NSAutoreleasePool._instance.removeObject(this);
		}
	}
}
