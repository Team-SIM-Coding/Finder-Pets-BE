package inf.saveanimals.domain.posts.common;

import lombok.Getter;

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
