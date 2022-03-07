package com.example.sfgdi.config;

import com.example.pets.PetService;
import com.example.pets.PetServiceFactory;
import com.example.sfgdi.datasource.FakeDataSource;
import com.example.sfgdi.services.I18nEnglishGreetingService;
import com.example.sfgdi.services.I18nSpanishGreetingService;
import com.example.repositories.EnglishGreetingRepository;
import com.example.repositories.EnglishGreetingRepositoryImpl;
import com.example.sfgdi.services.ConstructorInjectedGreetingService;
import com.example.sfgdi.services.PrimaryGreetingService;
import com.example.sfgdi.services.PropertyInjectedGreetingService;
import com.example.sfgdi.services.SetterInjectedGreetingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@PropertySource("classpath:datasource.properties")
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {


    @Bean
    FakeDataSource fakeDataSource(@Value("${com.username}") String username,
                                  @Value("${com.password}") String password,
                                  @Value("${com.jdbcurl}") String jdbcurl) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcurl(jdbcurl);

        return fakeDataSource;
    }

    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    //the bean name will be the name of the constructor, in
    //this case it will be "petService"
    PetService petService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }

    @Profile("cat")
    @Bean("petService")
    PetService catPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("cat");
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18nSpanishGreetingService i18nSpanishGreetingService() {
        return new I18nSpanishGreetingService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }

    //the constructor bean is defined in the xml config file in the resources. On top, you can make
    //sure the greetingserviceconfig looks for the xml file by @importresource annotation.
    //@Bean
   /* ConstructorInjectedGreetingService constructorInjectedGreetingService() {
        return new ConstructorInjectedGreetingService();
    }*/

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService() {
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }
}
