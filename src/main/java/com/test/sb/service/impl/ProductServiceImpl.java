package com.test.sb.service.impl;

import com.test.sb.model.Product;
import com.test.sb.repository.ProductRepository;
import com.test.sb.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<Product> products) {
        repository.saveAll(products);
    }

    @Override
    public Product findByExternalId(String id) {
        return repository.findProductByExternalId(id).get();
    }
}
