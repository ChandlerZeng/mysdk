package com.ddt.main.service.sdk;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CarStatus {
    private static final String TAG = CarStatus.class.getSimpleName();

    public static final String COLUME_NAME = "name";
    public static final String COLUME_VALUE = "value";

    public static final int STATE_DEF = -1;
    public static final int STATE_OFF = 0;
    public static final int STATE_ON = 1;

    public interface Status {
        String getTable();

        String[] getKeys();
    }

    public static class Device implements Status {
        public static final String DB_TABLE = "device";
        public static final String ACC = "acc";
        public static final String HAND_BRAKE = "hand_brake";
        public static final String BACK_CAR = "back_car";
        public static final String LAMP = "lamp";

        public String getTable() {
            return DB_TABLE;
        }

        public String[] getKeys() {
            return new String[]{ACC, HAND_BRAKE, BACK_CAR, LAMP};
        }

        public static void init(SQLiteDatabase db) {
            createTable(db, DB_TABLE);
            insert(db, DB_TABLE, ACC, 0);
            insert(db, DB_TABLE, HAND_BRAKE, 1);
            insert(db, DB_TABLE, BACK_CAR, 0);
            insert(db, DB_TABLE, LAMP, 0);
        }


        public static void reset(SQLiteDatabase db) {
            update(db, DB_TABLE, ACC, 0);
            update(db, DB_TABLE, HAND_BRAKE, 0);
            update(db, DB_TABLE, BACK_CAR, 0);
            update(db, DB_TABLE, LAMP, 0);
        }
    }

    public static class Radio implements Status {
        public static final String DB_TABLE = "radio";
        public static final String STATUS = "status";
        public static final String FREQUENCY = "frequency";
        public static final String SENSITIVITY = "sensitivity";
        public static final String VOLUME_FACTOR = "volume_factor";
        public static final String STEREO_INSTRUCTIONS = "stereo_instructions";

        public String getTable() {
            return DB_TABLE;
        }

        public String[] getKeys() {
            return new String[]{STATUS, FREQUENCY, SENSITIVITY, VOLUME_FACTOR, STEREO_INSTRUCTIONS};
        }

        public static void init(SQLiteDatabase db) {
            createTable(db, DB_TABLE);
            insert(db, DB_TABLE, STATUS, 0);
            insert(db, DB_TABLE, FREQUENCY, 8750);
            insert(db, DB_TABLE, SENSITIVITY, 1);
            insert(db, DB_TABLE, VOLUME_FACTOR, 0);
            insert(db, DB_TABLE, STEREO_INSTRUCTIONS, 0);
        }

        public static void reset(SQLiteDatabase db) {
            update(db, DB_TABLE, STATUS, 0);
            update(db, DB_TABLE, FREQUENCY, 8750);
            insert(db, DB_TABLE, SENSITIVITY, 1);
            update(db, DB_TABLE, VOLUME_FACTOR, 0);
            update(db, DB_TABLE, STEREO_INSTRUCTIONS, 0);
        }
    }

    public static class Call implements Status {
        public static final String DB_TABLE = "call";

        public static final String STATUS = "status";

        @Override
        public String getTable() {
            return DB_TABLE;
        }

        @Override
        public String[] getKeys() {
            return new String[]{STATUS};
        }

        public static void init(SQLiteDatabase db) {
            createTable(db, DB_TABLE);
            insert(db, DB_TABLE, STATUS, 0);
        }

        public static void reset(SQLiteDatabase db) {
            update(db, DB_TABLE, STATUS, 0);
        }
    }

    public static class SteeringWheel implements Status {
        public static final String DB_TABLE = "steering_wheel";

        public static final String PREVIOUS = "previous";
        public static final String NEXT = "next";
        public static final String SOURCE_SWITCH = "source_switch";
        public static final String NAVIGATION = "navigation";
        public static final String VOLUME_UP = "volume_up";
        public static final String VOLUME_DOWN = "volume_down";
        public static final String PLAY = "play";
        public static final String MUTE = "mute";
        public static final String ANSWER = "answer";
        public static final String HANG_UP = "hang_up";
        public static final String BACK = "back";
        public static final String HOME = "home";

        @Override
        public String getTable() {
            return DB_TABLE;
        }

        @Override
        public String[] getKeys() {
            return new String[]{PREVIOUS, NEXT, SOURCE_SWITCH, NAVIGATION, VOLUME_UP, VOLUME_DOWN, PLAY, MUTE, ANSWER, HANG_UP, BACK, HOME};
        }

        public static void init(SQLiteDatabase db) {
            createTable(db, DB_TABLE);
            insert(db, DB_TABLE, PREVIOUS, 0);
            insert(db, DB_TABLE, NEXT, 0);
            insert(db, DB_TABLE, SOURCE_SWITCH, 0);
            insert(db, DB_TABLE, NAVIGATION, 0);

            insert(db, DB_TABLE, VOLUME_UP, 0);
            insert(db, DB_TABLE, VOLUME_DOWN, 0);
            insert(db, DB_TABLE, PLAY, 0);
            insert(db, DB_TABLE, MUTE, 0);

            insert(db, DB_TABLE, ANSWER, 0);
            insert(db, DB_TABLE, HANG_UP, 0);
            insert(db, DB_TABLE, BACK, 0);
            insert(db, DB_TABLE, HOME, 0);
        }

        public static void reset(SQLiteDatabase db) {
            update(db, DB_TABLE, PREVIOUS, 0);
            update(db, DB_TABLE, NEXT, 0);
            update(db, DB_TABLE, SOURCE_SWITCH, 0);
            update(db, DB_TABLE, NAVIGATION, 0);

            update(db, DB_TABLE, VOLUME_UP, 0);
            update(db, DB_TABLE, VOLUME_DOWN, 0);
            update(db, DB_TABLE, PLAY, 0);
            update(db, DB_TABLE, MUTE, 0);

            update(db, DB_TABLE, ANSWER, 0);
            update(db, DB_TABLE, HANG_UP, 0);
            update(db, DB_TABLE, BACK, 0);
            update(db, DB_TABLE, HOME, 0);
        }
    }

    public static class Peripherals implements Status {

        public static final String DB_TABLE = "peripherals";

        public static final String BACK_CAR_CAMERA = "back_car_camera";
        public static final String HAND_BRAKE = "hand_brake";
        public static final String PANEL_LIGHT = "panel_light";

        @Override
        public String getTable() {
            return DB_TABLE;
        }

        @Override
        public String[] getKeys() {
            return new String[]{BACK_CAR_CAMERA, HAND_BRAKE, PANEL_LIGHT};
        }

        public static void init(SQLiteDatabase db) {
            createTable(db, DB_TABLE);
            insert(db, DB_TABLE, BACK_CAR_CAMERA, 1);
            insert(db, DB_TABLE, HAND_BRAKE, 1);
            insert(db, DB_TABLE, PANEL_LIGHT, 5);
        }

        public static void reset(SQLiteDatabase db) {
            update(db, DB_TABLE, BACK_CAR_CAMERA, 0);
            update(db, DB_TABLE, HAND_BRAKE, 0);
            update(db, DB_TABLE, PANEL_LIGHT, 5);
        }
    }

    public static class Navigation implements Status {
        public static final String DB_TABLE = "navigation";

        public static final String APPLICATION = "application";
        public static final String AUTORUN = "autorun";

        @Override
        public String getTable() {
            return DB_TABLE;
        }

        @Override
        public String[] getKeys() {
            return new String[]{APPLICATION, AUTORUN};
        }

        public static void init(SQLiteDatabase db) {
            createTable(db, DB_TABLE);
            insert(db, DB_TABLE, APPLICATION, 0);
            insert(db, DB_TABLE, AUTORUN, 1);
        }

        public static void reset(SQLiteDatabase db) {
            update(db, DB_TABLE, APPLICATION, 0);
            update(db, DB_TABLE, AUTORUN, 1);
        }
    }

    private static void createTable(SQLiteDatabase db, String table) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table + "(" +
                COLUME_NAME + " TEXT," +
                COLUME_VALUE + " TEXT);");
    }

    private static void insert(SQLiteDatabase db, String table, String name, String value) {
        ContentValues values = new ContentValues();
        values.put(COLUME_NAME, name);
        values.put(COLUME_VALUE, value);
        db.insert(table, null, values);
    }

    private static void insert(SQLiteDatabase db, String table, String name, int value) {
        ContentValues values = new ContentValues();
        values.put(COLUME_NAME, name);
        values.put(COLUME_VALUE, value);
        db.insert(table, null, values);
    }

    private static void update(SQLiteDatabase db, String table, String name, int value) {
        Log.i(TAG, "update table:" + table + " name:" + name + " value:" + value);
        ContentValues values = new ContentValues();
        values.put(COLUME_NAME, name);
        values.put(COLUME_VALUE, value);
        db.update(table, values, COLUME_NAME + "=?", new String[]{name});
    }

    private static void update(SQLiteDatabase db, String table, String name, String value) {
        Log.i(TAG, "update table:" + table + " name:" + name + " value:" + value);
        ContentValues values = new ContentValues();
        values.put(COLUME_NAME, name);
        values.put(COLUME_VALUE, value);
        db.update(table, values, COLUME_NAME + "=?", new String[]{name});
    }
}
