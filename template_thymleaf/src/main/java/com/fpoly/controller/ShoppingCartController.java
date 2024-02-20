package com.fpoly.controller;



import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Product;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.ShoppingCartDetail;
import com.fpoly.model.Users;
import com.fpoly.repository.UserRepository;
import com.fpoly.service.ProductService;
import com.fpoly.service.ShoppingCartDetailService;
import com.fpoly.service.ShoppingCartService;
import com.fpoly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    ProductService productService;

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

//        Customer user = new Customer();
//        user.setId(2);
//        user.setFirstName("Duy");
//        user.setLastName("Tran Xuan");
//        user.setUserName("james");
//        user.setEmail("duy@example.com");
//        user.setPassword("123");
//        user.setAddress("Tu son");
//        user.setPhone("0975122665");
//        user.setStatus(1);
//        user.setRoleId(2L);

        Users user = userService.findById(2);

        ShoppingCart cart = user.getShoppingCart();

        if (cart == null) {
            model.addAttribute("message", "Nothing in your cart");
        } else {
            Set<ShoppingCartDetail> cartDetails = cart.getShoppingCartDetails();
            model.addAttribute("cartItems", cartDetails);
            Double total = cart.getTotalPrice();
            model.addAttribute("total", total);
        }
        return "html/cart/cart";
    }

    @PostMapping("/add")
    public String add(RedirectAttributes redirectAttributes,
                      Model model,
                      @RequestParam("id") Integer productId,
                      @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity) throws NotFoundException {

      Users user = userService.findById(2);
//        User user = new User();
//        user.setId(2L);
//        user.setFirstName("Duy");
//        user.setLastName("Tran Xuan");
//        user.setUserName("james");
//        user.setEmail("duy@example.com");
//        user.setPassword("123");
//        user.setAddress("Tu son");
//        user.setPhone("0975122665");
//        user.setStatus(1);
//        user.setRoleId(2L);

        // Retrieve the product by ID
        Product product = productService.findById(productId);

        // Add the product to the shopping cart
        shoppingCartService.addToCart(product, quantity, user);

        List<ShoppingCartDetail> cartItems = shoppingCartDetailService.getAll();
        model.addAttribute("cartItems", cartItems);
        redirectAttributes.addFlashAttribute("message", "Add to cart successfully");

        // Redirect to the product page or wherever you want after adding the product to the cart
        return "redirect:/product";
    }
}
