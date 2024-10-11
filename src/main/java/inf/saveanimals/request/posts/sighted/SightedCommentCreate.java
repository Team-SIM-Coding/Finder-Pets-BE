package inf.saveanimals.request.posts.sighted;

import inf.saveanimals.domain.posts.sighted.SightedComments;
import inf.saveanimals.domain.users.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 제보_댓글 - 생성 요청 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SightedCommentCreate {

    // 2개는 로그인 정보로 받기
    private String user_nickname; // 작성자 닉네임
    private String user_image; // 작성자 프로필

    private String comment; // 내용

    private Long parentId; // 부모 댓글 pk

    @Builder
    public SightedCommentCreate( String comment) {
        this.comment = comment;
    }

    public SightedComments toEntity(User user) {
        return SightedComments.builder()
                .user_nickname(user.getName())
                .user_image(user.getImg())
                .content(comment)
                .build();
    }
}
