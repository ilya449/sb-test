package com.test.sb.service;

import com.test.sb.model.Product;

public interface ProductService extends GeneralService<Product> {
    Product findByExternalId(String id);
}
