package inf.saveanimals.domain.posts.sighted;


import inf.saveanimals.domain.posts.common.IsMainImg;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sighted_img")
public class SightedImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sighted_img_id")
    private Long id;

    private String imgUrl; // 이미지 url 경로

    @Enumerated(EnumType.STRING)
    private IsMainImg isMainImg; // 대표 이미지 여부

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "sighted_pets_id")
    private SightedPets sightedPets;

    public void assignToPost(SightedPets sightedPets) {
        this.sightedPets = sightedPets;
    }

    @Builder
    public SightedImg(String imgUrl, IsMainImg isMainImg, SightedPets sightedPets) {
        this.imgUrl = imgUrl;
        this.isMainImg = isMainImg;
        this.sightedPets = sightedPets;
    }



    @Override
    public String toString() {
        return "Sighted Post Img Info {" + imgUrl + '}';
    }
}
