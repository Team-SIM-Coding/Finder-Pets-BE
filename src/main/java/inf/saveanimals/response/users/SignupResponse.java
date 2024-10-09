package inf.saveanimals.response.users;

import inf.saveanimals.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupResponse {

    private String email;
    private String message;

    @Builder
    public SignupResponse(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public static SignupResponse fromEntity(User user) {
        return SignupResponse.builder()
                .email(user.getEmail())
                .message("CREATED")
                .build();
    }
}
