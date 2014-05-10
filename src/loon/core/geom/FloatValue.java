
package loon.core.geom;

public class FloatValue {

	private float value;

	public FloatValue(float v) {
		this.value = v ;
	}

	public float get() {
		return value;
	}

	public void set(float v) {
		this.value = v ;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
