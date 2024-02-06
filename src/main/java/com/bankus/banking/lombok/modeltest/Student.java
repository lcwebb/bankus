/*
package com.bankus.banking.lombok.modeltest;

import com.bankus.banking.models.Address;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="etudiant")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(
        name = "prenom",
        length = 1024,
        updatable =false,
        insertable = false
    )
    //private String firstname;
    private LocalDateTime creationDate = LocalDateTime.now();

    @OneToOne(mappedBy = "student")
    private Address address;

}
*/
