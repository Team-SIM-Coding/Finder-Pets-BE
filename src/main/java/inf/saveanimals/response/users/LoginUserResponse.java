package inf.saveanimals.response.users;

import inf.saveanimals.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserResponse {
    // 사용자 DB 인덱스 값을 굳이 사용자에게 노출시킬 필요는 없다고 생각
    private String email;


    @Builder
    public LoginUserResponse(String email) {
        this.email = email;
    }

    // Entity -> DTO
    public static LoginUserResponse fromEntity(User user) {
        return LoginUserResponse.builder()
                .email(user.getEmail())
                .build();
    }
}