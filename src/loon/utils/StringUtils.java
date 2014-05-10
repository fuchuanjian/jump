package loon.utils;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;


final public class StringUtils {

	public static final String ASCII_CHARSET = "US-ASCII";

	public static final String ISO88591_CHARSET = "ISO-8859-1";

	public final static boolean startsWith(String n, char tag) {
		return n.charAt(0) == tag;
	}

	public final static boolean endsWith(String n, char tag) {
		return n.charAt(n.length() - 1) == tag;
	}
	
	private StringUtils() {
	}

	
	public static String concat(Object... res) {
		StringBuffer sbr = new StringBuffer(res.length);
		for (int i = 0; i < res.length; i++) {
			if (res[i] instanceof Integer) {
				sbr.append(res[i]);
			} else {
				sbr.append(res[i]);
			}
		}
		return sbr.toString();
	}

	
	public static boolean isEnglishAndNumeric(String string) {
		if (string == null || string.length() == 0) {
			return false;
		}
		char[] chars = string.toCharArray();
		int size = chars.length;
		for (int j = 0; j < size; j++) {
			char letter = chars[j];
			if ((97 > letter || letter > 122) && (65 > letter || letter > 90)
					&& (48 > letter || letter > 57)) {
				return false;
			}
		}
		return true;
	}

	
	public static boolean isSingle(final char c) {
		return (':' == c || '：' == c)
				|| (',' == c || '，' == c)
				|| ('"' == c || '“' == c)
				|| ((0x0020 <= c)
						&& (c <= 0x007E)
						&& !((('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')))
						&& !('0' <= c) && (c <= '9'));

	}

	
	public static String[] split(final String string, final String tag) {
		StringTokenizer str = new StringTokenizer(string, tag);
		String[] result = new String[str.countTokens()];
		int index = 0;
		for (; str.hasMoreTokens();) {
			result[index++] = str.nextToken();
		}
		return result;
	}

	
	public static final String replace(String string, String oldString,
			String newString) {
		if (string == null)
			return null;
		if (newString == null)
			return string;
		int i = 0;
		if ((i = string.indexOf(oldString, i)) >= 0) {
			char string2[] = string.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(string2.length);
			buf.append(string2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = string.indexOf(oldString, i)) > 0; j = i) {
				buf.append(string2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(string2, j, string2.length - j);
			return buf.toString();
		} else {
			return string;
		}
	}

	
	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return line;
		}
	}

	
	public static final String replaceIgnoreCase(String line, String oldString,
			String newString, int count[]) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			int counter = 1;
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		} else {
			return line;
		}
	}

	
	public static final String replace(String line, String oldString,
			String newString, int count[]) {
		if (line == null)
			return null;
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int counter = 1;
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		} else {
			return line;
		}
	}

	
	public static boolean isChinaLanguage(char[] chars) {
		int[] ints = new int[2];
		boolean isChinese = false;
		int length = chars.length;
		byte[] bytes = null;
		for (int i = 0; i < length; i++) {
			bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;
				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
						&& ints[1] <= 0xFE) {
					isChinese = true;
				}
			} else {
				return false;
			}
		}
		return isChinese;
	}

	public static boolean isChinese(char c) {
		return c >= 0x4e00 && c <= 0x9fa5;
	}

	
	public static boolean isEmpty(String param) {
		return param == null || param.length() == 0 || param.trim().equals("");
	}

	
	public static final boolean hasChinese(String checkStr) {
		boolean checkedStatus = false;
		boolean isError = false;
		String spStr = " _-";
		int checkStrLength = checkStr.length() - 1;
		for (int i = 0; i <= checkStrLength; i++) {
			char ch = checkStr.charAt(i);
			if (ch < '\176') {
				ch = Character.toUpperCase(ch);
				if (((ch < 'A') || (ch > 'Z')) && ((ch < '0') || (ch > '9'))
						&& (spStr.indexOf(ch) < 0)) {
					isError = true;
				}
			}
		}
		checkedStatus = !isError;
		return checkedStatus;
	}

	
	public final static boolean isAlphabet(String value) {
		if (value == null || value.length() == 0)
			return false;
		for (int i = 0; i < value.length(); i++) {
			char c = Character.toUpperCase(value.charAt(i));
			if ('A' <= c && c <= 'Z')
				return true;
		}
		return false;
	}

	
	public static boolean isAlphabetNumeric(String value) {
		if (value == null || value.trim().length() == 0)
			return true;
		for (int i = 0; i < value.length(); i++) {
			char letter = value.charAt(i);
			if (('a' > letter || letter > 'z')
					&& ('A' > letter || letter > 'Z')
					&& ('0' > letter || letter > '9'))
				return false;
		}
		return true;
	}

	
	public static String replaceMatch(String line, String oldString,
			String newString) {
		int i = 0;
		int j = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buffer = new StringBuffer(line2.length);
			buffer.append(line2, 0, i).append(newString2);
			i += oLength;
			for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				buffer.append(line2, j, i - j).append(newString2);
				i += oLength;
			}
			buffer.append(line2, j, line2.length - j);
			return buffer.toString();
		} else {
			return line;
		}
	}

	
	public static int charCount(String str, char chr) {
		int count = 0;
		if (str != null) {
			int length = str.length();
			for (int i = 0; i < length; i++) {
				if (str.charAt(i) == chr) {
					count++;
				}
			}
			return count;
		}
		return count;
	}

	public static byte[] getAsciiBytes(String data) {
		if (data == null) {
			throw new IllegalArgumentException("Parameter may not be null");
		}
		try {
			return data.getBytes(ASCII_CHARSET);
		} catch (UnsupportedEncodingException e) {
		}
		throw new RuntimeException("LGame requires ASCII support");
	}

	public static String getAsciiString(byte[] data, int offset, int length) {
		if (data == null) {
			throw new IllegalArgumentException("Parameter may not be null");
		}
		try {
			return new String(data, offset, length, ASCII_CHARSET);
		} catch (UnsupportedEncodingException e) {
		}
		throw new RuntimeException("LGame requires ASCII support");
	}

	public static String getAsciiString(byte[] data) {
		return getAsciiString(data, 0, data.length);
	}

}
