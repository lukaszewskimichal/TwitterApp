package pl.lukaszewski.twitterapp.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import pl.lukaszewski.twitterapp.TwitterappApplication;


public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TwitterappApplication.class);
    }
}
