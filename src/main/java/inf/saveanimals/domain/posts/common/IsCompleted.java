package inf.saveanimals.domain.posts.common;

import lombok.Getter;

/**
 * 포스팅 글 해결유무 표시
 */
@Getter
public enum IsCompleted {

    UNRESOLVED("미해결", "UNRESOLVED", true),
    COMPLETION("완료", "COMPLETION", false);

    private String kCode;
    private String statusCode;
    private boolean is_completed;

    IsCompleted(String kCode, String statusCode, boolean is_completed) {
        this.kCode = kCode;
        this.statusCode = statusCode;
        this.is_completed = is_completed;
    }



}
