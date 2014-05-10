
package loon.utils.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Stack;

import loon.core.LRelease;
import loon.utils.MathUtils;

public class XMLOutput extends Writer implements LRelease {

	private final Stack<String> _stack = new Stack<String>();

	private final Writer _writer;

	private String _currentElement;

	private boolean _flag;

	public int _count;

	public XMLOutput() {
		this(new StringWriter());
	}

	public XMLOutput(Writer w) {
		this._writer = w;
	}

	public void start_ele(XMLElement ele) throws IOException {
		for (Iterator<?> it = ele.elements(); it.hasNext();) {
			XMLElement e = (XMLElement) it.next();
			start_ele(e.getName());
		}
	}

	public XMLOutput start_ele(String name) throws IOException {
		if (content()) {
			_writer.write('\n');
		}
		newline();
		_writer.write('<');
		_writer.write(name);
		_currentElement = name;
		return this;
	}

	public XMLOutput start_ele(String name, Object text) throws IOException {
		return start_ele(name).put_txt(text).end();
	}

	private boolean content() throws IOException {
		if (_currentElement == null) {
			return false;
		}
		_count++;
		_stack.push(_currentElement);
		_currentElement = null;
		_writer.write(">");
		return true;
	}

	public void start_attr(XMLAttribute attr) throws IOException {
		start_attr(attr.getName(), attr.getValue());
	}

	public XMLOutput start_attr(String name, Object value) throws IOException {
		if (_currentElement == null)
			throw new IllegalStateException();
		_writer.write(' ');
		_writer.write(name);
		_writer.write("=\"");
		_writer.write(value == null ? "null" : value.toString());
		_writer.write('"');
		return this;
	}

	public XMLOutput put_txt(Object text) throws IOException {
		content();
		String string = text == null ? "null" : text.toString();
		_flag = string.length() > 64;
		if (_flag) {
			_writer.write('\n');
			newline();
		}
		_writer.write(string);
		if (_flag) {
			_writer.write('\n');
		}
		return this;
	}

	public XMLOutput end() throws IOException {
		if (_currentElement != null) {
			_writer.write("/>\n");
			_currentElement = null;
		} else {
			_count = MathUtils.max(_count - 1, 0);
			if (_flag) {
				newline();
			}
			_writer.write("</");
			_writer.write(_stack.pop());
			_writer.write(">\n");
		}
		_flag = true;
		return this;
	}

	private void newline() throws IOException {
		int count = _count;
		if (_currentElement != null){
			count++;
		}
		for (int i = 0; i < count; i++) {
			_writer.write('\t');
		}
	}

	@Override
	public void close() throws IOException {
		while (_stack.size() != 0) {
			end();
		}
		_writer.close();
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		content();
		_writer.write(cbuf, off, len);
	}

	@Override
	public void flush() throws IOException {
		_writer.flush();
	}

	@Override
	public String toString() {
		return _writer.toString();
	}

	@Override
	public void dispose() {
		if (_stack != null) {
			_stack.clear();
		}
	}

}
