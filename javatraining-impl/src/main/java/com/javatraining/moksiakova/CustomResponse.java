package com.javatraining.moksiakova;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse<T> {

    private int code;
    private String message;
    private String jsonObject;
    private T object;
}
