package com.armanyazdi.carpriceestimator;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class DataReader {

    // File Reader
    public void readFile(String file, List<String> list) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/data/".concat(file)));
            String line;
            while ((line = br.readLine()) != null) list.add(line);
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
