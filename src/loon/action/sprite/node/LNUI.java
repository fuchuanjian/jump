
package loon.action.sprite.node;

import loon.core.geom.Vector2f;

public class LNUI extends LNNode {
	
	public float _bold;

	protected int _touchID;

	public LNUI() {
		super();
		this.init();
	}

	private void init() {
		this._touchID = -1;
		this._enabled = true;
		this._bold = 0f;
		super.setNodeSize(0, 0);
	}

	public boolean isInside(Vector2f point) {
		float[] pos = super.convertToWorldPos();
		return (((point.x >= (pos[0] - this._bold)) && (point.x < ((pos[0] + super
				.getWidth()) + this._bold))) && ((point.y >= (pos[1] - this._bold)) && (point.y < ((pos[1] + super
				.getHeight()) + this._bold))));
	}

	public void touchesCancel() {
		this._touchID = -1;
	}

	public void setBold(float b) {
		this._bold = b;
	}

	public float getBold() {
		return this._bold;
	}
}
