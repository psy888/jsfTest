package org.example.jsftest;

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
