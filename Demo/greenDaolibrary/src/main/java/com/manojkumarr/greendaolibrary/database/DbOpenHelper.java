package com.manojkumarr.greendaolibrary.database;

import android.content.Context;

import com.manojkumarr.greendaolibrary.models.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Create a data base
 */
public class DbOpenHelper extends DaoMaster.OpenHelper {

    //region Constructor
    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }
    //endregion Constructor

    //region Override Methods
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
    //endregion Override Methods

}
