package com.test.sb.service;

public interface CsvLineParser<T> {
    T parseData(String line);
}
