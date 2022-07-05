package fun.epak.pak.model.user;

import fun.epak.pak.model.Comment;
import fun.epak.pak.model.Post;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Users")
@Builder
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String username;
    private String imageName;
    private LocalDate registerDate;
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<UserRole> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Post> posts;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;
    @ManyToMany
    @JoinTable(name = "subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id"))
    private Set<User> subscriptions;
}
