package inf.saveanimals.domain.posts.sighted;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.District;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.posts.ImageNotFoundException;
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

/**
 * 제보 - 포스팅 본문 [테이블]
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sighted_pets")
public class SightedPets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sighted_pets_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category; // 글 카테고리 (실종 / 제보)

    @Enumerated(EnumType.STRING)
    private IsCompleted isCompleted; // 글 상태 (해결 유무)


    // --실종동물 등록 시 정보 입력--
    private String writerNickname; // 작성자 닉네임
    private String writerProfileImage; // 작성자 프로필

    @Enumerated(EnumType.STRING)
    private Breed breed; // 품종

    @Enumerated(EnumType.STRING)
    private BreedGroup breedGroup; // (동물 종류)

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    private String weight; // 몸무게
    private String color; //색상
    private String age; // 나이

    @Enumerated(EnumType.STRING)
    private NeuteringStatus neuteringStatus; // 중성화 여부

    private String specialMark; // 특징

    private String reporterTel; // 제보자 연락처

    /**
     * 만약, 위치정보를 (시/구)를 받을 경우로 가정하
     */
    @Enumerated(EnumType.STRING)
    private District district;

    @Enumerated(EnumType.STRING)
    private City city;

    private LocalDateTime foundDate; // 목격한 날짜
    private LocalDateTime createdAt; // 작성 시간
    private String foundPlace; // 발견된 장소

    // 지도로 받은 정보
    private String latitude;  // 장소 - 위도
    private String longitude; // 장소 - 경도


    @Column(name = "views", nullable = false)
    private Integer views; //조회수

    @Column(name = "total_like", nullable = false)
    private Integer totalLike; // 좋아요 수

    private String detailed; // 본문

    // 이미지
    @JsonIgnore
    @OneToMany(mappedBy = "sightedPets", cascade = CascadeType.ALL)
    private List<SightedImg> sightedImgList = new ArrayList<>();

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sightedPets")
    private List<SightedComments> comments = new ArrayList<>();

    @Builder
    public SightedPets(Breed breed, BreedGroup breedGroup, Gender gender,
                       String weight, String color, String age, NeuteringStatus neuteringStatus, String specialMark,
                       String reporterTel, District district, City city, LocalDateTime foundDate, String foundPlace,
                       String latitude, String longitude, String detailed, User user) {
        this.views = 0;
        this.totalLike = 0;
        this.isCompleted = IsCompleted.UNRESOLVED;
        this.category = Category.MISSING;
        this.createdAt = LocalDateTime.now();
        this.writerNickname = user.getName();
        this.writerProfileImage = user.getImg();
        this.breed = breed;
        this.breedGroup = breedGroup;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.neuteringStatus = neuteringStatus;
        this.specialMark = specialMark;
        this.reporterTel = reporterTel;
        this.district = district;
        this.city = city;
        this.foundDate = foundDate;
        this.foundPlace = foundPlace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.detailed = detailed;
        this.user = user;
    }


    public void update(SightedPetsEdit postEdit) {
        this.breed = postEdit.getKind();
        this.breedGroup = searchBreedGroup(postEdit.getKind());
        this.gender = postEdit.getGender();
        this.weight = postEdit.getWeight();
        this.color = postEdit.getColor();
        this.age = postEdit.getAge();
        this.neuteringStatus = postEdit.getIs_neutering();
        this.specialMark = postEdit.getCharacter();
        this.reporterTel = postEdit.getPhone();
        this.foundPlace = postEdit.getArea();
        this.foundDate = postEdit.getDate();
        this.district = postEdit.getDistrict();
        this.city = postEdit.getCity();
        this.longitude = postEdit.getLongitude();
        this.latitude = postEdit.getLatitude();
        this.detailed = postEdit.getDetailed();
    }



    public SightedPetsDetailResponse toSightedPetsDetailResponse() {
        return SightedPetsDetailResponse.builder()
                .pet_id(id)
                .category(category)
                .isCompleted(isCompleted)
                .name(writerNickname)
                .writerProfileImage(writerProfileImage)
                .foundDate(foundDate)
                .createdAt(createdAt)
                .breed(breed)
                .breedGroup(breedGroup)
                .gender(gender)
                .weight(weight)
                .color(color)
                .age(age)
                .neuteringStatus(neuteringStatus)
                .specialMark(specialMark)
                .reporterTel(reporterTel)
                .city(city)
                .district(district)
                .foundPlace(foundPlace)
                .latitude(latitude)
                .longitude(longitude)
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
            throw new ImageNotFoundException();
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

    private String fetchUserNickname() {
        return user.getNickname();
    }

    private String fetchUserProfileImage() {
        return user.getImg();
    }

}
