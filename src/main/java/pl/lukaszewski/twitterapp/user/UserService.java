package pl.lukaszewski.twitterapp.user;

import java.util.List;

public interface UserService {

    User getLoggedUser();

    User getUserById(long id);

    public List<User> getAllUsers();

    public boolean validUserEmail(String email);
}
