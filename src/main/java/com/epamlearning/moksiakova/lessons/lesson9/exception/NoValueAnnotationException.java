package com.epamlearning.moksiakova.lessons.lesson9.exception;

/**
 * Exception throws when on class set annotation Entity but not set annotation Value.
 */
public class NoValueAnnotationException extends RuntimeException {
    public NoValueAnnotationException(String message) {
        super(message);
    }
}
