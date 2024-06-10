package inf.saveanimals.domain.posts.lost;

import inf.saveanimals.domain.users.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 실종 - 관심하트 [테이블]
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "lost_like")
public class LostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lost_pets_id")
    private LostPets lostPets;

    @Builder
    public LostLike(User user, LostPets lostPets) {
        this.user = user;
        this.lostPets = lostPets;
    }
}
