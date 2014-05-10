package loon.utils.xml;


public class XMLTokenizer {

	private String text;

	private int pointer;

	public XMLTokenizer(String text) {
		this.text = text;
	}

	public boolean hasMoreElements() {
		return this.pointer < this.text.length();
	}

	public String nextElement() {
		if (this.text.charAt(this.pointer) == '<') {
			return nextTag();
		}
		return nextString();
	}

	private String nextTag() {
		int i = 0;
		int j = this.pointer;
		do {
			switch (this.text.charAt(this.pointer)) {
			case '"':
				i = i != 0 ? 0 : 1;
			}
			this.pointer += 1;
		} while ((this.pointer < this.text.length())
				&& ((this.text.charAt(this.pointer) != '>') || (i != 0)));
		if (this.pointer < this.text.length()) {
			this.pointer += 1;
		} else {
			throw new RuntimeException(
					"Tokenizer error: < without > at end of text");
		}
		return this.text.substring(j, this.pointer);
	}

	private String nextString() {
		int i = 0;
		int j = this.pointer;
		do {
			switch (this.text.charAt(this.pointer)) {
			case '"':
				i = i != 0 ? 0 : 1;
			}
			this.pointer += 1;
		} while ((this.pointer < this.text.length())
				&& ((this.text.charAt(this.pointer) != '<') || (i != 0)));
		return this.text.substring(j, this.pointer);
	}

}
