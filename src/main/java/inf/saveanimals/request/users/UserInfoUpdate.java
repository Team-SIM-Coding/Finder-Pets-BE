package inf.saveanimals.request.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoUpdate {

    private String nickname;
    private String phone;
    private String like_area;
    private String like_kind;
    private String like_animal;
    private String intro;
}
