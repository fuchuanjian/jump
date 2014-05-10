package loon.core.resource;

import java.io.InputStream;
import java.util.HashMap;

import loon.core.graphics.LImage;
import loon.core.graphics.opengl.LTexture;
import loon.jni.NativeSupport;
import loon.utils.CollectionUtils;
import loon.utils.collection.ArrayByte;


public abstract class LPKResource {

	//如果此项为true，已加载数据会自动缓存。
	public static boolean CACHE = false;

	private static HashMap<String, PAK> pakRes = new HashMap<String, PAK>(
			CollectionUtils.INITIAL_CAPACITY);

	private static HashMap<String, ArrayByte> cacheRes;

	static public class PAK {

		public LPKTable[] tables;

		public int head_size = 0;

		public int skip;

		public int length;

	}

	public static void FreeCache() {
		if (cacheRes != null) {
			cacheRes.clear();
		}
	}

	public static HashMap<String, PAK> MAP() {
		return pakRes;
	}

	
	public static byte[] openResource(String fileName, String resName) {
		try {
			PAK pak = pakRes.get(fileName);
			InputStream ins = Resources.openResource(fileName);
			ArrayByte result = null;

			if (CACHE) {
				if (cacheRes == null) {
					cacheRes = new HashMap<String, ArrayByte>(
							CollectionUtils.INITIAL_CAPACITY);
				}
				result = cacheRes.get(fileName);
				if (result == null) {
					result = new ArrayByte(ins, ArrayByte.LITTLE_ENDIAN);
					cacheRes.put(fileName, result);
				} else {
					result.reset(ArrayByte.LITTLE_ENDIAN);
				}
			} else {
				result = new ArrayByte(ins, ArrayByte.LITTLE_ENDIAN);
			}

			if (pak == null) {
				pak = new PAK();
				LPKHeader header = readHeader(result);
				pak.tables = readLPKTable(result, (int) header.getTables());
				pak.head_size = (int) (LPKHeader.size() + header.getTables()
						* LPKTable.size());
				pak.skip = result.position();
				pak.length = result.length();
				pakRes.put(fileName, pak);
			} else {
				result.setPosition(pak.skip);
			}

			boolean find = false;
			int fileIndex = 0;
			String innerName = null;
			LPKTable[] tables = pak.tables;
			final int size = tables.length;

			for (int i = 0; i < size; i++) {
				innerName = tables[i].getFileName();
				if (resName.equalsIgnoreCase(innerName)) {
					find = true;
					fileIndex = i;
					break;
				}
			}

			if (!find) {
				throw new RuntimeException("File not found. ( " + fileName
						+ " )");
			} else {
				return readFileFromPak(result, pak.head_size, tables[fileIndex]);
			}
		} catch (Exception e) {
			throw new RuntimeException("File not found. ( " + fileName + " )");
		}
	}

	
	public static LImage openImage(String fileName, String resName) {
		byte[] buffer = null;
		try {
			buffer = LPKResource.openResource(fileName, resName);
			return LImage.createImage(buffer);
		} catch (Exception e) {
			throw new RuntimeException("File not found. ( " + resName + " )");
		}
	}

	
	public static LTexture openTexture(String fileName, String resName) {
		try {
			LImage image = openImage(fileName, resName);
			image.setAutoDispose(true);
			return image.getTexture();
		} catch (Exception e) {
			throw new RuntimeException("File not found. ( " + resName + " )");
		}
	}

	
	public static LPKHeader readHeader(ArrayByte dis) throws Exception {
		LPKHeader header = new LPKHeader();
		header.setPAKIdentity(dis.readInt());
		byte[] pass = dis.readByteArray(LPKHeader.LF_PASSWORD_LENGTH);
		header.setPassword(pass);
		header.setVersion(dis.readFloat());
		header.setTables(dis.readLong());
		return header;
	}

	
	public static LPKTable[] readLPKTable(ArrayByte dis, int fileTableNumber)
			throws Exception {
		LPKTable[] fileTable = new LPKTable[fileTableNumber];
		for (int i = 0; i < fileTableNumber; i++) {
			LPKTable ft = new LPKTable();
			ft.setFileName(dis.readByteArray(LPKHeader.LF_FILE_LENGTH));
			ft.setFileSize(dis.readLong());
			ft.setOffSet(dis.readLong());
			fileTable[i] = ft;
		}
		return fileTable;
	}

	
	public static byte[] readFileFromPak(ArrayByte dis, int size,
			LPKTable fileTable) throws Exception {
		dis.skip(fileTable.getOffSet() - size);
		int fileLength = (int) fileTable.getFileSize();
		byte[] fileBuff = new byte[fileLength];
		int readLength = dis.read(fileBuff, 0, fileLength);
		if (readLength < fileLength) {
			return null;
		} else {
			NativeSupport.makeBuffer(fileBuff, readLength, 0xF7);
			return fileBuff;
		}
	}

}
