package pl.lukaszewski.twitterapp.message;
import pl.lukaszewski.twitterapp.user.User;

import java.util.List;

public interface MessageService {

    public int numberOfUnreadMessages();

    public List<User> getReciversList();
}
