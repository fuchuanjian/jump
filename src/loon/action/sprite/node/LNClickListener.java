
package loon.action.sprite.node;

public interface LNClickListener {
	
	void DownClick(LNNode node, float x, float y);

	void UpClick(LNNode node, float x, float y);

	void DragClick(LNNode node, float x, float y);
	
}
