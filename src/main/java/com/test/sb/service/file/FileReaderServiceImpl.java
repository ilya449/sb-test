package com.test.sb.service.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<String> readFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> data = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + path, e);
        }
    }
}
