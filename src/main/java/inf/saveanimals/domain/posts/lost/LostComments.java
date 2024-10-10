package inf.saveanimals.domain.posts.lost;

import inf.saveanimals.domain.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

/**
 * 실종 - 댓글 [테이블]
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "lost_comments")
public class LostComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_comments_id")
    private Long id;

    @Column(nullable = false)
    private String user_nickname; // 작성자 닉네임
    private String user_image; // 작성자 프로필
    private LocalDateTime create_at; // 작성 시간
    private String content; // 내용

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lost_pets_id")
    private LostPets lostPets;

    @ManyToOne(fetch =  LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public LostComments(String user_nickname, String user_image, String content) {
        this.user_nickname = user_nickname;
        this.user_image = user_image;
        this.create_at = LocalDateTime.now();
        this.content = content;
    }


    public void assignToLostPets(LostPets lostPets) {
        this.lostPets = lostPets;
    }

    public void assignToUser(User user) {
        this.user = user;
    }

    public void update(String message) {
        this.content = message;
    }
}
