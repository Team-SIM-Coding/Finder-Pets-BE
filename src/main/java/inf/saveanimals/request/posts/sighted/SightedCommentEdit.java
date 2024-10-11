package inf.saveanimals.request.posts.sighted;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SightedCommentEdit {

    private String comment; // 내용

    @Builder
    public SightedCommentEdit(String comment) {
        this.comment = comment;
    }
}
