package com.zcq.sdk.test.greendao;

import com.zcq.sdk.test.activity.MyApplication;
import com.zcq.sdk.test.util.LogUtil;

import org.greenrobot.greendao.database.Database;

/**
 * Created by zcq on 2018/1/10.
 */

public class DaoManager {
    private static DaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper mHelper;
    private Database mDb;
    private DaoManager(){
        mHelper = new DaoMaster.DevOpenHelper(MyApplication.getInstance().getApplicationContext(),"nebula.db",null);
        mDb = mHelper.getWritableDb();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoManager getInstance(){
        if(mInstance==null){
            synchronized (DaoManager.class){
                if(mInstance==null){
                    mInstance = new DaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public UserDao getUserDao(){
        return mDaoSession.getUserDao();
    }

    public void closeDb(){
        mDb.close();
    }

    public DaoManager insertUser(User u){
        User user = getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(u.getId())).build().unique();
        if (user == null) {
            getUserDao().insert(u);
        }else{
            LogUtil.e("sorry,user already exist");
        }
        return mInstance;
    }

    public void deleteUser(long id){
        User user = getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).build().unique();
        if (user == null) {
            LogUtil.e("id is not exist");
        }else{
            getUserDao().deleteByKey(user.getId());
        }
    }

    public void updateUser(User u){
        User user = getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(u.getId())).build().unique();
        if (user == null) {
            LogUtil.e("user is not exist");
        }else{
            getUserDao().update(u);
        }
    }

}
