package com.epamlearning.moksiakova.lessons.lesson9;

import com.epamlearning.moksiakova.lessons.lesson9.exception.NoValueAnnotationException;
import com.epamlearning.moksiakova.lessons.lesson9.pojo.Human;
import com.epamlearning.moksiakova.lessons.lesson9.pojo.HumanWithEntityWithoutValue;
import com.epamlearning.moksiakova.lessons.lesson9.pojo.HumanWithoutEntityAndValue;
import com.epamlearning.moksiakova.lessons.lesson9.pojo.HumanWithoutEntityWithValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnnotationHandlerTest {

    @Test
    void checkAnnotationEntityWhenSetEntityAndValue() {
        Human human = new Human();
        Boolean expected =  AnnotationHandler.checkAnnotationEntity(human);
        assertTrue(expected);
    }

    @Test
    void checkAnnotationEntityWhenSetEntityNotSetValue() {
        HumanWithEntityWithoutValue human = new HumanWithEntityWithoutValue();
        String expectedMessage = String.format(
                "Class %s need have one or more field or method with annotation Value",
                human.getClass().getSimpleName());
        assertThrows(
                NoValueAnnotationException.class,
                () -> AnnotationHandler.checkAnnotationEntity(human),
                expectedMessage
        );
    }

    @Test
    void checkAnnotationEntityWhenNotSetEntitySetValue() {
        HumanWithoutEntityWithValue human = new HumanWithoutEntityWithValue();
        String expectedMessage = String.format(
                "You should not use annotation Value in class %s.",
                human.getClass().getSimpleName()
        );
        assertThrows(
                IllegalStateException.class,
                () -> AnnotationHandler.checkAnnotationEntity(human),
                expectedMessage
        );
    }

    @Test
    void checkAnnotationEntityWhenNotSetEntityAndValue() {
        HumanWithoutEntityAndValue human = new HumanWithoutEntityAndValue();
        Boolean expected =  AnnotationHandler.checkAnnotationEntity(human);
        assertFalse(expected);
    }

    @Test
    void checkAnnotationValueWhenIsSet() {
        Human human = new Human();
        Boolean expected =  AnnotationHandler.checkAnnotationValue(human);
        assertTrue(expected);
    }

    @Test
    void checkAnnotationValueWhenNotSet() {
        HumanWithoutEntityAndValue human = new HumanWithoutEntityAndValue();
        Boolean expected =  AnnotationHandler.checkAnnotationValue(human);
        assertFalse(expected);
    }

    @Test
    void setValueToFieldFromAnnotation() {
        Human human = new Human();
        AnnotationHandler.setValueToFieldFromAnnotation(human);
        assertEquals(5,human.getAge());
        assertEquals("defaultValue",human.getName());
    }

    @Test
    void setValueToMethodFromAnnotation() {
        Human human = new Human();
        AnnotationHandler.setValueToMethodFromAnnotation(human);
        assertEquals("very good boy",human.getDescription());
    }


}