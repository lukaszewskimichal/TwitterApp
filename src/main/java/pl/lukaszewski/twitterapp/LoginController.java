package pl.lukaszewski.twitterapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.lukaszewski.twitterapp.authentication.AuthenticationFacade;
import pl.lukaszewski.twitterapp.message.MessageService;
import pl.lukaszewski.twitterapp.tweet.Tweet;
import pl.lukaszewski.twitterapp.user.Role;
import pl.lukaszewski.twitterapp.user.User;
import pl.lukaszewski.twitterapp.user.UserRepository;
import pl.lukaszewski.twitterapp.validator.ValidationGroupUniqueEmail;


import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String mainPage(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("pageMessage", "Welcome on Tweeter App index page.");
        model.addAttribute("formMessage", "Add tweet");
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("formAction", "addTweet");
        model.addAttribute("unreadMessages", messageService.numberOfUnreadMessages());
        return "tweetForm";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistration(@Validated({ValidationGroupUniqueEmail.class, Default.class}) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setActive(true);
        user.setRoles(roles);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        model.addAttribute("registrationResult", 1);
        return "login";
    }
}
