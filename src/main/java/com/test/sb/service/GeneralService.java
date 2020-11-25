package com.test.sb.service;

import java.util.List;

public interface GeneralService<T> {
    void saveAll(List<T> items);
}
