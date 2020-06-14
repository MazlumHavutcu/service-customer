package com.micro.service.customer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CustomerEntity {
    @Id
    private long id;

    private String name;

    private String address;
}
