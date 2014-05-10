package loon.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import loon.core.LSystem;



final public class FileUtils {

	
	public static void write(String fileName, String context)
			throws IOException {
		write(fileName, context, false);
	}

	
	public static void write(File file, String context, String coding)
			throws IOException {
		write(file, context.getBytes(coding), false);
	}

	
	public static void write(String fileName, String context, boolean append)
			throws IOException {
		write(new File(fileName), context.getBytes(LSystem.encoding), append);
	}

	
	public static void write(File file, byte[] bytes) throws IOException {
		write(file, new ByteArrayInputStream(bytes), false);
	}

	
	public static void write(File file, byte[] bytes, boolean append)
			throws IOException {
		write(file, new ByteArrayInputStream(bytes), append);
	}

	
	public static void write(File file, InputStream input) throws IOException {
		write(file, input, false);
	}

	
	public static void write(File file, InputStream input, boolean append)
			throws IOException {
		makedirs(file);
		BufferedOutputStream output = null;
		try {
			int contentLength = input.available();
			output = new BufferedOutputStream(
					new FileOutputStream(file, append));
			while (contentLength-- > 0) {
				output.write(input.read());
			}
		} finally {
			close(input);
			close(output);
		}
	}

	
	public static void write(File file, char[] chars) throws IOException {
		write(file, new CharArrayReader(chars), false);
	}

	
	public static void write(File file, char[] chars, boolean append)
			throws IOException {
		write(file, new CharArrayReader(chars), append);
	}

	
	public static void write(File file, String string) throws IOException {
		write(file, new CharArrayReader(string.toCharArray()), false);
	}

	
	public static void write(File file, String string, boolean append)
			throws IOException {
		write(file, new CharArrayReader(string.toCharArray()), append);
	}

	
	public static void write(File file, Reader reader) throws IOException {
		write(file, reader, false);
	}

	
	public static void write(File file, Reader reader, boolean append)
			throws IOException {
		makedirs(file);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, append));
			int i = -1;
			while ((i = reader.read()) != -1) {
				writer.write(i);
			}
		} finally {
			close(reader);
			close(writer);
		}
	}

	
	public static void write(File file, ArrayList<String> records)
			throws IOException {
		write(file, records, false);
	}

	
	public static void write(File file, ArrayList<String> records,
			boolean append) throws IOException {
		makedirs(file);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, append));
			for (Iterator<String> it = records.iterator(); it.hasNext();) {
				writer.write(it.next());
				writer.write(LSystem.LS);
			}
		} finally {
			close(writer);
		}
	}

	
	public static void makedirs(String fileName) throws IOException {
		makedirs(new File(fileName));

	}

	
	public static void makedirs(File file) throws IOException {
		checkFile(file);
		File parentFile = file.getParentFile();
		if (parentFile != null) {
			if (!parentFile.exists() && !parentFile.mkdirs()) {
				throw new IOException("Creating directories "
						+ parentFile.getPath() + " failed.");
			}
		}
	}

	
	private static void checkFile(File file) throws IOException {
		boolean exists = file.exists();
		if (exists && !file.isFile()) {
			throw new IOException("File " + file.getPath()
					+ " is actually not a file.");
		}
	}

	
	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				closingFailed(e);
			}
		}
	}

	
	public static void close(OutputStream output) {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				closingFailed(e);
			}
		}
	}

	
	public static void close(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				closingFailed(e);
			}
		}
	}

	
	public static void close(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				closingFailed(e);
			}
		}
	}

	
	public static void closingFailed(IOException e) {
		throw new RuntimeException(e.getMessage());
	}

	
	public static long copy(InputStream is, OutputStream os, long len)
			throws IOException {
		byte[] buf = new byte[1024];
		long copied = 0;
		int read;
		while ((read = is.read(buf)) != 0 && copied < len) {
			long leftToCopy = len - copied;
			int toWrite = read < leftToCopy ? read : (int) leftToCopy;
			os.write(buf, 0, toWrite);
			copied += toWrite;
		}
		return copied;
	}

	
	public static long copy(InputStream in, OutputStream out)
			throws IOException {
		long written = 0;
		byte[] buffer = new byte[4096];
		while (true) {
			int len = in.read(buffer);
			if (len < 0) {
				break;
			}
			out.write(buffer, 0, len);
			written += len;
		}
		return written;
	}

	
	public static byte[] read(InputStream is, long len) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(is, out, len);
		return out.toByteArray();
	}

	
	public static long getKB(File file) {
		return getKB(file.length());
	}

	
	public static long getKB(long size) {
		size /= 1000L;
		if (size == 0L) {
			size = 1L;
		}
		return size;
	}

	
	public static boolean deleteAll(File dir) {
		String fileNames[] = dir.list();
		if (fileNames == null)
			return false;
		for (int i = 0; i < fileNames.length; i++) {
			File file = new File(dir, fileNames[i]);
			if (file.isFile())
				file.delete();
			else if (file.isDirectory())
				deleteAll(file);
		}

		return dir.delete();
	}

	
	public static byte[] readBytesFromFile(File file) throws IOException {
		InputStream is = new DataInputStream(new BufferedInputStream(
				new FileInputStream(file)));
		long length = file.length();
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			extracted(file);
		}
		is.close();
		return bytes;
	}

	private static void extracted(File file) throws IOException {
		throw new IOException("Could not completely read file "
				+ file.getName());
	}

	
	public static byte[] readBytesFromFile(String fileName) {
		try {
			return readBytesFromFile(new File(fileName));
		} catch (IOException e) {
			return null;
		}
	}

	
	public static final InputStream read(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	
	public static final InputStream read(String fileName) {
		return read(new File(fileName));
	}

	
	public static ArrayList<String> getAllFiles(String path) throws IOException {
		File file = new File(path);
		ArrayList<String> ret = new ArrayList<String>();
		String[] listFile = file.list();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File tempfile = new File(path + LSystem.FS + listFile[i]);
				if (tempfile.isDirectory()) {
					ArrayList<String> arr = getAllFiles(tempfile.getPath());
					ret.addAll(arr);
					arr.clear();
					arr = null;
				} else {
					ret.add(tempfile.getAbsolutePath());

				}
			}
		}
		return ret;
	}

	
	public static ArrayList<String> getAllDir(String path) throws IOException {
		File file = new File(path);
		ArrayList<String> ret = new ArrayList<String>();
		String[] listFile = file.list();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File tempfile = new File(path + LSystem.FS + listFile[i]);
				if (tempfile.isDirectory()) {
					ret.add(tempfile.getAbsolutePath());
					ArrayList<String> arr = getAllDir(tempfile.getPath());
					ret.addAll(arr);
					arr.clear();
					arr = null;

				}
			}
		}
		return ret;

	}

	
	public static ArrayList<String> getAllFiles(String path, String ext)
			throws IOException {
		File file = new File(path);
		ArrayList<String> ret = new ArrayList<String>();
		String[] exts = ext.split(",");
		String[] listFile = file.list();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File tempfile = new File(path + LSystem.FS + listFile[i]);
				if (tempfile.isDirectory()) {
					ArrayList<String> arr = getAllFiles(tempfile.getPath(), ext);
					ret.addAll(arr);
					arr.clear();
					arr = null;
				} else {
					for (int j = 0; j < exts.length; j++) {
						if (getExtension(tempfile.getAbsolutePath())
								.equalsIgnoreCase(exts[j])) {
							ret.add(tempfile.getAbsolutePath());
						}
					}
				}
			}
		}
		return ret;
	}

	
	public static ArrayList<String> getFiles(String path) throws IOException {
		File file = new File(path);
		ArrayList<String> Ret = new ArrayList<String>();
		String[] listFile = file.list();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File tempfile = new File(path + LSystem.FS + listFile[i]);

				if (!tempfile.isDirectory()) {
					Ret.add(tempfile.getAbsolutePath());

				}

			}
		}
		return Ret;
	}

	
	public static ArrayList<String> getDir(String path) throws IOException {
		File file = new File(path);
		ArrayList<String> ret = new ArrayList<String>();
		String[] listFile = file.list();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File tempfile = new File(path + LSystem.FS + listFile[i]);

				if (tempfile.isDirectory()) {
					ret.add(tempfile.getAbsolutePath());

				}
			}
		}
		return ret;
	}

	
	public static ArrayList<String> getFiles(String path, String ext)
			throws IOException {
		File file = new File(path);
		ArrayList<String> ret = new ArrayList<String>();
		String[] listFile = file.list();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File tempfile = new File(path + LSystem.FS + listFile[i]);

				if (!tempfile.isDirectory()) {
					if (getExtension(tempfile.getAbsolutePath())
							.equalsIgnoreCase(ext))
						ret.add(tempfile.getAbsolutePath());

				}
			}
		}
		return ret;
	}

	
	public static String getFileName(String name) {
		if (name == null) {
			return "";
		}
		int length = name.length();
		int size = name.lastIndexOf(LSystem.FS) + 1;
		if (size < length) {
			return name.substring(size, length);
		} else {
			return "";
		}
	}

	
	public static String getExtension(String name) {
		if (name == null) {
			return "";
		}
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return "";
		} else {
			return name.substring(index + 1);
		}
	}

	
	public static void deleteFile(String path) throws Exception {
		File file = new File(path);
		String[] listFile = file.list();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File tempfile = new File(path + LSystem.FS + listFile[i]);
				// 如果是目录
				if (tempfile.isDirectory()) {
					deleteFile(tempfile.getPath());
				} else { // 如果不是
					tempfile.delete();

				}
			}
		}
	}

	
	public static void deleteDir(String path) throws Exception {
		File file = new File(path);
		String[] listFile = file.list();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				File tempfile = new File(path + LSystem.FS + listFile[i]);
				if (tempfile.isDirectory()) {
					deleteDir(tempfile.getPath());
					tempfile.delete();

				}
			}
		}
	}
}
