package loon.utils.debugging;

import loon.core.LSystem;


public class LogFormat {

	final static private int LOG_LEN[] = { 24, 15, 7, 100 };

	final static private String LOG_TITLE[] = { "time", "app", "module",
			"message" };

	final static private String LOG_TAG[] = { "-", "-", "-", "-" };

	private int count;

	public int Type;

	private String logMsg;

	private boolean show;

	public LogFormat(boolean s, int t) {
		this.show = s;
		this.Type = t;
	}

	private static String formatString(String str[], String pad, String sp) {
		StringBuffer sbr = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			if (str[i].length() > LOG_LEN[i]) {
				sbr.append(str[i].substring(0, LOG_LEN[i]) + sp);
				continue;
			}
			sbr.append(str[i]);
			for (int j = str[i].length(); j < LOG_LEN[i]; j++) {
				sbr.append(pad);
			}
			sbr.append(sp);
		}
		return sbr.toString();
	}

	public synchronized void title(int flag, String msg) {
		switch (flag) {
		case 0:
			android.util.Log.i("info", msg);
			break;
		case 1:
			android.util.Log.e("err", msg);
			break;
		}
	}

	public synchronized void out(String msg) {
		if (!show) {
			return;
		}
		title(Type, msg);
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public synchronized void out(String tm, String app, String level, String msg) {
		String value[] = { tm, app, level, msg };
		if (count++ % 9999 == 0) {
			logMsg = new StringBuffer(formatString(LOG_TAG, "-", " "))
					.append(LSystem.LS)
					.append(formatString(LOG_TITLE, " ", " "))
					.append(LSystem.LS).append(formatString(LOG_TAG, "-", " "))
					.append(LSystem.LS).append(formatString(value, " ", " "))
					.append(LSystem.LS).toString();
		} else {
			logMsg = formatString(value, " ", " ") + LSystem.LS;
		}
		out(logMsg);
	}

}
