package inf.saveanimals.domain.posts.lost;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn
    private LostPets lostPets;

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
}
