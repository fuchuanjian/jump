package loon.core.resource;

import java.io.Serializable;


public class LPKTable implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String fileName;

	private long fileSize = 0L;

	private long offSet = 0L;

	public LPKTable() {
		this.fileName = null;
	}

	public LPKTable(byte[] fileName, long fileSize, long offSet) {
		this.fileName = new String(fileName).trim();
		this.fileSize = fileSize;
		this.offSet = offSet;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(byte[] bytes) {
		this.fileName = new String(bytes).trim();
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public long getOffSet() {
		return offSet;
	}

	public void setOffSet(long offSet) {
		this.offSet = offSet;
	}

	public static int size() {
		return LPKHeader.LF_FILE_LENGTH + 4 + 4;
	}

}
