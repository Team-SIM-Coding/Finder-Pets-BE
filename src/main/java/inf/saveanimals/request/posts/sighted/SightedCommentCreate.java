package inf.saveanimals.request.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedComments;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SightedCommentCreate {

    private String user_nickname; // 작성자 닉네임
    private String user_image; // 작성자 프로필
    private String content; // 내용

    @Builder
    public SightedCommentCreate(String user_nickname, String user_image, String content) {
        this.user_nickname = user_nickname;
        this.user_image = user_image;
        this.content = content;
    }

    public SightedComments toEntity() {
        return SightedComments.builder()
                .user_nickname(user_nickname)
                .user_image(user_image)
                .content(content)
                .build();
    }
}
