package com.epamlearning.moksiakova.lessons.lesson9.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Value {
}
