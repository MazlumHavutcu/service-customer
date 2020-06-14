package com.micro.service.customer;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class CustomerDto {

    private long id;

    @NotEmpty(message = " name must not be empty")
    private String name;

    @NotEmpty(message = "address name must not be empty")
    private String address;

    @Email
    private String email;

    @NotEmpty(message = " name must not be empty")
    private String phoneNumber;
}
