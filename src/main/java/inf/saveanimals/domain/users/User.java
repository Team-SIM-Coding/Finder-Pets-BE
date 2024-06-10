package inf.saveanimals.domain.users;

import inf.saveanimals.domain.animals.MyPets;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 회원 [테이블]
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
@DiscriminatorValue("USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name; // 이름
    private String nickname; // 닉네임
    private String email; // 이메일
    private String password; // 비밀번호
    private String img; // 프로필 경로
    private String userTel; // 전화번호

    // 관심동물, 관심지역, 관심품종, 자기소개



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<LostPets> lostPets = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<SightedPets> sightedPets = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MyPets> myPetsList = new ArrayList<>();


    @Builder
    public User(String name, String email, String password, String img, String userTel, String nickname) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.img = img;
        this.userTel = userTel;
        this.nickname = nickname;
    }
}
