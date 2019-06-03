package com.meibanlu.qa.service;

import com.iflytek.cloud.speech.SpeechUtility;
import com.meibanlu.qa.service.util.LibLoader;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class ServiceApplication {

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
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("windows")){
            LibLoader.loadLib("msc64.dll");
        }else{
            LibLoader.loadLib("libmsc64.so");
        }
        SpeechUtility.createUtility("appid=5cc6b609");
        SpringApplication.run(ServiceApplication.class, args);
    }
}
