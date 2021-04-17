package com.wolox.test.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.wolox.test.infrastructure.rest.feign")
public class FeignClientConfig {
}
