package com.test.sb.service;

import com.test.sb.model.Product;
import com.test.sb.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements GeneralService<Product> {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<Product> products) {
        repository.saveAll(products);
    }
}
