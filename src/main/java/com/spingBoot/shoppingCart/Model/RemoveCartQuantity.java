package com.spingBoot.shoppingCart.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveCartQuantity {
    private Long productId;
    private int quantity;
}
