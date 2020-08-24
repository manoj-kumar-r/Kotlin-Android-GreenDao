package com.manojkumarr.greendaolibrary.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manojkumarr.greendaolibrary.database.converters.EmpType;
import com.manojkumarr.greendaolibrary.database.converters.EmpTypeConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.jetbrains.annotations.NotNull;

/**
 * Used to store patient values in to the db and for serialization
 */
@Entity(
        nameInDb = "mst_emp",
        active = true
)
public class EmployeeModel {

    @SerializedName("id")
    @Expose
    @Id
    @Property(nameInDb = "id")
    private String id;

    @SerializedName("empName")
    @Expose
    @Property(nameInDb = "empName")
    private String empName;

    @SerializedName("empAge")
    @Expose
    @Property(nameInDb = "empAge")
    private int empAge;

    @SerializedName("empGender")
    @Expose
    @Property(nameInDb = "empGender")
    private String empGender;

    @SerializedName("empDepartment")
    @Expose
    @Property(nameInDb = "empDepartment")
    private String empDepartment;

    @SerializedName("empType")
    @Expose
    @Property(nameInDb = "empType")
    @Convert(converter = EmpTypeConverter.class, columnType = Integer.class)
    private EmpType empType;

    @NotNull
    @Override
    public String toString() {
        return empName;
    }

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1017140317)
    private transient EmployeeModelDao myDao;

    @Generated(hash = 140797186)
    public EmployeeModel(String id, String empName, int empAge, String empGender,
            String empDepartment, EmpType empType) {
        this.id = id;
        this.empName = empName;
        this.empAge = empAge;
        this.empGender = empGender;
        this.empDepartment = empDepartment;
        this.empType = empType;
    }

    @Generated(hash = 550606091)
    public EmployeeModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpAge() {
        return empAge;
    }

    public void setEmpAge(int empAge) {
        this.empAge = empAge;
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }

    public EmpType getEmpType() {
        return empType;
    }

    public void setEmpType(EmpType empType) {
        this.empType = empType;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1744958693)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEmployeeModelDao() : null;
    }
}
