package com.fpoly.repository;

import com.fpoly.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    @Query(value = "SELECT s.id, s.total_item, s.total_price, u.id, u.address, u.email, u.first_name, u.last_name, u.password, u.phone, r.id, r.name, r.status, u.role_id, u.status, u.user_name, s.user_id FROM Shopping_Cart s LEFT JOIN [User] u ON u.id = s.user_id LEFT JOIN Role r ON r.id = u.role_id WHERE s.id = :id", nativeQuery = true)
    ShoppingCart findShoppingCartInfoById(Long id);
}
