package inf.saveanimals.request.posts.lost;

import inf.saveanimals.domain.posts.lost.LostComments;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostCommentCreate {

    private String user_nickname; // 작성자 닉네임
    private String user_image; // 작성자 프로필
    private String content; // 내용

    @Builder
    public LostCommentCreate(String user_nickname, String user_image, String content) {
        this.user_nickname = user_nickname;
        this.user_image = user_image;
        this.content = content;
    }

    public LostComments toEntity() {
        return LostComments.builder()
                .user_nickname(user_nickname)
                .user_image(user_image)
                .content(content)
                .build();
    }
}
