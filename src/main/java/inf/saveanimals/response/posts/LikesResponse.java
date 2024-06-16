package inf.saveanimals.response.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikesResponse {
    String message;
    Integer totalLikes;

    @Builder
    public LikesResponse(String message, Integer totalLikes) {
        this.message = message;
        this.totalLikes = totalLikes;
    }

    public static LikesResponse of(String message, Integer totalLikes) {
        return LikesResponse.builder()
                .message(message)
                .totalLikes(totalLikes)
                .build();
    }
}
