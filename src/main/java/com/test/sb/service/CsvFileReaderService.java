package com.test.sb.service;

import java.util.List;

public interface CsvFileReaderService {
    List<String> readFile(String path);
}