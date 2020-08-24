package com.manojkumarr.greendaolibrary.utils;

import android.util.Log;

import com.manojkumarr.greendaolibrary.BuildConfig;


public class GreenDaoConstants {
    public static String NAME = "cif";
    public static String DB_PASSWORD = "cif";

    public static void log(String message) {
        if (BuildConfig.DEBUG)
            Log.d(BuildConfig.LIBRARY_PACKAGE_NAME, message);
    }
}
