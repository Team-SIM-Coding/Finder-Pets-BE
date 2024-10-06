package inf.saveanimals.response.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String grantType;

}
