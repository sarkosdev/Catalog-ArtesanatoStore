package com.artesanato.catalog.cart.service;

import com.artesanato.catalog.cart.Cart;
import com.artesanato.catalog.cart.CartRepository;
import com.artesanato.catalog.product.Product;
import com.artesanato.catalog.product.ProductRepository;
import com.artesanato.catalog.product.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }


    public void addNewCart(Cart cart) {
        System.out.println("POST REQUEST | TRYING TO CREATE Cart: " + cart);

        Optional<Cart> cartOptional = cartRepository.findById(cart.getId());

        if(cartOptional.isPresent()) {
            throw new IllegalStateException("cart already exists");
        }
        else {
            cartRepository.save(cart);
        }
    }


    // Delete one Cart
    public void deleteCart(Long cartId) {
        boolean exists = cartRepository.existsById(cartId);
        if(!exists) {
            throw new IllegalStateException("cart with id: " + cartId + "doesnt exist");
        }
        else {
            cartRepository.deleteById(cartId);
            System.out.println("SUCCESS DELETING CART WITH ID: " + cartId);
        }

    }

    // Returns one single cart by ID
    public Optional<Cart> findCartById(Long id) {
        return cartRepository.findById(id);
    }


    // Add a new product to an existing Cart
    public Cart updateProductListFromCart(long id, long productId) {
        logger.info("TRYING TO PATCH CART -> product list");

        Optional<Product> productOptional = productRepository.findById(productId);
        Optional<Cart> cartOptional = cartRepository.findById(id);
        Cart cart = new Cart();

        logger.info(productId);
        logger.info(productOptional);

        if(cartOptional.isPresent()) {
            if(productOptional.isPresent()) {

                Set<Product> tempProductList = new HashSet<Product>();
                Set<Product> productList = new HashSet<Product>();
                Product product = productRepository.findProductById(productId);
                Cart tempCart = cartRepository.findCartInDBByID(id);

                tempProductList = tempCart.getProductSet();

                for(Product prd: tempProductList) {
                    productList.add(prd);
                }
                productList.add(product);



                cart.setId(tempCart.getId());
                cart.setName(tempCart.getName());
                cart.setStatus(tempCart.getStatus());
                cart.setProductSet(productList);
                cart.setUser(tempCart.getUser());

                cartRepository.save(cart);
                logger.info("SUCCESS ON PATCH Product status: {}", cart);
            }
            else {
                throw new IllegalStateException("Product doesnt exist in DB");
            }
        }
        else {
            throw new IllegalStateException("Cart doesnt exist in DB");
        }

        return cart;

    }
}
