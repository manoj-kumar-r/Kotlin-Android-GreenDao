package com.manojkumarr.greendaolibrary.database.converters;

public enum EmpType {
    DEFAULT(-1),
    MANAGER(0),
    HR(1),
    EMPLOYEE(2);

    public final int id;

    EmpType(int id) {
        this.id = id;
    }
}
