package com.micro.service.customer;

import com.micro.service.feign.CollectorControllerClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final RestTemplate restTemplate;

    private final CustomerRepository customerRepository;

    private final CollectorControllerClient collectorControllerClient;

    List<CustomerDto> customerList;

    public CustomerService(
            @Qualifier("LoadBalanced") RestTemplate restTemplate,
            CustomerRepository customerRepository,
            CollectorControllerClient collectorControllerClient) {
        this.restTemplate = restTemplate;
        this.customerRepository = customerRepository;
        this.collectorControllerClient = collectorControllerClient;
    }

    public void save(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.customerDtoToEntity(customerDto);
        customerRepository.save(customerEntity);
        collectorControllerClient.sendInfo(customerDto);
    }

    public CustomerDto getById(long id) {
        CustomerDto customerDto = null;
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        if (customerEntity.isPresent()) {
            customerDto = CustomerMapper.INSTANCE.customerEntityToDto(customerEntity.get());
        }
        return customerDto;
    }

    public ResponseEntity<Void> deleteById(long id) {
        CustomerDto customerDto = getById(id);
        //collectorControllerClient.sendSms(customerDto);
        restTemplate.put("http://collector/smsMailQueue/sendSms", customerDto);
        customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "returnAllCustomer")
    public List<CustomerDto> getAll() {
        List<CustomerDto> customerDtos = CustomerMapper.INSTANCE.customerEntityToDto(customerRepository.findAll());
        customerList = customerDtos;
        return customerDtos;
    }

    public List<CustomerDto> returnAllCustomer() {
        return customerList;
    }
}
