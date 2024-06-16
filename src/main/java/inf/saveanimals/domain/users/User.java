package inf.saveanimals.domain.users;

import inf.saveanimals.domain.animals.MyPets;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 회원 [테이블]
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
@DiscriminatorValue("USER")
public class User implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    protected UserLevel userLevel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<LostPets> lostPets = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<SightedPets> sightedPets = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MyPets> myPetsList = new ArrayList<>();

    @Builder
    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.userLevel = UserLevel.USER;
        this.img = "https://storage.googleapis.com/java-board-bucket/defaultImg.jpg";
    }

    @Builder(builderMethodName = "test")
    public User(String name, String nickname, String email, String password, String img, String userTel) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.img = img;
        this.userTel = userTel;
    }

    public void uploadUserInfo(String userTel, String name) {
        this.userTel = userTel;
        this.name = name;
    }

    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }

    //========== UserDetails implements ==========//
    /**
     * Token을 고유한 Email 값으로 생성합니다
     * @return email;
     */

    private String roles;
    public void settingRoles() {
        this.roles = UserLevel.USER.getCode();
    }

    public List<String> getRoleList() {
        if (this.roles == null) {
            return Collections.emptyList(); // 빈 목록 반환 또는 다른 처리 수행
        }
        return Arrays.asList(this.roles.split(","));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority("ROLE_" + this.userLevel.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
