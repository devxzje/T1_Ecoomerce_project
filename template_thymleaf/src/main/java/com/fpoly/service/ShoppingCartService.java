package com.fpoly.service;

import com.fpoly.model.Product;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.Users;

public interface ShoppingCartService {

    ShoppingCart addToCart(Product product, Integer quantity, Users user);
}
