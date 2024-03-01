package com.fpoly.controller;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.ShoppingCartDetail;
import com.fpoly.model.Users;
import com.fpoly.service.ShoppingCartDetailService;
import com.fpoly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ShoppingCartDetailService shoppingCartDetailService;

    @Autowired
    UserService userService;

    @GetMapping("/check-out")
    public String viewCheckOut(Model model) throws NotFoundException {

        List<ShoppingCartDetail> shoppingCartDetails = shoppingCartDetailService.getAll();
        model.addAttribute("shoppingCartDetails",shoppingCartDetails );

        Users user = userService.findById(2);
        ShoppingCart shoppingCart = user.getShoppingCart();
        String totalPrice =formatPrice(shoppingCart.getTotalPrice());

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("user", user);

        return "html/order/checkout";
    }

    private String formatPrice(double price) {
        // Format the price with grouping separators and append "VND"
        DecimalFormat formatter = new DecimalFormat("#,### VND");
        return formatter.format(price);
    }
}
