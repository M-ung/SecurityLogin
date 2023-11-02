package securitylogin.securitylogin.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user_table")
@ToString(exclude = {"followers", "following", "friends", "interests"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, unique = true, length = 100)
    private String userEmail;

    @Column(nullable = false, length = 100)
    private String userPassword;

    @Column(length = 255)
    private String userProfile;

    @Column(nullable = false)
    private double userScore;

//    @ManyToMany
//    @JoinTable(
//            name = "user_followers",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "follower_id")
//    )
//    private List<User> followers = new ArrayList<>();
//
//    @ManyToMany(mappedBy = "followers")
//    private List<User> following = new ArrayList<>();
//
//    @ManyToMany
//    @JoinTable(
//            name = "user_friends",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "friend_id")
//    )
//    private List<User> friends = new ArrayList<>();
//
//    @ElementCollection
//    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "interest")
//    private List<Double> interests = new ArrayList<>();
}
