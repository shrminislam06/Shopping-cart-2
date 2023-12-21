package com.spingBoot.shoppingCart.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInfo {
    private long id;
    private String userName;
    private String password;

}
