package dev.seba.apiaref.model;

public record Post(
        Integer id,
        Integer userId,
        String title,
        String body
) {


}
