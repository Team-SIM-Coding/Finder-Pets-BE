package inf.saveanimals.request.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -Request-
 * 회원 정보 변경 요청 dto
 */
@Getter
@Setter
@NoArgsConstructor
public class UserEdit {

    private String password;
    private String passwordCheck;
    private String username;

    @Builder
    public UserEdit(String password, String passwordCheck, String username) {
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
    }
}
