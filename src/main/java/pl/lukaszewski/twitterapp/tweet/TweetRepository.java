package pl.lukaszewski.twitterapp.tweet;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukaszewski.twitterapp.user.User;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    public List<Tweet> findAllByOrderByIdDesc();

    public List<Tweet> findAllByUser(User user);

    public List<Tweet> findFirst5ByOrderByIdDesc();
}
