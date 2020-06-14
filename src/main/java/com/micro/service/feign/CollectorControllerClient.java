package com.micro.service.feign;

import com.micro.service.customer.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "collector")
@RequestMapping("/smsMailQueue")
public interface CollectorControllerClient {

    @PutMapping("/sendInfo")
    void sendInfo(@RequestBody CustomerDto customerDto);

    @PutMapping("/sendSms")
    void sendSms(@RequestBody CustomerDto customerDto);
}
