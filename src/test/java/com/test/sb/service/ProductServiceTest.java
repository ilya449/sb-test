package com.test.sb.service;

import com.test.sb.model.Product;
import com.test.sb.repository.ProductRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    public static final String FIRST_PRODUCT_ID = "B001E4KFG0";
    public static final String SECOND_PRODUCT_ID = "B00813GRG4";
    public static final String THIRD_PRODUCT_ID = "B000LQOCH0";
    public static final int ONE = 1;
    @MockBean
    private ProductRepository repository;

    @Autowired
    private GeneralService<Product> service;

    @Test
    public void saveAll() {
        List<Product> products = List.of(new Product(FIRST_PRODUCT_ID),
                new Product(SECOND_PRODUCT_ID),
                new Product(THIRD_PRODUCT_ID));

        service.saveAll(products);
        Mockito.verify(repository, Mockito.times(ONE)).saveAll(products);
    }
}
