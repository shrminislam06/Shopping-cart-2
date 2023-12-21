package com.spingBoot.shoppingCart.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int quantity;
   private  double totalPrice;
   private boolean purchased;
   private Date purchaseDate;
    @ManyToOne
    private Product products;
    @OneToOne
    private UserEntity userEntity;



}
