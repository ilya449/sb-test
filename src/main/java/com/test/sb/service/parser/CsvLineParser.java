package com.test.sb.service.parser;

public interface CsvLineParser<T> {
    T parseData(String line);
}
