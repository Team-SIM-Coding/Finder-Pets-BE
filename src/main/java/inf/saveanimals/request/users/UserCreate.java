package inf.saveanimals.request.users;

import inf.saveanimals.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreate {

    private String name;
    private String password;
    private String passwordCheck;
    private String email;

    @Builder
    public UserCreate(String email, String password, String passwordCheck, String name) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
    }

    public User ofEntity() {
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .name(this.getName())
                .build();
    }
}
