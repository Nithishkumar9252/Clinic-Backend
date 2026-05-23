package com.homeo.clinic.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "users")

@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true)
    private String username;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    private String role;

    private Boolean active;
}