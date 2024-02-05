package com.fpoly.service.impl;

import com.fpoly.model.Product;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.ShoppingCartDetail;
import com.fpoly.model.User;
import com.fpoly.repository.ShoppingCartDetailRepository;
import com.fpoly.repository.ShoppingCartRepository;
import com.fpoly.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ShoppingCartDetailRepository shoppingCartDetailRepository;


    @Override
    public ShoppingCart addToCart(Product product, Integer quantity, User user) {

        ShoppingCart cart = user.getShoppingCart();

        if (cart == null) {
            cart = new ShoppingCart();
        }

        Set<ShoppingCartDetail> cartDetails = cart.getShoppingCartDetails();
        ShoppingCartDetail cartDetail = findCartDetail(cartDetails, product.getId());
        if (cartDetail == null) {
            cartDetails = new HashSet<>();
            if (cartDetail == null) {
                cartDetail = new ShoppingCartDetail();
                cartDetail.setProduct(product);
                cartDetail.setTotalPrice(quantity * product.getPrice());
                cartDetail.setQuantity(quantity);
                cartDetail.setCart(cart);
                cartDetails.add(cartDetail);
                shoppingCartDetailRepository.save(cartDetail);
            }
        }else {
                if (cartDetail == null) {
                    cartDetail = new ShoppingCartDetail();
                    cartDetail.setProduct(product);
                    cartDetail.setTotalPrice(quantity * product.getPrice());
                    cartDetail.setQuantity(quantity);
                    cartDetail.setCart(cart);
                    cartDetails.add(cartDetail);
                    shoppingCartDetailRepository.save(cartDetail);
                } else {
                    cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
                    cartDetail.setTotalPrice(cartDetail.getTotalPrice() + (quantity * product.getPrice()));
                    shoppingCartDetailRepository.save(cartDetail);
                }
            }

            cart.setShoppingCartDetails(cartDetails);
            int totalItems = totalItems(cart.getShoppingCartDetails());
            double totalPrice = totalPrice(cart.getShoppingCartDetails());

            cart.setTotalPrice(totalPrice);
            cart.setTotalItem(totalItems);
            cart.setUser(user);

            return shoppingCartRepository.save(cart);
        }



    private ShoppingCartDetail findCartDetail(Set<ShoppingCartDetail> cartDetails, Integer productId) {
        if (cartDetails == null) {
            return null;
        }
        ShoppingCartDetail cartDetail = null;

        for (ShoppingCartDetail item : cartDetails) {
            if (item.getProduct().getId() == productId) {
                cartDetail = item;
            }
        }
        return cartDetail;
    }

    private int totalItems(Set<ShoppingCartDetail> cartDetails) {
        int totalItems = 0;
        for (ShoppingCartDetail item : cartDetails) {
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private double totalPrice(Set<ShoppingCartDetail> cartDetails) {
        double totalPrice = 0.0;

        for (ShoppingCartDetail item : cartDetails) {
            totalPrice += item.getTotalPrice();
        }

        return totalPrice;
    }
}
