package com.nhnacademy.edu.springframework.project.exception;

/**
 * 빈 파일일 경우에 발생하는 예외입니다.
 */
public class FileIsEmptyException extends RuntimeException {
    public FileIsEmptyException(String message) {
        super("empty : " + message);
    }
}
