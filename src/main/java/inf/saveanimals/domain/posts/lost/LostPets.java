package inf.saveanimals.domain.posts.lost;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.common.Category;
import inf.saveanimals.domain.posts.common.IsCompleted;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.ImageNotFound;
import inf.saveanimals.request.posts.lost.LostPetsEdit;
import inf.saveanimals.response.posts.lostPets.LostPetsDetailResponse;
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
 * 실종 - 포스팅 본문 [테이블]
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "lost_pets")
public class LostPets {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_pets_id")
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
    private String petOwnerTel; // 보호자 연락처
    private String detailed; // 본문

    /**
     * 만약, 위치정보를 (시/구)를 받을 경우로 가정하
     */
    @Enumerated(EnumType.STRING)
    private City city;  // 시

    @Enumerated(EnumType.STRING)
    private Districts districts; // 구(군)

    private LocalDateTime lostDate; // 실종 날짜
    private LocalDateTime createdAt; // 작성 시간
    private String happenPlace; // 잃어버린 장소

    // 지도 api 받고 나서?
    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

    @Column(name = "views", nullable = false)
    private Integer views; //조회수

    @Column(name = "total_like", nullable = false)
    private Integer totalLike; // 좋아요 수

    // 이미지
    @JsonIgnore
    @OneToMany(mappedBy = "lostPets", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LostImg> lostImgs = new ArrayList<>();

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lostPets")
    private List<LostComments> comments = new ArrayList<>();


    @Builder
    public LostPets(Breed breed, BreedGroup breedGroup, Gender gender, String weight, String color, String age,
                    NeuteringStatus neuteringStatus, String specialMark, String petOwnerTel,
                    City city, Districts districts, LocalDateTime lostDate, String happenPlace, String latitude, String longitude, String detailed, User user) {
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
        this.petOwnerTel = petOwnerTel;
        this.city = city;
        this.districts = districts;
        this.lostDate = lostDate;
        this.happenPlace = happenPlace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.detailed = detailed;
        this.user = user;
    }

    public void update(LostPetsEdit postEdit) {
        this.breed = postEdit.getKind();
        this.breedGroup = postEdit.getAnimal();
        this.gender = postEdit.getGender();
        this.weight = postEdit.getWeight();
        this.color = postEdit.getColor();
        this.age = postEdit.getAge();
        this.neuteringStatus = postEdit.getIs_neutering();
        this.specialMark = postEdit.getCharacter();
        this.petOwnerTel = postEdit.getPhone();
        this.happenPlace = postEdit.getArea();
        this.lostDate = postEdit.getDate();
        this.city = postEdit.getCity();
        this.districts = postEdit.getDistricts();
        this.longitude = postEdit.getLongitude();
        this.latitude = postEdit.getLatitude();
    }


    public LostPetsDetailResponse toMissingPostDetailResponse() {
        return LostPetsDetailResponse.builder()
                .pet_id(id)
                .category(category)
                .isCompleted(isCompleted)
                .name(writerNickname)
                .profile_image(writerProfileImage)
                .date(lostDate)
                .created_at(createdAt)
                .kind(breed)
                .animal(breedGroup)
                .gender(gender)
                .weight(weight)
                .color(color)
                .age(age)
                .is_neutering(neuteringStatus)
                .character(specialMark)
                .phone(petOwnerTel)
                .city(city)
                .districts(districts)
                .area(happenPlace)
                .latitude(latitude)
                .longitude(longitude)
                .img_url_list(getImgPaths())
                .views(views)
                .total_like(totalLike)
                .detailed(detailed)
                .build();
    }


    // 연관관계 메서드 --
    public void uploadImg(LostImg img) {
        lostImgs.add(img);
        img.assignToPost(this);
    }
    public void removeImg(LostImg img) {
        // --
        if (lostImgs.size() == 1) {
            throw new ImageNotFound();
        }

        this.lostImgs.remove(img);
    }

    // 실종된 반려동물을 찾았을 때, 완료 처리하는 로직
    public void finalizeCase() {
        this.isCompleted = IsCompleted.COMPLETION;
    }


    // 조회수
    public void viewCount() {
        this.views++;
    }


    // 관심하트 관련
    public void addLike() {
        this.totalLike++;
    }

    public void deleteLike() {
        this.totalLike--;
    }

    public void checkLikeCount() {
        if (totalLike < 1) {
            this.totalLike = 0;
        }
    }

    // 댓글 - 연관관계 로직
    public void addComment(LostComments comment) {
        comment.assignToLostPets(this);
        this.comments.add(comment);
    }

    private List<String> getImgPaths() {
        return lostImgs.stream()
                .map(LostImg::getImgUrl)
                .collect(Collectors.toList());
    }

    private BreedGroup searchBreedGroup(Breed searchTarget) {
        return BreedGroup.findGroup(searchTarget);

    }

    private String fetchUserNickname() {
        return user.getNickname();
    }

    private String fetchUserProfileImage() {
        return user.getImg();
    }

}
