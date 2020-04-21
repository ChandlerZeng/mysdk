package com.zcq.sdk.test.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * @ClassName MyDbManager
 * @Description 数据库管理类，使用静态内部类懒汉式单例模式
 * @Author Chandler
 * @Date 2020/3/25 16:03
 * @Version 1.0
 */
public class MyDbManager {
    //本地数据库路径
    private static final String DB_PATH = "";
    private SQLiteDatabase db;

    private static class LazyHolder{
        private static final MyDbManager INSTANCE = new MyDbManager();
    }

    private MyDbManager(){
        db = SQLiteDatabase.openDatabase(DB_PATH,null,SQLiteDatabase.OPEN_READONLY);
    }

    public static MyDbManager getInstance(){
        return LazyHolder.INSTANCE;
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
