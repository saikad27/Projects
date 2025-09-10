package com.example.mychat.config;

import com.example.mychat.model.MessageDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Bean
    public Map<Long, DeferredResult<MessageDetail>> request_registry(){
        return new HashMap<>();
    }
}
