package com.json.parser.db.antipn.models.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_purchases")
    @SequenceGenerator(name = "global_seq_purchases", sequenceName = "global_seq_purchases", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_customer")
    Long idCustomer;

    @Column(name = "id_product")
    Long idProduct;

    @Column(name = "quantity_purchase")
    Integer quantityPurchases;

    @Column(name = "date_purchase")
    Date datePurchase;

}
