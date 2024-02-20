package com.fpoly.controller;



import com.fpoly.exception.NotFoundException;
import com.fpoly.model.*;
import com.fpoly.repository.UserRepository;
import com.fpoly.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.DecimalFormat;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    ProductService productService;

    @Autowired
    SizeService sizeService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartDetailService shoppingCartDetailService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/view")
    public String viewShoppingCart(Model model,
                                   RedirectAttributes redirectAttributes) throws NotFoundException {

        Users user = userService.findById(2);

        ShoppingCart cart = user.getShoppingCart();

        if (cart == null) {
            model.addAttribute("message", "Nothing in your cart");
        } else {
            Set<ShoppingCartDetail> cartDetails = cart.getShoppingCartDetails();
            model.addAttribute("cartItems", cartDetails);
            String formattedTotalPrice = formatPrice(cart.getTotalPrice());
            model.addAttribute("total", formattedTotalPrice);
        }
        return "html/cart/cart";
    }

    private String formatPrice(double price) {
        // Format the price with grouping separators and append "VND"
        DecimalFormat formatter = new DecimalFormat("#,### VND");
        return formatter.format(price);
    }

    @PostMapping("/add")
    public String add(RedirectAttributes redirectAttributes,
                      Model model,
                      @RequestParam("id") Integer productId,
                      @RequestParam("sizeId") Integer sizeId,
                      @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity) throws NotFoundException {

      Users user = userService.findById(2);

        // Retrieve the product by ID
        Product product = productService.findById(productId);

        // Retrieve the size by ID
        Size size = sizeService.findById(sizeId);

        // Add the product to the shopping cart
        shoppingCartService.addToCart(product,size, quantity, user);

        List<ShoppingCartDetail> cartItems = shoppingCartDetailService.getAll();
        model.addAttribute("cartItems", cartItems);
        redirectAttributes.addFlashAttribute("message", "Add to cart successfully");

        // Redirect to the product page or wherever you want after adding the product to the cart
        return "redirect:/product";
    }

    @PutMapping("/update")
    public String update(RedirectAttributes redirectAttributes,
                         @RequestParam("productId") Integer productId,
                         @RequestParam("sizeId") Integer sizeId,
                         @RequestParam("quantity") Integer quantity) throws NotFoundException {

        // Assuming you have a way to identify the user, let's say you get the user ID.
        int userId = 2;
        Users user = userService.findById(userId);

        // Retrieve the product and size
        Product product = productService.findById(productId);
        Size size = sizeService.findById(sizeId);

        // Update the shopping cart
        shoppingCartService.updateCart(product, size, quantity, user);

        redirectAttributes.addFlashAttribute("message", "Cart updated successfully");

        // Redirect back to the shopping cart view page
        return "redirect:/cart/view";
    }

}
