package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.WaterRate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * CsvDataParser.
 */
@Component
public class CsvDataParser implements DataParser {
    private final Log log = LogFactory.getLog(CsvDataParser.class);

    @Override
    public Map<Integer, WaterRate> parse(String path) {
        Map<Integer, WaterRate> parsingDataList = new HashMap<>();

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
}
