package com.nhnacademy.edu.springframework.project.exception;

/**
 * 파일 확장자가 요구사항과 맞지 않을때 발생하는 예외입니다.
 */
public class IllegalExtensionException extends RuntimeException {
    public IllegalExtensionException(String message) {
        super(message);
    }
}
