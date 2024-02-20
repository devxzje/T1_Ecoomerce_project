package com.fpoly.service;

import com.fpoly.model.Product;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.Size;
import com.fpoly.model.Users;

public interface ShoppingCartService {

    ShoppingCart addToCart(Product product, Size size, Integer quantity, Users user);
    ShoppingCart updateCart(Product product, Size size, Integer quantity, Users user);
}
