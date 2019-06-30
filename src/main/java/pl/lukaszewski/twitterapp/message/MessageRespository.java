package pl.lukaszewski.twitterapp.message;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukaszewski.twitterapp.user.User;

import java.util.List;

public interface MessageRespository extends JpaRepository<Message, Long> {

    public List<Message> findAllByReciverAndIsReadIsFalse(User reciver);
    public List<Message> findAllByReciver(User reciver);
    public List<Message> findAllBySender(User sender);
}
