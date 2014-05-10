
package loon.physics;

public class PContactData {

	public boolean flip;
	public int id;

	public PContactData() {
		id = 0;
		flip = false;
	}

	public PContactData(int id, boolean flip) {
		this.id = id;
		this.flip = flip;
	}

	@Override
	public PContactData clone() {
		PContactData ret = new PContactData();
		ret.id = id;
		ret.flip = flip;
		return ret;
	}

	public void set(int id, boolean flip) {
		this.id = id;
		this.flip = flip;
	}

}
