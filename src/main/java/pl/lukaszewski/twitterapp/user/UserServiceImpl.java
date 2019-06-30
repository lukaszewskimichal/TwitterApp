package pl.lukaszewski.twitterapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.lukaszewski.twitterapp.authentication.AuthenticationFacade;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public User getLoggedUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findById(customUserDetails.getId());
        return userOptional.get();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get();
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public boolean validUserEmail(String email) {
        try {
            List<User> userList = userRepository.findAll();
            if (null == userList) {
                return true;
            }
            for (User user : userList) {
                if (user.getEmail().equals(email)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("validUserEmailException: " + e.getMessage());

        }
        return true;
    }
}
