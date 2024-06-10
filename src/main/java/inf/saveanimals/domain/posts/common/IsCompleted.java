package inf.saveanimals.domain.posts.common;

import lombok.Getter;

/**
 * 포스팅 글 해결유무 표시
 */
@Getter
public enum IsCompleted {

    UNRESOLVED("미해결", "UNRESOLVED"),
    COMPLETION("완료", "COMPLETION");

    private String kCode;
    private String statusCode;

    IsCompleted(String kCode, String statusCode) {
        this.kCode = kCode;
        this.statusCode = statusCode;
    }



}
