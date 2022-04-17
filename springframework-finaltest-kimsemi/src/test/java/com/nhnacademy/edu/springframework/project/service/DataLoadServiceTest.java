package com.nhnacademy.edu.springframework.project.service;

import org.junit.jupiter.api.BeforeEach;

public class DataLoadServiceTest {
    LoadService dataLoadService;

    @BeforeEach
    void setUp() {
        dataLoadService = new DataLoadService();
    }
}
