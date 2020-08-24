package com.manojkumarr.greendaolibrary.database;

import com.manojkumarr.greendaolibrary.models.DaoSession;
import com.manojkumarr.greendaolibrary.models.EmployeeModel;

import java.util.List;

/**
 * Used to handle all data base methods
 */
public class DbHelper {

    //region Variables
    private DaoSession session;
    //endregion Variables

    //region Constructor
    public DbHelper(DaoSession session) {
        this.session = session;
    }
    //endregion Constructor

    //region Public insert methods
    public void insertEmployeeModel(EmployeeModel employeeModel) {
        session.getEmployeeModelDao().insertOrReplaceInTx(employeeModel);
    }
    //endregion Public insert methods

    //region Public fetch methods
    public List<EmployeeModel> getAllEmployees() {
        return session.getEmployeeModelDao().loadAll();
    }
    //endregion Getter

    //region Delete methods
    public void deleteEmployee(EmployeeModel employeeModel) {
        session.getEmployeeModelDao().deleteInTx(employeeModel);
    }
    //endregion Delete methods

    //region Getter
    public DaoSession getSession() {
        return session;
    }
    //endregion Private Methods

}

