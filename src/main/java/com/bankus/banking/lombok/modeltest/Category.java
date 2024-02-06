/*
package com.bankus.banking.lombok.modeltest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    // One category can be related to many products
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
*/
