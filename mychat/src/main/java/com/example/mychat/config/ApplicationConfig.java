package com.example.mychat.config;

import com.example.mychat.dto.MessageDTO;
import com.example.mychat.model.MessageDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.async.DeferredResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ApplicationConfig {

    @Bean
    public Map<Long, DeferredResult<MessageDTO>> request_registry(){
        return new ConcurrentHashMap<Long,DeferredResult<MessageDTO>>();
    }

    @Bean
    public Map<Long,Long> onlineUserRegistry(){
        return new ConcurrentHashMap<Long,Long>();
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
