package com.epam.framework.util;

import com.epam.framework.annotation.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public final class ReflectionUtil {

    private ReflectionUtil() {
    }

    public static List<Field> getAccessibleEntityFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (shouldBeIncluded(field)) {
                    field.setAccessible(true);
                    fields.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private static boolean shouldBeIncluded(final Field field) {
        final int modifiers = field.getModifiers();
        return (modifiers & (Modifier.STATIC | Modifier.FINAL)) == 0
                && field.getAnnotation(Transient.class) == null;
    }
}
