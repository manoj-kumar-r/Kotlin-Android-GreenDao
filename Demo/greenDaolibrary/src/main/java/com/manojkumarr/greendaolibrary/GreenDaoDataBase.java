package com.manojkumarr.greendaolibrary;

import android.content.Context;


import com.manojkumarr.greendaolibrary.database.DbHelper;
import com.manojkumarr.greendaolibrary.database.DbOpenHelper;
import com.manojkumarr.greendaolibrary.models.DaoMaster;
import com.manojkumarr.greendaolibrary.models.DaoSession;
import com.manojkumarr.greendaolibrary.utils.GreenDaoConstants;

import org.greenrobot.greendao.database.Database;

public class GreenDaoDataBase {
    public static DbHelper getDataBase(Context context, String name, String password) {
        GreenDaoConstants.NAME = name;
        GreenDaoConstants.DB_PASSWORD = password;
        DbOpenHelper openHelper = new DbOpenHelper(context, GreenDaoConstants.NAME);
        Database database = openHelper.getEncryptedWritableDb(password);
        DaoSession daoSession = new DaoMaster(database).newSession();
        return new DbHelper(daoSession);
    }
}
