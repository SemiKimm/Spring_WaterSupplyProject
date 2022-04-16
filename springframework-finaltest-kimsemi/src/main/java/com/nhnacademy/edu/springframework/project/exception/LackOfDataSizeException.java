package com.nhnacademy.edu.springframework.project.exception;

/**
 * 데이터의 크기가 특정 기준보다 작을때 발생하는 예외입니다.
 */
public class LackOfDataSizeException extends RuntimeException {
    public LackOfDataSizeException(String message) {
        super(message);
    }
}
