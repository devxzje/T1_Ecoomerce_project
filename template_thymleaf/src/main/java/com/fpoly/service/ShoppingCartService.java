package com.fpoly.service;

import com.fpoly.model.Product;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.User;

public interface ShoppingCartService {

    ShoppingCart addToCart(Product product, Integer quantity, User user);
}
