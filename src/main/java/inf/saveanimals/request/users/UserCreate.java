package inf.saveanimals.request.users;

import inf.saveanimals.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class UserCreate {

    private String email;
    private String password;
    private String name;
    private String phone;

    @Builder
    public UserCreate(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public User ofEntity() {
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .name(this.getName())
                .userTel(this.getPhone())
                .build();
    }

    public void encodePwd(BCryptPasswordEncoder encoder) {
        String encodePwd = encoder.encode(password);
        this.password = encodePwd;
    }
}
