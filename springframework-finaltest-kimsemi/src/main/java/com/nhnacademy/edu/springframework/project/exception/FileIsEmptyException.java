package com.nhnacademy.edu.springframework.project.exception;

public class FileIsEmptyException extends RuntimeException{
    public FileIsEmptyException(String message) {
        super("empty : " + message);
    }
}
