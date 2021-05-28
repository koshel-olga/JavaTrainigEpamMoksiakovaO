package com.javatraining.moksiakova;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse {

    private int code;
    private String message;
}
