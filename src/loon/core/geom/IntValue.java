
package loon.core.geom;

public class IntValue {

	private int value;

	public IntValue(int v) {
		this.value = v;
	}

	public int get() {
		return value;
	}

	public void set(int v) {
		this.value = v;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
