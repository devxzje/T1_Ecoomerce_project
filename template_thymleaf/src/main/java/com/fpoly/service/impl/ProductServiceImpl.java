package com.fpoly.service.impl;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Product;
import com.fpoly.repository.ProductRepository;
import com.fpoly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String keyword) {
        if (keyword != null) {
            return productRepository.filter(keyword);
        }
        return productRepository.findAll();
    }

    @Override
    public Page<Product> paginatedProducts(Pageable pageable) {

        final List<Product> products = productRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> productPage = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());
        return productPage;
    }

    @Override
    public Page<Product> paginatedFilterProducts(String keyword, Pageable pageable) {

        List<Product> products = null;

        if(keyword != null){
            products = productRepository.filter(keyword);
        }else {
            products = productRepository.findAll();
        }

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> productPage = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());
        return productPage;
    }

    @Override
    public Page<Product> paginatedFilterByCategoryAndKeyword(String category, String keyword, Pageable pageable) {
        List<Product> products;

        if (keyword != null && !keyword.isEmpty()) {
            products = productRepository.filter(keyword);
        } else {
            products = productRepository.findAllByCategory(category);
        }

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> productPage = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());
        return productPage;
    }


    @Override
    public Product findById(Integer id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NotFoundException("Can not find product with id: " + id);
    }

    @Override
    public Page<Product> getByCategory(Integer id, Pageable pageable) {
        final List<Product> products = productRepository.getByCategory(id);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> productPage = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());
        return productPage;
    }
}
