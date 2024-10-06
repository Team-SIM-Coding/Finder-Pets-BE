package inf.saveanimals.response.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * -Response-
 * 사용자 정보 반환 + token Dto
 */

@Getter
@Setter
@NoArgsConstructor
public class UserTokenDto {
    private String email;

    private String grantType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public UserTokenDto(String email, String accessToken, String refreshToken, String grantType) {
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.grantType = grantType;
    }

    // Entity -> DTO
    public static UserTokenDto fromEntity(UserDetails member, CreateTokenResponse createTokenResponse) {
        return UserTokenDto.builder()
                .email(member.getUsername())
                .accessToken(createTokenResponse.getAccessToken())
                .refreshToken(createTokenResponse.getRefreshToken())
                .grantType(createTokenResponse.getGrantType())
                .build();
    }
}
