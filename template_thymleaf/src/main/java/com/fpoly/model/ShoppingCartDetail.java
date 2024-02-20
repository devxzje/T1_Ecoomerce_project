package com.fpoly.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Table(name = "Shopping_Cart_Detail")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "shopping_cart_id", insertable = false, updatable = false)
    private Integer shoppingCartId;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @Column(name = "size_id", insertable = false, updatable = false)
    private Integer sizeId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private ShoppingCart cart;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private Size size;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ShoppingCartDetail that = (ShoppingCartDetail) obj;
        return Objects.equals(id, that.id);
    }
}
