package inf.saveanimals.domain.posts.sighted;

import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

/**
 * 제보 - 댓글 [테이블]
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sighted_comments")
public class SightedComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sighted_comments_id")
    private Long id;

    @Column(nullable = false)
    private String user_nickname; // 작성자 닉네임
    private String user_image; // 작성자 프로필
    private LocalDateTime create_at; // 작성 시간
    private String content; // 내용

    @ManyToOne(fetch =  LAZY)
    @JoinColumn(name = "sighted_pets_id")
    private SightedPets sightedPets;

    @ManyToOne(fetch =  LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 대댓글
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sighted_parent_id")
    private SightedComments sightedParent;

    @OneToMany(mappedBy = "sightedParent", orphanRemoval = true)
    private List<SightedComments> sightedChildren = new ArrayList<>();



    @Builder
    public SightedComments(String user_nickname, String user_image, String content) {
        this.user_nickname = user_nickname;
        this.user_image = user_image;
        this.create_at = LocalDateTime.now();
        this.content = content;
    }

    public void assignToLostPets(SightedPets sightedPets) {
        this.sightedPets = sightedPets;
    }

    public void assignToUser(User user) {
        this.user = user;
    }

    public void update(String message) {
        this.content = message;
    }

    public void updateParent(SightedComments comment) {
        this.sightedParent = comment;
    }
}
