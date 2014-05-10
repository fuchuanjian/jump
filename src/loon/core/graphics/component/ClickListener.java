
package loon.core.graphics.component;

import loon.core.graphics.LComponent;

public interface ClickListener {

	void DoClick(LComponent comp);
	
	void DownClick(LComponent comp, float x, float y);

	void UpClick(LComponent comp, float x, float y);

	void DragClick(LComponent comp, float x, float y);
	
}
