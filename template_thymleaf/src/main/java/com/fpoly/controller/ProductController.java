package com.fpoly.controller;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Product;
import com.fpoly.model.ProductMedia;
import com.fpoly.model.ProductSize;
import com.fpoly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public String listProducts(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("keyword") Optional<String> keyword) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);

//        Page<Product> products = productService.paginatedProducts(PageRequest.of(currentPage - 1, pageSize));
        Page<Product> products = productService.paginatedFilterProducts(keyword.orElse(""), PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("products", products);

        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
//            model.addAttribute("keyword", keyword.orElse(""));
        }

        return "html/product/product";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {

        try {
            Product product = productService.findById(id);
            model.addAttribute("product", product);

            List<ProductSize> sizes = product.getSizes();
            model.addAttribute("sizes", sizes);

            List<ProductMedia> medias = product.getMedias();
            model.addAttribute("medias", medias);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        return "html/product/detail";
    }


}
