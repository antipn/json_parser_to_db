package com.json.parser.db.antipn.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_products")
    @SequenceGenerator(name = "global_seq_products", sequenceName = "global_seq_products", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_price")
    Integer productPrice;
}