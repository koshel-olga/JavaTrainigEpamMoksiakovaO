package com.javatraining.moksiakova.locale;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Data
@Component
public class SetLocale {
    private Locale locale = Locale.ENGLISH;
}
