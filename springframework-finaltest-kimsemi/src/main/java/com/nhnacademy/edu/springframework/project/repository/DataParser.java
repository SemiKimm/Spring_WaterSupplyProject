package com.nhnacademy.edu.springframework.project.repository;

public interface DataParser {
    void parse();

    boolean isEmptyFile(String path);
}
