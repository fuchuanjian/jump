package loon.utils.debugging;


public class Level {

	public static final Level DEBUG = new Level("Debug", 1);

	public static final Level INFO = new Level("Info", 2);

	public static final Level WARN = new Level("Warn", 3);

	public static final Level ERROR = new Level("Error", 4);

	public static final Level IGNORE = new Level("Ignore", 5);

    public static final Level ALL = new Level("Ignore", 0);
    
	String levelString;

	final int level;

	private Level(String levelString, int levelInt) {
		this.levelString = levelString;
		this.level = levelInt;
	}

	@Override
	public String toString() {
		return levelString;
	}

	public int toType() {
		return level;
	}
}
