package pl.lukaszewski.twitterapp.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukaszewski.twitterapp.user.User;
import pl.lukaszewski.twitterapp.user.UserRepository;
import pl.lukaszewski.twitterapp.user.UserService;

import java.util.List;


@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRespository messageRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public int numberOfUnreadMessages() {
        return messageRespository.findAllByReciverAndIsReadIsFalse(userService.getLoggedUser()).size();
    }

    @Override
    public List<User> getReciversList() {
        List<User> userList = userRepository.findAll();
        userList.remove(userService.getLoggedUser());
        return userList;
    }
}
