package com.fpoly.repository;

import com.fpoly.model.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMediaRepository extends JpaRepository<ProductMedia, Integer> {
}
