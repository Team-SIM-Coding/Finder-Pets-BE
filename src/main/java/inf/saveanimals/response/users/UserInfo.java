package inf.saveanimals.response.users;

import inf.saveanimals.domain.users.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {

    private String email;
    private String name;
    private String nickname;
    private String phone;
    private String like_area;
    private String like_kind;
    private String like_animal;
    private String intro;


    public static UserInfo fromEntity(User user) {
        return UserInfo.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .phone(user.getUserTel())
                .like_area(user.getLike_area())
                .like_kind(user.getLike_kind())
                .like_animal(user.getLike_animal())
                .intro(user.getIntro())
                .build();
    }
}
