package com.ddt.main.service.util;

import android.util.Log;

public class Logger {
    private static final boolean DEBUG = true;
    private static final String TAG = Logger.class.getSimpleName();

    private Logger() {
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String head, String msg) {
        if (DEBUG) {
            Log.d(TAG, head + msg);
        }
    }

    public static void i(String head, String msg) {
        if (DEBUG) {
            Log.i(TAG, head + msg);
        }
    }

    public static void w(String head, String msg) {
        if (DEBUG) {
            Log.w(TAG, head + msg);
        }
    }

    public static void e(String head, String msg) {
        if (DEBUG) {
            Log.e(TAG, head + msg);
        }
    }
}
