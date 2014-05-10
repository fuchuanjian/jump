
package loon.foundation;

import loon.core.LSystem;

public class NSRange extends NSObject {
	public int start = 0;
	public int end = 0;

	public NSRange(int start, int end) {
		if (start < end) {
			this.start = start;
			this.end = end;
		}
	}

	@Override
	protected void addSequence(StringBuilder sbr, String indent) {
		sbr.append(indent);
		sbr.append("<range>");
		sbr.append(LSystem.LS);
		sbr.append("<start>");
		sbr.append(start);
		sbr.append("</start>");
		sbr.append("<end>");
		sbr.append(end);
		sbr.append("</end>");
		sbr.append(LSystem.LS);
		sbr.append("</range>");
	}

}
