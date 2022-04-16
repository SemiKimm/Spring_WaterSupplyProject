package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import com.nhnacademy.edu.springframework.project.exception.FileIsEmptyException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CsvDataParser implements DataParser {
    private final Map<Integer, WaterRate> parsingDataList = new HashMap<>();
    private final Log log = LogFactory.getLog(CsvDataParser.class);

    @Override
    public void parse(String path) {
        if (isEmptyFile(path)) {
            throw new FileIsEmptyException("file is empty");
        }
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path))))) {
            fileReader.readLine();
            String l;
            while ((l = fileReader.readLine()) != null) {

                String[] data = l.split("\\s*,\\s*");
                parsingDataList.put(Integer.parseInt(data[0]),new WaterRate(Integer.parseInt(data[0]), data[1], data[2],
                    Integer.parseInt(data[6])));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public boolean isEmptyFile(String path) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path))))) {
            if (fileReader.lines().count() == 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<Integer, WaterRate> getParsingDataList() {
        return this.parsingDataList;
    }
}
