package com.epamlearning.moksiakova.lessons.lesson9;

import com.epamlearning.moksiakova.lessons.lesson9.annotation.Entity;
import com.epamlearning.moksiakova.lessons.lesson9.annotation.Value;
import com.epamlearning.moksiakova.lessons.lesson9.exception.NoValueAnnotationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class for work with annotation.
 * */
public class AnnotationHandler {

    private static Logger logger = LoggerFactory.getLogger(AnnotationHandler.class);

    /**
     * Method checks setting annotation Entity on class.
     *
     * @param object Object for checking.
     * @return boolean.
     * */
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

    /**
     * Method checks setting annotation Value on fields and methods.
     *
     * @param object Object for checking.
     * @return boolean.
     * */
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

    /**
     *
     * Method sets value  from annotation Value to field.
     *
     * @param object Object for setting.
     * */
    public static void setValueToFieldFromAnnotation(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Value.class)) {
                final Object convertedValue = convertAnnotationValueToFieldType(field);
                try {
                    field.set(object, convertedValue);
                } catch (IllegalArgumentException | IllegalAccessException exception) {
                    logger.error(
                            "Cannot set value {} on field {} type of {} in class {}",
                            field.getAnnotation(Value.class).value(),
                            field.getName(),
                            field.getType().getSimpleName(),
                            object.getClass().getSimpleName());
                }
            }
        }
    }

    /**
     *
     * Method sets value  from annotation Value to field.
     *
     * @param object Object for setting.
     * */
    public static void setValueToMethodFromAnnotation(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Value.class)) {

                try {
                    method.invoke(object, method.getAnnotation(Value.class).value());
                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException exception) {
                    logger.error(
                            "Cannot set value {} with method {} in class {}",
                            method.getAnnotation(Value.class).value(),
                            method.getName(),
                            object.getClass().getSimpleName());
                }
            }
        }
    }

    /**
     *
     * */
    private static Object convertAnnotationValueToFieldType(Field field) {
        field.setAccessible(true);
        String value = field.getAnnotation(Value.class).value();
        Object typedValue;
        final Class<?> type = field.getType();
        if (int.class.isAssignableFrom(type)) {
            typedValue = Integer.parseInt(value);
        } else if (boolean.class.isAssignableFrom(type)){
            typedValue = Boolean.valueOf(value);
        } else if (type.isEnum()) {
            typedValue = Enum.valueOf((Class<Enum>) type, value);
        } else {
            typedValue = value;
        }
        return typedValue;
    }
}
