package inf.saveanimals.domain.posts.sighted;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;
import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.ImageNotFound;
import inf.saveanimals.request.posts.sighted.SightedPetsEdit;
import inf.saveanimals.response.posts.sightedPets.SightedPetsDetailResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sighted_pets")
public class SightedPets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sighted_pets_id")
    private Long id;

    private String authorNick; // 작성자 닉네임
    private String authorImg; // 작성자 프로필

    @Enumerated(EnumType.STRING)
    private Category category; // 글 카테고리 (실종 / 제보)

    @Enumerated(EnumType.STRING)
    private IsCompleted isCompleted; // 글 상태

    private LocalDateTime foundDate; // 목격한 날짜
    private LocalDateTime createdAt; // 작성 시간
    private String foundPlace; // 발견된 장소
    private String specialMark; // 상세 설명 (내용 입력칸)
    private String reporterTel; // 제보자 연락처

    // 지도로 받은 정보
    private String latitude;  // 장소 - 위도
    private String longitude; // 장소 - 경도

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private Districts districts;

    @Enumerated(EnumType.STRING)
    private BreedGroup breedGroup; // (동물 종류)

    @Enumerated(EnumType.STRING)
    private Breed breed; // 품종

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sightedPets")
    private List<SightedComments> comments = new ArrayList<>();


    // 이미지
    @JsonIgnore
    @OneToMany(mappedBy = "sightedPets", cascade = CascadeType.ALL)
    private List<SightedImg> sightedImgList = new ArrayList<>();

    @Column(name = "views", nullable = false)
    private Integer views; //조회수

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_like", nullable = false)
    private Integer totalLike; // 좋아요 수

    @Builder
    public SightedPets(User user, String reporterTel, Breed breed, LocalDateTime foundDate, String foundPlace, String specialMark,
                       City city, Districts districts) {
        this.user = user;
        this.authorNick = user.getNickname();
        this.authorImg = user.getImg();
        this.reporterTel = reporterTel;
        this.breed = breed;
        this.breedGroup = searchBreedGroup(breed);
        this.category = Category.SIGHTED;
        this.isCompleted = IsCompleted.UNRESOLVED;
        this.views = 0;
        this.totalLike = 0;
        this.createdAt = LocalDateTime.now();
        this.foundDate = foundDate;
        this.foundPlace = foundPlace;
        this.specialMark = specialMark;
        this.city = city;
        this.districts = districts;
    }

    public void update(SightedPetsEdit sightedPetsEdit) {
        this.breed = sightedPetsEdit.getBreed();
        this.breedGroup = searchBreedGroup(sightedPetsEdit.getBreed());
        this.specialMark = sightedPetsEdit.getSpecialMark();
        this.reporterTel = sightedPetsEdit.getReporterTel();
        this.foundDate = sightedPetsEdit.getFoundDate();
        this.foundPlace = sightedPetsEdit.getFoundPlace();
        this.city = sightedPetsEdit.getCity();
        this.districts = sightedPetsEdit.getDistricts();
    }

    public SightedPetsDetailResponse toSightedPetsDetailResponse() {
        return SightedPetsDetailResponse.builder()
                .id(id)
                .category(category)
                .isCompleted(isCompleted)
                .foundDate(foundDate)
                .createdAt(createdAt)
                .foundPlace(foundPlace)
                .specialMark(specialMark)
                .reporterTel(reporterTel)
                .latitude(latitude)
                .longitude(longitude)
                .city(city)
                .districts(districts)
                .breedGroup(breedGroup)
                .breed(breed)
                .imgPaths(getImgPaths())
                .views(views)
                .totalLike(totalLike)
                .build();
    }



    // 연관관계 메서드 --
    public void uploadImg(SightedImg img) {
        sightedImgList.add(img);
        img.assignToPost(this);
    }
    public void removeImg(SightedImg img) {
        // --
        if (sightedImgList.size() == 1) {
            throw new ImageNotFound();
        }

        this.sightedImgList.remove(img);
    }

    // 실종된 반려동물을 찾았을 때, 완료 처리하는 로직
    public void finalizeCase() {
        this.isCompleted = IsCompleted.COMPLETION;
    }

    private List<String> getImgPaths() {
        return sightedImgList.stream()
                .map(SightedImg::getImgUrl)
                .collect(Collectors.toList());
    }

    private BreedGroup searchBreedGroup(Breed searchTarget) {
        return BreedGroup.findGroup(searchTarget);

    }

    // 관심하트 관련
    public void addLike() {
        this.totalLike++;
    }

    public void deleteLike() {
        this.totalLike--;
    }

    // 댓글 - 연관관계 로직
    public void addComment(SightedComments comment) {
        comment.assignToLostPets(this);
        this.comments.add(comment);
    }
}
