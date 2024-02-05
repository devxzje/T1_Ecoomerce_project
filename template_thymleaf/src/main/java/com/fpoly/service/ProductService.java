package com.fpoly.service;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts(String keyword);

    public Page<Product> paginatedProducts(Pageable pageable);

    public Product findById(Integer id) throws NotFoundException;

}
