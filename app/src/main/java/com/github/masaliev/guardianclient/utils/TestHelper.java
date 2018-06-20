package com.github.masaliev.guardianclient.utils;

import java.lang.reflect.Field;

public class TestHelper {

    public static void setPrivateFieldValue(Object object, String fieldName, Object value) throws Exception{
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }
}
