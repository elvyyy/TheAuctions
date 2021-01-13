package com.epam.framework.converter.impl;

import com.epam.framework.converter.Converter;
import com.epam.framework.exception.FrameworkClassCastException;

public class DefaultConverter implements Converter {
    @Override
    public <T> T convert(final Class<T> clazz, final Object value) {
        if (value == null) {
            return null;
        } else if (clazz == Object.class || clazz == value.getClass()) {
            return (T) value;
        } else {
            throw new FrameworkClassCastException("Cannot convert class"
                    + value.getClass() + " to class " + clazz);
        }
    }
}
