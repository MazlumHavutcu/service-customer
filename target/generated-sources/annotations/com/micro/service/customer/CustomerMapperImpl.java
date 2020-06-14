package com.micro.service.customer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-26T20:04:41+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_112 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerEntityToDto(CustomerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( entity.getId() );
        customerDto.setName( entity.getName() );
        customerDto.setAddress( entity.getAddress() );

        return customerDto;
    }

    @Override
    public List<CustomerDto> customerEntityToDto(Iterable<CustomerEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<CustomerDto>();
        for ( CustomerEntity customerEntity : entityList ) {
            list.add( customerEntityToDto( customerEntity ) );
        }

        return list;
    }

    @Override
    public CustomerEntity customerDtoToEntity(CustomerDto dto) {
        if ( dto == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId( dto.getId() );
        customerEntity.setName( dto.getName() );
        customerEntity.setAddress( dto.getAddress() );

        return customerEntity;
    }
}
