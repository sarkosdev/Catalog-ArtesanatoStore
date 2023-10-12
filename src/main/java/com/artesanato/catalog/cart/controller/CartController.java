package com.artesanato.catalog.cart.controller;

import com.artesanato.catalog.cart.Cart;
import com.artesanato.catalog.cart.service.CartService;
import com.artesanato.catalog.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController (CartService cartService) {
        this.cartService = cartService = cartService;
    }

    @GetMapping
    public List<Cart> getCarts() {
        return cartService.getCarts();
    }

    @GetMapping(path = "/get/cart/{cartId}")
    public Optional<Cart> getSingleCart(@PathVariable("cartId") Long id) {
        return cartService.findCartById(id);
    }

    @PostMapping
    public void registerNewCart(@RequestBody Cart cart) {
        cartService.addNewCart(cart);
    }

    // Deletes an existing cart by ID -> controller
    @DeleteMapping(path = "deleteById/{cartId}")
    public void deleteCart(@PathVariable("cartId") Long id) {
        cartService.deleteCart(id);
    }

    // Deletes a List of carts by ID passing a List of IDs
    @DeleteMapping(path = "deleteByIdList")
    public void deleteProductList(@RequestBody List<Long> idList) {
        for(Long id: idList) {
            cartService.deleteCart(id);
        }
    }

    // Add new product to an existing cart
    @PatchMapping(path = "/updateProducts/{cartId}")
    public Cart patchCartListOfProducts(@PathVariable long cartId, @RequestBody long productId) {
        return cartService.updateProductListFromCart(cartId, productId);
    }



}
