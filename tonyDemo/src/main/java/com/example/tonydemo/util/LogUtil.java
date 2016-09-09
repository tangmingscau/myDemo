package com.example.tonydemo.util;

import android.util.Log;

public class LogUtil {
	private static boolean DEBUG = true;

    public static boolean isLoggable(String tag, int level) {
		if(DEBUG)
			return true;
        return Log.isLoggable(tag, level);
    }

    public static int v(String tag, String format, Object... args) {
        if (isLoggable(tag, Log.VERBOSE)) {
            return Log.v(tag, String.format(format, args));
        }
        return 0;
    }

    public static int d(String tag, String format, Object... args) {
        if (isLoggable(tag, Log.DEBUG)) {
            return Log.d(tag, String.format(format, args));
        }
        return 0;
    }

    public static int i(String tag, String format, Object... args) {
        if (isLoggable(tag, Log.INFO)) {
            return Log.i(tag, String.format(format, args));
        }
        return 0;
    }

    public static int w(String tag, String format, Object... args) {
        if (isLoggable(tag, Log.WARN)) {
            return Log.w(tag, String.format(format, args));
        }
        return 0;
    }

    public static int e(String tag, String format, Object... args) {
        if (isLoggable(tag, Log.ERROR)) {
            return Log.e(tag, String.format(format, args));
        }
        return 0;
    }

}
