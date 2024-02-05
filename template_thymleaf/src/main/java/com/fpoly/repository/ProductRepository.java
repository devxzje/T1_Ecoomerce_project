package com.fpoly.repository;

import com.fpoly.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT prod FROM Product prod WHERE CONCAT(prod.category.name, ' ', prod.name, ' ') like %?1%")
    public List<Product> filter(String keyword);
}
