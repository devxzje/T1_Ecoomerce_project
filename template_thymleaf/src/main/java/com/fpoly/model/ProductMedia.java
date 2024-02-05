package com.fpoly.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "Product_Media")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
