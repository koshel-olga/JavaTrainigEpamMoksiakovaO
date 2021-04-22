package com.epamlearning.moksiakova.lessons.lesson9;

import com.epamlearning.moksiakova.lessons.lesson9.annotation.Entity;
import com.epamlearning.moksiakova.lessons.lesson9.annotation.Value;
import com.epamlearning.moksiakova.lessons.lesson9.exception.NoValueAnnotationException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationHandler {

    public static boolean checkAnnotationEntity(Object object) {
        if (object.getClass().isAnnotationPresent(Entity.class)) {
            if (!checkAnnotationValue(object)) {
                String exceptionMessage = String.format(
                        "Class %s need have one or more field or method with annotation Value",
                        object.getClass().getName());
                throw new NoValueAnnotationException(exceptionMessage);
            }
            return true;
        }
        else {
            if (checkAnnotationValue(object)) {
                String exceptionMessage = String.format(
                        "You should not use annotation Value in class %s.",
                        object.getClass().getName()
                );
                throw new IllegalStateException(exceptionMessage);
            }
        }
        return false;
    }

    public static boolean checkAnnotationValue(Object object) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Value.class)) {
                return true;
            }
        }

        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Value.class)) {
                return true;
            }
        }
        return false;
    }

    public static void setValueToFieldFromAnnotation(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Value.class)) {
                field.setAccessible(true);
                field.set(object, field.getAnnotation(Value.class).value());
            }
        }
    }
}
