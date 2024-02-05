package com.fpoly.repository;

import com.fpoly.model.ShoppingCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartDetailRepository extends JpaRepository<ShoppingCartDetail, Integer> {
}
