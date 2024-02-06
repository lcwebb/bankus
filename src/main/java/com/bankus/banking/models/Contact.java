package com.bankus.banking.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Contact extends AbstractEntity {

    private  String firstname;

    private  String lastname;

    private String email;

    private String iban;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
