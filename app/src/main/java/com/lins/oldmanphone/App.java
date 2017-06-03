package com.lins.oldmanphone;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;

import com.lins.oldmanphone.gen.DaoMaster;
import com.lins.oldmanphone.gen.DaoSession;
import com.lins.oldmanphone.utils.GreenDaoManager;
import com.lins.oldmanphone.utils.SharedData;
import com.lins.oldmanphone.utils.UpgradeHelper;


import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by LINS on 2017/5/2.
 */

public class App extends Application{
    // 用来保存整个应用程序数据
    private static HashMap<String, Object> allData = new HashMap<String, Object>();
    private static Context mContext;
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        GreenDaoManager.getInstance();
        initDaoSession();

//        Bmob.initialize(this,"33e6d0b9cc101e2998dc0493a98e1b5d");
        JPushInterface.init(this);

    }
    public static Context getContext() {
        return mContext;
    }

    public static String getSharedData(String key) {
        return new SharedData(mContext).getString(key,"0");
    }

    public static void setSharedData(String key,String autoLogin) {
        new SharedData(mContext).putString(key,autoLogin);
    }

    //    public static String getIsVip(){
//        return new KeyValueStorage(getContext()).getString(Config.IS_VIP,"");
//    }
//    public static void setIsVip(){
//        new KeyValueStorage(getContext()).putString(Config.IS_VIP,"");
//    }

    // 存数据*/
    public static void setHashData(String key, Object value) {
        allData.put(key, value);
    }

    // 取数据
    public static Object getHashData(String key) {
        if (allData.containsKey(key)) {
            return allData.get(key);
        }
        return null;
    }
    // 删除一条数据
    public static void delHashDataBykey(String key) {
        if (allData.containsKey(key)) {
            allData.remove(key);
        }
    }

    // 删除所有数据
    public static void delAllHashData() {
        allData.clear();
    }


    /*
         * GreenDao相关
         */
    public synchronized DaoSession getDaoSession() {
        if (mDaoSession == null) {
            initDaoSession();
        }
        return mDaoSession;
    }

    private void initDaoSession() {
        // 相当于得到数据库帮助对象，用于便捷获取db
        // 这里会自动执行upgrade的逻辑.backup all table→del all table→create all new table→restore data
        UpgradeHelper helper = new UpgradeHelper(this, "note.db", null);
        // 得到可写的数据库操作对象
        SQLiteDatabase db = helper.getWritableDatabase();
        // 获得Master实例,相当于给database包装工具
        DaoMaster daoMaster = new DaoMaster(db);
        // 获取类似于缓存管理器,提供各表的DAO类
        mDaoSession = daoMaster.newSession();
    }



















    // 结束的时候
    @Override
    public void onTerminate() {
        // TODO Auto-generated method stub
        super.onTerminate();
    }

    // 配置改变的时候
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }
}
