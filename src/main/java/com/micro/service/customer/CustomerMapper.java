package com.micro.service.customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerEntityToDto(CustomerEntity entity);

    List<CustomerDto> customerEntityToDto(Iterable<CustomerEntity> entityList);

    CustomerEntity customerDtoToEntity(CustomerDto dto);
}
