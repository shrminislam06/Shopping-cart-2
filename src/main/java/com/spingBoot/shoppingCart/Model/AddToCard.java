package com.spingBoot.shoppingCart.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCard {
    private long id;
    private Long productId;
    private int quantity;



}
