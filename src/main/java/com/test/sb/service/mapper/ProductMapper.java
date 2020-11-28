package com.test.sb.service.mapper;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements GeneralMapper<Product> {
    @Override
    public Product getEntity(ReviewDto dto) {
        Product product = new Product();
        product.setExternalId(dto.getProductId());
        return product;
    }
}
