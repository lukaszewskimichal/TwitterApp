package pl.lukaszewski.twitterapp.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    public Authentication getAuthentication();
}
