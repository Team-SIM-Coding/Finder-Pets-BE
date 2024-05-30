package inf.saveanimals.domain.animals;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 반려동물 정보
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "my_pets")
public class MyPets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_pets_id")
    private Long id;

    private String name; // 반려동물 이름
    
    @Enumerated(EnumType.STRING)
    private Breed breed; // 품종

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    private float weight; // 몸무게
    private String color; //색상
    private String age; // 나이, 예시 ->2019(년생)

    @Enumerated(EnumType.STRING)
    private NeuteringStatus neuteringStatus; // 중성화 여부

    private String specialMark; // 특징 (소개)
    private String petOwnerTel; // 보호자 연락처
    
    // 그외
    private boolean isAdoption; // 입양 유무
    private LocalDateTime adoptionDay; // 입양날짜
    private LocalDateTime birthDay; // 생일
    private String petImage; // 펫 사진

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
