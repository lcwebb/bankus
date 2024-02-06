/*
package com.bankus.banking.lombok.modeltest;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String code;

    // One category can have many products
    @ManyToOne
    @JoinColumn(name="id_cat")
    private Category category;

}
*/
