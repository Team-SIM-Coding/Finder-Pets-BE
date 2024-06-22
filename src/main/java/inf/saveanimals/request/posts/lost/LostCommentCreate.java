package inf.saveanimals.request.posts.lost;

import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.users.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 실종_댓글 -생성 요청 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostCommentCreate {

    // 2개는 로그인 정보로 받기
    private String user_nickname; // 작성자 닉네임
    private String user_image; // 작성자 프로필

    private String comment; // 내용

    @Builder
    public LostCommentCreate(String comment) {
        this.comment = comment;
    }


    public LostComments toEntity(User user) {
        return LostComments.builder()
                .user_nickname(user.getName())
                .user_image(user.getImg())
                .content(comment)
                .build();
    }


}
