package inf.saveanimals.response.posts.sightedPets;

import com.querydsl.core.annotations.QueryProjection;
import inf.saveanimals.response.posts.CommentWriterDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SightedPetCommentDto {
    private Long id; // comment pk

    private CommentWriterDto writer; // 작성자 정보 (회원 pk, 네임, 프로필)

    private LocalDateTime createdAt; // 작성 시간
    private String content; // 내용

    private List<SightedPetCommentDto> children = new ArrayList<>(); // 대댓글

    @QueryProjection
    public SightedPetCommentDto(Long id,
                                Long userId, String user_nickname, String user_profile_image,
                                String content, LocalDateTime createdAt) {
        this.id = id;
        this.writer = CommentWriterDto.toDto(userId, user_nickname, user_profile_image);
        this.content = content;
        this.createdAt = createdAt;
    }
}
