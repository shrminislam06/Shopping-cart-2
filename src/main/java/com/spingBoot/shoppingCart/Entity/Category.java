package com.spingBoot.shoppingCart.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
//    @OneToMany(mappedBy = "category")
//    private List<Product>products;
}
