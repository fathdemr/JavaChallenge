package com.fatihdemir.javachallenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntityAudit {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phoneNumber;

    @OneToOne
    private Cart cart;


}
