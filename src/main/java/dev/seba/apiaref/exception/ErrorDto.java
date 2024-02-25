package dev.seba.apiaref.exception;

import lombok.Data;

@Data
public class ErrorDto {
    private Integer statusCode;
    private String message;
}
