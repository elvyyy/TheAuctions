package com.epam.framework.handler.impl;

import com.epam.framework.annotation.Child;
import com.epam.framework.converter.Converter;
import com.epam.framework.converter.impl.DefaultConverter;
import com.epam.framework.handler.ResultSetHandler;
import com.epam.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler<T> implements ResultSetHandler<T> {
    private final Class<T> clazz;
    private final Converter converter;

    public DefaultResultSetHandler(final Class<T> clazz, final Converter converter) {
        this.clazz = clazz;
        this.converter = converter;
    }

    public DefaultResultSetHandler(final Class<T> clazz) {
        this(clazz, new DefaultConverter());
    }

    @Override
    public T handle(ResultSet resultSet) throws SQLException {
        // TODO: remake exception handling
        T instance = null;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        final List<Field> fieldList = ReflectionUtil.getAccessibleEntityFields(clazz);
        final List<String> columns = getAllColumns(resultSet);
        populateFields(instance, fieldList, columns, resultSet);
        // TODO: handle method
        return null;
    }

    private void populateFields(T entity, List<Field> fields, List<String> columns, ResultSet resultSet) {
        for (Field field : fields) {
            final Class<?> fieldClass = field.getType();
            final Child child = field.getAnnotation(Child.class);
            if (child != null) {
                // TODO: populate inner fields
//                populateChildFields
            } else {
                populateCurrentField(entity, field, fieldClass, columns, resultSet);
            }
        }
    }

    private void populateCurrentField(T entity, Field field, Class<?> fieldClass,
                                      List<String> columns, ResultSet resultSet) {

    }

    public List<String> getAllColumns(ResultSet resultSet) throws SQLException {
        final ResultSetMetaData metaData = resultSet.getMetaData();
        final int columnCount = metaData.getColumnCount();
        List<String> columnNames = new ArrayList<>(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            final String label = metaData.getColumnLabel(i);
            columnNames.add(label);
        }
        return columnNames;
    }
}
