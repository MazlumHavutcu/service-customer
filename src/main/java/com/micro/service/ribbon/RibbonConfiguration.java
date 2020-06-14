package com.micro.service.ribbon;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RibbonClient(name = "collector-ribbon", configuration = RibbonConfig.class)
public class RibbonConfiguration {

    @Bean
    @LoadBalanced
    @Qualifier("LoadBalanced")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
