package com.dearlhd.crhwifi.SDK.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.dearlhd.crhwifi.SDK.bean.Journey;
import com.dearlhd.crhwifi.SDK.bean.User;

import java.io.File;

/**
 * Created by dearlhd on 2017/12/18.
 * 目前仅用于保存用户信息
 */
public class SQLiteHelper {
    private final String PATH = android.os.Environment.getExternalStorageDirectory() + "/crhwifi/";
    private final String DB_NAME = "crhwifi.db";

    private final String[] JOURNEY_ATTRS = {"destination", "current_station", "arrival_time"};
    private final String[] USER_ATTRS = {"username", "id"};

    private SQLiteDatabase mDatabase;

    public SQLiteHelper() {
        File dir = new File(PATH);
        if (!dir.exists()) {//不存在创建
            dir.mkdir();
        }
    }

    public void setUser(String username, long id) {
        final boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        final String databaseFilename = PATH + DB_NAME;

        mDatabase = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);

        synchronized (SQLiteHelper.class) {
            if (mDatabase != null) {
                String dropQuery = "DROP TABLE IF EXISTS user";
                mDatabase.execSQL(dropQuery);

                String createQuery = "CREATE TABLE IF NOT EXISTS user ("
                        + USER_ATTRS[0] + " text,"
                        + USER_ATTRS[1] + " integer"
                        + ");";
                mDatabase.execSQL(createQuery);

                String insertQuery = "INSERT INTO user ("
                        + USER_ATTRS[0] + ","
                        + USER_ATTRS[1]
                        + ") VALUES(?, ?)";
                mDatabase.execSQL(insertQuery, new Object[]{username, id});

            }
        }
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public long getUid () {
        final String databaseFilename = PATH + DB_NAME;

        mDatabase = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);

        String createQuery = "CREATE TABLE IF NOT EXISTS user ("
                + USER_ATTRS[0] + " text,"
                + USER_ATTRS[1] + " integer"
                + ");";
        mDatabase.execSQL(createQuery);

        String query = "SELECT * FROM user";
        Cursor cursor = mDatabase.rawQuery(query, null);

        long uid = 0;

        while (cursor.moveToNext()) {
            uid = cursor.getInt(cursor.getColumnIndex(USER_ATTRS[1]));
        }

        if (mDatabase != null) {
            mDatabase.close();
        }

        return uid;
    }

    public String getUsername () {
        final String databaseFilename = PATH + DB_NAME;

        mDatabase = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);

        String createQuery = "CREATE TABLE IF NOT EXISTS user ("
                + USER_ATTRS[0] + " text,"
                + USER_ATTRS[1] + " integer"
                + ");";
        mDatabase.execSQL(createQuery);

        String query = "SELECT * FROM user";
        Cursor cursor = mDatabase.rawQuery(query, null);

        String username = null;

        while (cursor.moveToNext()) {
            username = cursor.getString(cursor.getColumnIndex(USER_ATTRS[0]));
        }

        if (mDatabase != null) {
            mDatabase.close();
        }

        return username;
    }

    public void setJourney(Journey journey) {
        final boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        final String databaseFilename = PATH + DB_NAME;

        mDatabase = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);

        synchronized (SQLiteHelper.class) {
            if (mDatabase != null) {
                String dropQuery = "DROP TABLE IF EXISTS journey";
                mDatabase.execSQL(dropQuery);

                if (journey != null) {
                    String createQuery = "CREATE TABLE IF NOT EXISTS journey ("
                            + JOURNEY_ATTRS[0] + " text,"
                            + JOURNEY_ATTRS[1] + " text,"
                            + JOURNEY_ATTRS[2] + " text"
                            + ");";
                    mDatabase.execSQL(createQuery);

                    String insertQuery = "INSERT INTO journey ("
                            + JOURNEY_ATTRS[0] + ","
                            + JOURNEY_ATTRS[1] + ","
                            + JOURNEY_ATTRS[2]
                            + ") VALUES(?, ?, ?)";
                    mDatabase.execSQL(insertQuery, new Object[]{journey.getDestination(), journey.getCurrentStation(), journey.getArrivalTime()});
                }
            }
        }
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public Journey getJourney() {
        final String databaseFilename = PATH + DB_NAME;

        mDatabase = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);

        String createQuery = "CREATE TABLE IF NOT EXISTS journey ("
                + JOURNEY_ATTRS[0] + " text,"
                + JOURNEY_ATTRS[1] + " text,"
                + JOURNEY_ATTRS[2] + " text"
                + ");";
        mDatabase.execSQL(createQuery);

        String query = "SELECT * FROM journey";
        Cursor cursor = mDatabase.rawQuery(query, null);

        Journey journey = null;

        while (cursor.moveToNext()) {
            journey = new Journey();
            journey.setDestination(cursor.getString(cursor.getColumnIndex(JOURNEY_ATTRS[0])));
            journey.setCurrentStation(cursor.getString(cursor.getColumnIndex(JOURNEY_ATTRS[1])));
            journey.setArrivalTime(cursor.getString(cursor.getColumnIndex(JOURNEY_ATTRS[2])));
        }

        if (mDatabase != null) {
            mDatabase.close();
        }

        return journey;
    }

}

