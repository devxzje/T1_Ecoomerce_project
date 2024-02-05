package com.fpoly.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "Product_Size")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "size_id", insertable = false, updatable = false)
    private Integer sizeId;

    @Column(name = "product_id", insertable = false,updatable = false)
    private Integer productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name ="size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
