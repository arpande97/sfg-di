package com.example.sfgdi.services;

import com.example.repositories.EnglishGreetingRepository;
import com.example.sfgdi.services.GreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


public class I18nEnglishGreetingService implements GreetingService {

    private final EnglishGreetingRepository englishGreetingRepository;

    public I18nEnglishGreetingService(EnglishGreetingRepository englishGreetingRepository) {
        this.englishGreetingRepository = englishGreetingRepository;
    }

    @Override
    public String sayGreeting() {
        return englishGreetingRepository.getGreeting();
    }
}
