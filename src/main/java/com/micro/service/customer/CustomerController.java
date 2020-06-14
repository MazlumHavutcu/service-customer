package com.micro.service.customer;

import com.micro.service.common.LogElapsedTime;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getCustomerById/{id}")
    @HystrixCommand
    public CustomerDto getCostumerById(@PathVariable("id") long id) {
        return customerService.getById(id);
    }

    @PutMapping("/save/")
    @HystrixCommand
    @LogElapsedTime
    public void createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        customerService.save(customerDto);
    }

    @DeleteMapping("/delete/{id}")
    @HystrixCommand
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteById(id);
    }

    @GetMapping("/getAll")
    @HystrixCommand
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAll();
    }
}
