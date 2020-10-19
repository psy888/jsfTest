package org.example.jsftest;

import org.example.jsftest.service.MessageService;
import org.example.jsftest.service.MessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.example.jsftest"})
public class Config
{
    // @Bean(name = "service")
    // public MessageService getService(){
    //     return new MessageServiceImpl();
    // }
}
