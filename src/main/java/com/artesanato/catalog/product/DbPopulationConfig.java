package com.artesanato.catalog.product;

import com.artesanato.catalog.cart.Cart;
import com.artesanato.catalog.cart.CartRepository;
import com.artesanato.catalog.user.User;
import com.artesanato.catalog.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class DbPopulationConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, CartRepository cartRepository, UserRepository userRepository) {
        return args -> {
            /*
            productRepository.deleteAll();
            cartRepository.deleteAll();


            Product amendoa = new Product(
                "amendoa",
                "amendoa produzina na serra algarvia",
                LocalDate.of(2023, Month.MAY, 23),
                true,
                 "fruto-seco"
            );

            Product mel = new Product(
                    "mel",
                    "mel de flor de amendoa",
                    LocalDate.of(2023, Month.MAY, 15),
                    false,
                    "doces"
            );


            Cart cart1 = new Cart(
                    1L,
                    "cartxxx"

            );

            Cart cart2 = new Cart(
                    2L,
                    "cartxxx"

            );
            // Create new products in database
            productRepository.saveAll(List.of(amendoa, mel));
            cartRepository.saveAll(List.of(cart1, cart2));


            User user1 = new User(
                    1L,
                    "user1",
                    "user1@email.com",
                    "guest"

            );

            Cart cartwu = new Cart(
                    2L,
                    "cartxxx",
                    user1


            );



            userRepository.saveAll(List.of(user1));
            cartRepository.saveAll((List.of(cartwu)));
            */

        };
    }

}
