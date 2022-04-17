package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import com.nhnacademy.edu.springframework.project.exception.IllegalExtensionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * CsvDataParser 입니다.
 */
@Component
@Order(1)
public class CsvDataParser implements DataParser {
    private final Log log = LogFactory.getLog(CsvDataParser.class);

    @Override
    public Map<Integer, WaterRate> parse(String path) {
        Map<Integer, WaterRate> parsingDataList = new HashMap<>();
        if (checkInvalidExtension(path)) {
            throw new IllegalExtensionException("file extension is not csv : " + path);
        }
        if (isEmptyFile(path)) {
            throw new FileIsEmptyException("file is empty");
        }
        try (var fileReader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path))))) {
            String l = fileReader.readLine();
            while ((l = fileReader.readLine()) != null) {
                String[] data = l.replaceAll("\\s", "").split(",");
                parsingDataList.put(Integer.parseInt(data[0]),
                    new WaterRate(Integer.parseInt(data[0]), data[1], data[2],
                        Integer.parseInt(data[3]), Integer.parseInt(data[4]),
                        Integer.parseInt(data[5]), Integer.parseInt(data[6]), 0L));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return parsingDataList;
    }

    @Override
    public boolean isEmptyFile(String path) {
        try (var fileReader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path))))) {
            if (fileReader.lines().findAny().isEmpty()) {
                return true;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean checkInvalidExtension(String path) {
        return !FilenameUtils.getExtension(String.valueOf(Path.of(path).getFileName()))
            .equals("csv");
    }
}
