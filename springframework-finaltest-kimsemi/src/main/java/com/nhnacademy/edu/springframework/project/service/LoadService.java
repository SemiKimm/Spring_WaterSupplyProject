package com.nhnacademy.edu.springframework.project.service;

/**
 * 확장자에 따른 parser 를 선택하고 데이터를 load 합니다.
 */
public interface LoadService {
    void selectParser(String path);

    boolean checkJson(String path);

    boolean checkCsv(String path);

    void selectParserAndLoad(String path);
}
