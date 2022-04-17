package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.exception.IllegalExtensionException;
import com.nhnacademy.edu.springframework.project.repository.DataParser;
import com.nhnacademy.edu.springframework.project.repository.Tariff;
import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

/**
 * 파일의 확장자에 따라서 parser 를 선택하고 데이터를 load 합니다.
 */
@Service
public class DataLoadService implements LoadService {
    private final Tariff basicTariff;
    private final DataParser csvDataParser;
    private final DataParser jsonDataParser;

    /**
     * .
     *
     * @param basicTariff : 기본요금표저장소
     * @param csvDataParser : csv 확장자 파일 데이터 parser
     * @param jsonDataParser : json 확장자 파일 데이터 parser
     */
    public DataLoadService(Tariff basicTariff,
                           DataParser csvDataParser,
                           DataParser jsonDataParser) {
        this.basicTariff = basicTariff;
        this.csvDataParser = csvDataParser;
        this.jsonDataParser = jsonDataParser;
    }

    @Override
    public void selectParser(String path) {
        if (checkJson(path)) {
            basicTariff.setParser(jsonDataParser);
        } else if (checkCsv(path)) {
            basicTariff.setParser(csvDataParser);
        } else {
            throw new IllegalExtensionException("file extension is invalid : " + path);
        }
    }

    @Override
    public boolean checkJson(String path) {
        return FilenameUtils.getExtension(String.valueOf(Path.of(path).getFileName()))
            .equals("json");
    }

    @Override
    public boolean checkCsv(String path) {
        return FilenameUtils.getExtension(String.valueOf(Path.of(path).getFileName()))
            .equals("csv");
    }

    @Override
    public void selectParserAndLoad(String path) {
        selectParser(path);
        basicTariff.load(path);
    }
}
