package com.spingBoot.shoppingCart.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductInfo {
    private Long id;
    private String name;
    private double price;
}
