package com.fpoly.repository;

import com.fpoly.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT prod FROM Product prod WHERE CONCAT(prod.category.name, ' ', prod.name, ' ') like %?1%")
    public List<Product> filter(String keyword);

    @Query("SELECT prod FROM Product prod WHERE prod.category.name = :category AND CONCAT(prod.category.name, ' ', prod.name) LIKE %:keyword%")
    public List<Product> filterByCategoryAndKeyword(@Param("category") String category, @Param("keyword") String keyword);

    @Query("SELECT prod FROM Product prod WHERE prod.category.name = :category")
    List<Product> findAllByCategory(@Param("category") String category);

    public Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);

}
