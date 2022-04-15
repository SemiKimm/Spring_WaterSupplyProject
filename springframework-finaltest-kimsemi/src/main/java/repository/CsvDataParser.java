package repository;

import com.nhnacademy.edu.springframework.project.repository.DataParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CsvDataParser implements DataParser {
    @Override
    public void parse() {

    }

    @Override
    public boolean isEmptyFile(String path) {
        try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path))))){
            if(fileReader.lines().count()==0){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
