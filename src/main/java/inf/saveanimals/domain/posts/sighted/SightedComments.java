package inf.saveanimals.domain.posts.sighted;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn
    private SightedPets sightedPets;


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
}
