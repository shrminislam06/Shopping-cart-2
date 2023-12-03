package com.spingBoot.shoppingCart.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveCardItem {
    private Long cardItemId;
    private int quantity;
}
