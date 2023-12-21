package com.spingBoot.shoppingCart.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.SecondaryTable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {
    private long id;
    private Long productId;
    private String productName;
    private int quantity;
    private double totalPrice;
    private Date purchaseDate;



}
