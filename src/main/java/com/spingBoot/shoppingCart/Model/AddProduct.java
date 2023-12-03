package com.spingBoot.shoppingCart.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AddProduct {

    private long id;
    private String name;
    private double price;
    private int stock;
    private Long catId;
}
