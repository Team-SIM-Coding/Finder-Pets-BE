package inf.saveanimals.request.posts.lost;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostCommentEdit {

    private String comment; // 내용

    @Builder
    public LostCommentEdit(String comment) {
        this.comment = comment;
    }
}
