package inf.saveanimals.domain.posts.lost;

import inf.saveanimals.domain.posts.common.IsMainImg;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 실종 - 이미지 [테이블]
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name= "lost_img")
public class LostImg {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_img_id")
    private Long id;

    private String imgUrl; // 이미지 경로

    @Enumerated(EnumType.STRING)
    private IsMainImg isMainImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lost_pets_id")
    private LostPets lostPets;

    @Builder
    public LostImg(String imgUrl, IsMainImg isMainImg) {
        this.imgUrl = imgUrl;
        this.isMainImg = isMainImg;
    }



    public void assignToPost(LostPets lostPets) {
        this.lostPets = lostPets;
    }

    @Override
    public String toString() {
        return "Missing Post Img Info {" + imgUrl + '}';
    }
}
