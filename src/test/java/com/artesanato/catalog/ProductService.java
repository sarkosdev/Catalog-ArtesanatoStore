package com.artesanato.catalog;

import com.artesanato.catalog.product.Product;
import com.artesanato.catalog.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductService {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        Product product1 = new Product(1L,"amendoa", "amendoa produzina na serra algarvia", LocalDate.of(2013, Month.DECEMBER, 23), false, "fruto-seco");
        Product product2 = new Product(2L,"amendoa1", "amendoa produzina na serra algarvia", LocalDate.of(2023, Month.MAY, 21), true, "fruto-seco");
        Product product3 = new Product(3L,"amendoa2", "amendoa produzina na serra algarvia", LocalDate.of(2023, Month.AUGUST, 14), false, "fruto-seco");
        Product product4 = new Product(4L,"amendoa", "amendoa produzina na serra algarvia", LocalDate.of(2017, Month.MAY, 23), true, "fruto-seco");

    }

    @Test
    public void testAddNewProduct() {


    }
}
