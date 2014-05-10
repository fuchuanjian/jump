package loon.utils.debugging;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LogMessage {

	static private String LOG_DEFAULT_DATE = "yyyy-MM-dd HH:mm:ss,SSS";

	static private SimpleDateFormat LOG_DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			LOG_DEFAULT_DATE);

	static private Date date = new Date();

	public Level level;

	public String time;

	public String message;

	protected LogMessage(Level level, String message) {
		setLogMessage(level, message);
	}

	protected void setLogMessage(Level level, String message) {
		this.level = level;
		this.message = message;
		date.setTime(System.currentTimeMillis());
		this.time = LOG_DEFAULT_DATE_FORMAT.format(date);
	}

	public Level getLevel() {
		return level;
	}

	public String getMessage() {
		return message;
	}

	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		return (time + " [" + level + "] " + message).intern();
	}

}
