package com.test.sb.service.mapper;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {
    @Autowired
    private GeneralMapper<Product> mapper;

    @Test
    public void mapProduct_OK() {
        Product expected = new Product();
        expected.setExternalId("B001E4KFG0");
        ReviewDto dto = ReviewDto.builder().productId("B001E4KFG0").build();
        Product actual = mapper.getEntity(dto);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void mapEmptyProduct() {
        ReviewDto dto = ReviewDto.builder().build();
        mapper.getEntity(dto);
    }
}
