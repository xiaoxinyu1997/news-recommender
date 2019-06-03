package com.meibanlu.qa.analysis;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class AnalysisApplication {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {
        return builder.errorHandler(
                new ResponseErrorHandler(){

                    @Override
                    public boolean hasError(@NotNull ClientHttpResponse response) throws IOException {
                        return false;
                    }

                    @Override
                    public void handleError(@NotNull ClientHttpResponse response) throws IOException {

                    }
                }
        ).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(AnalysisApplication.class, args);
    }

}
