package com.artesanato.catalog.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.deleteAll();



            Product amendoa = new Product(
                "amendoa",
                "amendoa produzina na serra algarvia",
                    LocalDate.of(2023, Month.MAY, 23),
                true
            );

            Product mel = new Product(
                    "mel",
                    "mel de flor de amendoa",
                    LocalDate.of(2023, Month.MAY, 15),
                    false
            );

            // Create new products in database
            productRepository.saveAll(List.of(amendoa, mel));

        };
    }

}
