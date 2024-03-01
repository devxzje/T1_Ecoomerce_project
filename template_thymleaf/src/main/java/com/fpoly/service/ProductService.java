package com.fpoly.service;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import com.app.beans.Paged;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts(String keyword);

    public Page<Product> paginatedProducts(Pageable pageable);

    public Page<Product> paginatedFilterProducts(String keyword, Pageable pageable);

    public Page<Product> paginatedFilterByCategoryAndKeyword(String category, String keyword, Pageable pageable);

    public Product findById(Integer id) throws NotFoundException;

    public Page<Product> getByCategory(Integer id, Pageable pageable);

}
