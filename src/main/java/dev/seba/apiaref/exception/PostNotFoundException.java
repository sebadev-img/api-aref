package dev.seba.apiaref.exception;

public class PostNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public PostNotFoundException(String message){
        super(message);
    }
}
