package pl.lukaszewski.twitterapp.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.lukaszewski.twitterapp.tweet.Tweet;
import pl.lukaszewski.twitterapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date created;

    @Size(min = 1, max = 60)
    private String text;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "tweet_comments",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    private Tweet tweet;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_comments",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public Comment(@Size(min = 1, max = 60) String text, User user) {
        this.text = text;
        this.user = user;
    }
}
