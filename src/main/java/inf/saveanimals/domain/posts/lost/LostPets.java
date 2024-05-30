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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private IsCompleted isCompleted; // 글 상태

    // --실종동물 등록 시 정보 입력--
    @Enumerated(EnumType.STRING)
    private Breed breed; // 품종

    @Enumerated(EnumType.STRING)
    private BreedGroup breedGroup; // (동물 종류)

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    private float weight; // 몸무게
    private String color; //색상
    private String age; // 나이

    @Enumerated(EnumType.STRING)
    private NeuteringStatus neuteringStatus; // 중성화 여부

    private String specialMark; // 특징
    private String petOwnerTel; // 보호자 연락처
    private String content; // 포스팅 본문

    private String happenPlace; // 잃어버린 장소

    /**
     * 만약, 위치정보를 (시/구)를 받을 경우로 가정하
     */
    @Enumerated(EnumType.STRING)
    private City city;  // 시

    @Enumerated(EnumType.STRING)
    private Districts districts; // 구(군)

    // 지도 api 받고 나서?
    private String latitude; //  잃어버린 장소 - 위도
    private String longitude; // 잃어버린 장소 - 경도

    // 이미지
    @JsonIgnore
    @OneToMany(mappedBy = "lostPets", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LostImg> lostImgs = new ArrayList<>();

    @Column(name = "views", nullable = false)
    private Integer views; //조회수

    @Column(name = "total_like", nullable = false)
    private Integer totalLike; // 좋아요 수

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lostPets")
    private List<LostComments> comments = new ArrayList<>();

    @Builder
    public LostPets(Breed breed, Gender gender, float weight, String color,
                    String age, NeuteringStatus neuteringStatus,
                    String specialMark, String petOwnerTel,
                    String content, String happenPlace, User user,
                    City city, Districts districts) {
        this.category = Category.MISSING;
        this.isCompleted = IsCompleted.UNRESOLVED;
        this.views = 0;
        this.totalLike = 0;
        this.breed = breed;
        this.breedGroup = searchBreedGroup(breed);
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.age = age;
        this.neuteringStatus = neuteringStatus;
        this.specialMark = specialMark;
        this.petOwnerTel = petOwnerTel;
        this.content = content;
        this.happenPlace = happenPlace;
        this.user = user;
        this.city = city;
        this.districts = districts;
    }

    public void update(LostPetsEdit postEdit) {
        this.breed = postEdit.getBreed();
        this.breedGroup = searchBreedGroup(postEdit.getBreed());
        this.gender = postEdit.getGender();
        this.weight = postEdit.getWeight();
        this.color = postEdit.getColor();
        this.age = postEdit.getAge();
        this.neuteringStatus = postEdit.getNeuteringStatus();
        this.specialMark = postEdit.getSpecialMark();
        this.petOwnerTel = postEdit.getPetOwnerTel();
        this.content = postEdit.getContent();
        this.happenPlace = postEdit.getHappenPlace();
        this.city = postEdit.getCity();
        this.districts = postEdit.getDistricts();
    }

    public LostPetsDetailResponse toMissingPostDetailResponse() {
        return LostPetsDetailResponse.builder()
                .id(user.getId())
                .category(category)
                .isCompleted(isCompleted)
                .breed(breed)
                .gender(gender)
                .weight(weight)
                .color(color)
                .age(age)
                .neuteringStatus(neuteringStatus)
                .specialMark(specialMark)
                .petOwnerTel(petOwnerTel)
                .content(content)
                .happenPlace(happenPlace)
                .latitude(latitude)
                .longitude(longitude)
                .imgPaths(getImgPaths())
                .views(views)
                .totalLike(totalLike)
                .authorNick(user.getNickname())
                .city(city)
                .districts(districts)
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


    // 관심하트 관련
    public void addLike() {
        this.totalLike++;
    }

    public void deleteLike() {
        this.totalLike--;
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


}
