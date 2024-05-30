package inf.saveanimals.domain.posts.sighted;

import inf.saveanimals.domain.users.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "sighted_like")
public class SightedLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sighted_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sighted_pets_id")
    private SightedPets sightedPets;

    @Builder
    public SightedLike(User user, SightedPets sightedPets) {
        this.user = user;
        this.sightedPets = sightedPets;
    }
}
