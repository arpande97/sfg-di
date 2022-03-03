package com.example.repositories;

public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository {
    @Override
    public String getGreeting() {
        return "Hello World - EN...this is from the repo";
    }
}
