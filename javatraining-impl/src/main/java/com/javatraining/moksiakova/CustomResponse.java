package com.javatraining.moksiakova;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse<T> {

    @Expose
    private int code;

    @Expose
    private String message;

    @Expose
    private T entity;
}
