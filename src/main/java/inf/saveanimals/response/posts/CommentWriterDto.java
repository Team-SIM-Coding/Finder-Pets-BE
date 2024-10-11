package inf.saveanimals.response.posts;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentWriterDto {

    private Long userId;

    private String user_nickname;
    private String user_profile_image;

    @Builder
    public CommentWriterDto(Long userId, String user_nickname, String user_profile_image) {
        this.userId = userId;
        this.user_nickname = user_nickname;
        this.user_profile_image = user_profile_image;
    }

    public static CommentWriterDto toDto(Long userId, String user_nickname, String user_profile_image) {
        return CommentWriterDto.builder()
                .userId(userId)
                .user_nickname(user_nickname)
                .user_profile_image(user_profile_image)
                .build();
    }
}
