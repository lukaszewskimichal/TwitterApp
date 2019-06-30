package pl.lukaszewski.twitterapp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.lukaszewski.twitterapp.user.User;
import pl.lukaszewski.twitterapp.user.UserRepository;


@Component
public class ReceiverListConverter implements Converter<String, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(String s) {
        return userRepository.getOne(Long.parseLong(s));
    }
}
