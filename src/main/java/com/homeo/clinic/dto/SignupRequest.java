package com.homeo.clinic.dto;

import lombok.Data;

@Data

public class SignupRequest {

    private String fullName;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;
}