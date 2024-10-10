package inf.saveanimals.response.posts.lostPets;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostPetCommentDto {
    private Long id; // comment pk
    private Long userId;

    private String user_nickname;
    private String user_profile_image;
    private LocalDateTime createdAt; // 작성 시간
    private String content; // 내용

    @QueryProjection
    public LostPetCommentDto(Long id, Long userId, String user_nickname, String user_profile_image, String content, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.user_nickname = user_nickname;
        this.user_profile_image = user_profile_image;
        this.content = content;
        this.createdAt = createdAt;
    }
}
