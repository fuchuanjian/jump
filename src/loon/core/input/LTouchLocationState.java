
package loon.core.input;

public enum LTouchLocationState {
	
	Invalid, Dragged, Pressed, Released;

	public int getValue() {
		return this.ordinal();
	}

	public static LTouchLocationState forValue(int value) {
		return values()[value];
	}
}
