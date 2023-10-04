package com.json.parser.db.antipn.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseDto {

    Long idCustomer;
    Long idProduct;
    Integer quantityPurchases;
    Date datePurchase;

}
