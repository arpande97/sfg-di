package com.example.sfgdi.services;

import com.example.sfgdi.services.GreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


public class I18nSpanishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hola Munda - ES";
    }
}
