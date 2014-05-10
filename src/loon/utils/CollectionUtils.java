package loon.utils;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;



final public class CollectionUtils {

	final static public int INITIAL_CAPACITY = 20;

	protected CollectionUtils() {
		super();
	}

	
	public static int indexOf(Object[] array, Object obj) {
		for (int i = 0; i < array.length; ++i) {
			if (obj == array[i]) {
				return i;
			}
		}
		throw new NoSuchElementException("" + obj);
	}

	
	public static int hashCode(int[][] arrays) {
		if (arrays == null) {
			return 0;
		}
		int result = 1;
		int h = arrays.length;
		int w = arrays[0].length;
		int value = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				value = arrays[i][j];
				int elementHash = (value ^ (value >>> 32));
				result = 31 * result + elementHash;
			}
		}
		return result;
	}

	
	public static Object expand(Object obj, int i, boolean flag) {
		int j = Array.getLength(obj);
		Object obj1 = Array.newInstance(obj.getClass().getComponentType(), j
				+ i);
		System.arraycopy(obj, 0, obj1, flag ? 0 : i, j);
		return obj1;
	}

	
	public static Object expand(Object obj, int size) {
		return expand(obj, size, true);
	}

	
	public static Object expand(Object obj, int size, boolean flag,
			Class<?> class1) {
		if (obj == null) {
			return Array.newInstance(class1, 1);
		} else {
			return expand(obj, size, flag);
		}
	}

	
	public static Object cut(Object obj, int size) {
		int j;
		if ((j = Array.getLength(obj)) == 1) {
			return Array.newInstance(obj.getClass().getComponentType(), 0);
		}
		int k;
		if ((k = j - size - 1) > 0) {
			System.arraycopy(obj, size + 1, obj, size, k);
		}
		j--;
		Object obj1 = Array.newInstance(obj.getClass().getComponentType(), j);
		System.arraycopy(obj, 0, obj1, 0, j);
		return obj1;
	}

	
	public static Object copyOf(Object src) {
		int srcLength = Array.getLength(src);
		Class<?> srcComponentType = src.getClass().getComponentType();
		Object dest = Array.newInstance(srcComponentType, srcLength);
		if (srcComponentType.isArray()) {
			for (int i = 0; i < Array.getLength(src); i++) {
				Array.set(dest, i, copyOf(Array.get(src, i)));
			}
		} else {
			System.arraycopy(src, 0, dest, 0, srcLength);
		}
		return dest;
	}

	
	public static int[][] copyOf(int[][] obj) {
		int size = obj.length;
		int[][] copy = new int[size][];
		for (int i = 0; i < size; i++) {
			int len = obj[i].length;
			int[] res = new int[len];
			System.arraycopy(obj[i], 0, res, 0, len);
			copy[i] = res;
		}
		return copy;
	}

	
	public static String[] copyOf(String[] obj) {
		return copyOf(obj, obj.length);
	}

	
	public static String[] copyOf(String[] obj, int newSize) {
		String tempArr[] = new String[newSize];
		System.arraycopy(obj, 0, tempArr, 0, MathUtils.min(obj.length, newSize));
		return tempArr;
	}

	
	public static Object[] copyOf(Object[] original, int newLength) {
		return copyOf(original, newLength, original.getClass());
	}

	
	public static Object[] copyOf(Object[] original, int newLength,
			Class<?> newType) {
		Object[] copy = (newType == Object[].class) ? new Object[newLength]
				: (Object[]) Array.newInstance(newType.getComponentType(),
						newLength);
		System.arraycopy(original, 0, copy, 0, MathUtils.min(original.length,
				newLength));
		return copy;
	}

	
	public static int[] copyOf(int[] obj) {
		return copyOf(obj, obj.length);
	}

	
	public static int[] copyOf(int[] obj, int newSize) {
		int tempArr[] = new int[newSize];
		System.arraycopy(obj, 0, tempArr, 0, MathUtils.min(obj.length, newSize));
		return tempArr;
	}

	
	public static double[] copyOf(double[] obj) {
		return copyOf(obj, obj.length);
	}

	
	public static double[] copyOf(double[] obj, int newSize) {
		double tempArr[] = new double[newSize];
		System.arraycopy(obj, 0, tempArr, 0, MathUtils.min(obj.length, newSize));
		return tempArr;
	}

	
	public static float[] copyOf(float[] obj) {
		return copyOf(obj, obj.length);
	}

	
	public static float[] copyOf(float[] obj, int newSize) {
		float tempArr[] = new float[newSize];
		System.arraycopy(obj, 0, tempArr, 0, MathUtils.min(obj.length, newSize));
		return tempArr;
	}

	
	public static byte[] copyOf(byte[] obj) {
		return copyOf(obj, obj.length);
	}

	
	public static byte[] copyOf(byte[] obj, int newSize) {
		byte tempArr[] = new byte[newSize];
		System.arraycopy(obj, 0, tempArr, 0, MathUtils.min(obj.length, newSize));
		return tempArr;
	}

	
	public static char[] copyOf(char[] obj) {
		return copyOf(obj, obj.length);
	}

	
	public static char[] copyOf(char[] obj, int newSize) {
		char tempArr[] = new char[newSize];
		System.arraycopy(obj, 0, tempArr, 0, MathUtils.min(obj.length, newSize));
		return tempArr;
	}

	
	public static long[] copyOf(long[] obj) {
		return copyOf(obj, obj.length);
	}

	
	public static long[] copyOf(long[] obj, int newSize) {
		long tempArr[] = new long[newSize];
		System.arraycopy(obj, 0, tempArr, 0, MathUtils.min(obj.length, newSize));
		return tempArr;
	}

	
	public static boolean[] copyOf(boolean[] obj) {
		return copyOf(obj, obj.length);
	}

	
	public static boolean[] copyOf(boolean[] obj, int newSize) {
		boolean tempArr[] = new boolean[newSize];
		System.arraycopy(obj, 0, tempArr, 0, MathUtils.min(obj.length, newSize));
		return tempArr;
	}
	
}
