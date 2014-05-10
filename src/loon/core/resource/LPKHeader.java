package loon.core.resource;

import loon.utils.MathUtils;



public class LPKHeader {

	public static final int LF_PAK_ID = (('L' << 24) + ('G' << 16) + ('P' << 8) + 'K');
	
	public static final int LF_PASSWORD_LENGTH = 10;

	public static final int LF_FILE_LENGTH = 30;
	
	private int identity;

	private byte[] password;

	private float version = 1.0F;

	private long tables = 0;

	public LPKHeader() {
		this.password = new byte[LPKHeader.LF_PASSWORD_LENGTH];
	}

	public LPKHeader(byte[] password, float version, long tables) {
		this.password = password;
		this.version = version;
		this.tables = tables;
	}

	public long getTables() {
		return tables;
	}

	public void setTables(long tables) {
		this.tables = tables;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public int getPAKIdentity() {
		return LPKHeader.LF_PAK_ID;
	}

	public void setPAKIdentity(int id) {
		this.identity = id;
	}

	public boolean validatePAK() {
		return identity == LPKHeader.LF_PAK_ID;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(long pass) {
		this.password = MathUtils.addZeros(pass, LPKHeader.LF_PASSWORD_LENGTH)
				.getBytes();
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public static int size() {
		return 4 + LPKHeader.LF_PASSWORD_LENGTH + 4 + 4 + 1;
	}

}
