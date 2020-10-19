package org.example.jsftest.service;

import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService
{
    private String message = "bean test";

    @Override
    public String getGreetingMessage() {
        return message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
