package pl.lukaszewski.twitterapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lukaszewski.twitterapp.user.UserService;
import pl.lukaszewski.twitterapp.validator.UniqueEmailValidator;


@Configuration
public class ValidationCofiguration {

    @Autowired
    private UserService userService;

    @Bean
    public UniqueEmailValidator uniqueEmailValidator() {

        return  new UniqueEmailValidator(userService);
    }
}
