package com.epam.framework.converter;

public interface Converter {
    <T> T convert(Class<T> clazz, Object value);
}
