package com.test.sb.service.mapper;

import com.test.sb.dto.ReviewDto;

public interface GeneralMapper<T> {
    T getEntity(ReviewDto dto);
}
