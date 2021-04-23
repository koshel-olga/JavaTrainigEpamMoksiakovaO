package com.epamlearning.moksiakova.lessons.lesson9;

import com.epamlearning.moksiakova.lessons.lesson9.annotation.Entity;
import com.epamlearning.moksiakova.lessons.lesson9.exception.NoValueAnnotationException;
import com.epamlearning.moksiakova.lessons.lesson9.pojo.Human;
import com.epamlearning.moksiakova.lessons.lesson9.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static final String FILE_RESOURCE="human.txt";

    public static void main(String[] args) throws IllegalAccessException {
        Human human = new Human();
        User user = new User();
        try {
            if (AnnotationHandler.checkAnnotationEntity(human)) {
                AnnotationHandler.setValueToFieldFromAnnotation(human);
                AnnotationHandler.setValueToMethodFromAnnotation(human);
                log.info("Class {} is {}",human.getClass().getSimpleName(), human.toString());
            }
        } catch (NoValueAnnotationException | IllegalStateException exception) {
            logger.error(exception.getMessage());
        }
        try {
            AnnotationHandler.checkAnnotationEntity(user);
        } catch (NoValueAnnotationException | IllegalStateException exception) {
            logger.error(exception.getMessage());
        }
    }
}
