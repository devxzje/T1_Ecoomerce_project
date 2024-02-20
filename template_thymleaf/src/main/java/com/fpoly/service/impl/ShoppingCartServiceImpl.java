package com.fpoly.service.impl;

import com.fpoly.model.*;
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



    // Helper method to calculate total items in the cart
    private int calculateTotalItems(Set<ShoppingCartDetail> cartDetails) {
        return cartDetails.stream().mapToInt(ShoppingCartDetail::getQuantity).sum();
    }

    // Helper method to calculate total price of items in the cart
    private double calculateTotalPrice(Set<ShoppingCartDetail> cartDetails) {
        return cartDetails.stream().mapToDouble(detail -> detail.getTotalPrice()).sum();
    }


    @Override
    public ShoppingCart addToCart(Product product, Size size, Integer quantity, Users user) {


            ShoppingCart cart = user.getShoppingCart();

            // Create a new shopping cart if it doesn't exist
            if (cart == null) {
                cart = new ShoppingCart();
                cart.setUser(user);
                cart.setTotalItem(0);
                cart.setTotalPrice(0.0);
            }

            // Initialize cart details set if null
            Set<ShoppingCartDetail> cartDetails = cart.getShoppingCartDetails();
            if (cartDetails == null) {
                cartDetails = new HashSet<>();
                cart.setShoppingCartDetails(cartDetails);
            }

            // Check if the product already exists in the cart details
            ShoppingCartDetail existingDetail = cartDetails.stream()
                    .filter(detail -> detail.getProduct().getId().equals(product.getId()) && detail.getSize().getId().equals(size.getId()))
                    .findFirst()
                    .orElse(null);

            // If the product doesn't exist in the cart, create a new detail
            if (existingDetail == null) {
                existingDetail = new ShoppingCartDetail();
                existingDetail.setProduct(product);
                existingDetail.setSize(size);
                existingDetail.setQuantity(quantity);
                existingDetail.setTotalPrice(quantity * product.getPrice());
                existingDetail.setCart(cart);
                cartDetails.add(existingDetail);
            } else {
                // If the product already exists in the cart, update its quantity and total price
//                existingDetail.setQuantity(existingDetail.getQuantity() + quantity);
//                existingDetail.setTotalPrice(existingDetail.getTotalPrice() + (quantity * product.getPrice()));
                int newQuantity = existingDetail.getQuantity() + quantity;
                existingDetail.setQuantity(newQuantity);
                existingDetail.setTotalPrice(newQuantity * product.getPrice());
            }

            // Save the shopping cart detail
            shoppingCartDetailRepository.save(existingDetail);

            // Update total item count and total price in the shopping cart
            cart.setTotalItem(calculateTotalItems(cartDetails));
            cart.setTotalPrice(calculateTotalPrice(cartDetails));

            // Save the shopping cart
            return shoppingCartRepository.save(cart);
        }

    @Override
    public ShoppingCart updateCart(Product product, Size size, Integer quantity, Users user) {
        ShoppingCart cart = user.getShoppingCart();

        if (cart != null) {
            Set<ShoppingCartDetail> cartDetails = cart.getShoppingCartDetails();

            if (cartDetails != null) {
                // Check if the product already exists in the cart details
                ShoppingCartDetail existingDetail = cartDetails.stream()
                        .filter(detail -> detail.getProduct().getId().equals(product.getId()) && detail.getSize().getId().equals(size.getId()))
                        .findFirst()
                        .orElse(null);

                if (existingDetail != null) {
                    // Update the quantity and total price of the existing detail
                    existingDetail.setQuantity(quantity);
                    existingDetail.setTotalPrice(quantity * product.getPrice());

                    // Save the updated shopping cart detail
                    shoppingCartDetailRepository.save(existingDetail);

                    // Update total item count and total price in the shopping cart
                    cart.setTotalItem(calculateTotalItems(cartDetails));
                    cart.setTotalPrice(calculateTotalPrice(cartDetails));

                    // Save the shopping cart
                    return shoppingCartRepository.save(cart);
                }
            }
        }
        return null; // Return null if the cart or the product detail is not found
    }

}
