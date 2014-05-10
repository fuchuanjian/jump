package loon.core.input;

import loon.core.input.LInputFactory.Key;
import loon.utils.collection.ArrayByte;


public class LKey {

	int type;

	int keyCode;

	char keyChar;

	double timer;
	
	public LKey(byte[] out) {
		in(out);
	}

	LKey() {

	}

	public double getTimer() {
		return timer;
	}
	
	LKey(LKey key) {
		this.type = key.type;
		this.keyCode = key.keyCode;
		this.keyChar = key.keyChar;
	}

	public boolean equals(LKey e) {
		if (e == null) {
			return false;
		}
		if (e == this) {
			return true;
		}
		if (e.type == type && e.keyCode == keyCode && e.keyChar == keyChar) {
			return true;
		}
		return false;
	}

	public char getKeyChar() {
		return keyChar;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int getType() {
		return type;
	}

	public boolean isDown() {
		return type == Key.KEY_DOWN;
	}

	public boolean isUp() {
		return type == Key.KEY_UP;
	}

	public byte[] out() {
		ArrayByte touchByte = new ArrayByte();
		touchByte.writeInt(type);
		touchByte.writeInt(keyCode);
		touchByte.writeInt(keyChar);
		return touchByte.getData();
	}

	public void in(byte[] out) {
		ArrayByte touchByte = new ArrayByte(out);
		type = touchByte.readInt();
		keyCode = touchByte.readInt();
		keyChar = (char) touchByte.readInt();
		type = touchByte.readInt();
	}
}
