package com.epamlearning.moksiakova.lessons.lesson9.pojo;

import com.epamlearning.moksiakova.lessons.lesson9.annotation.Value;

/**
 * Class for test AnnotationHandlerTest.checkAnnotationEntityWhenNotSetEntitySetValue.
 */
public class HumanWithoutEntityWithValue {

    @Value
    private int age;
    private String name;
}
