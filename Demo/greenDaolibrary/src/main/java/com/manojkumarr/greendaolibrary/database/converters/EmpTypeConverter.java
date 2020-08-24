package com.manojkumarr.greendaolibrary.database.converters;

import org.greenrobot.greendao.converter.PropertyConverter;

public class EmpTypeConverter implements PropertyConverter<EmpType, Integer> {
    @Override
    public EmpType convertToEntityProperty(Integer databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        for (EmpType role : EmpType.values()) {
            if (role.id == databaseValue) {
                return role;
            }
        }
        return EmpType.DEFAULT;
    }

    @Override
    public Integer convertToDatabaseValue(EmpType entityProperty) {
        return entityProperty == null ? null : entityProperty.id;
    }
}
