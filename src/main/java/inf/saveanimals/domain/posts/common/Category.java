package inf.saveanimals.domain.posts.common;

import lombok.Getter;

/**
 * 포스팅 카테고리 데이터
 */
@Getter
public enum Category {

    MISSING("실종된 동물을 신고하는 글", "MISSING_POST"),
    SIGHTED("실종 동물을 목격했다는 글", "SIGHTED_POST"),
    SHELTER("보호소에 등록된 글", "SHELTER_POST");

    private String info;
    private String categoryCode;

    Category(String info, String categoryCode) {
        this.info = info;
        this.categoryCode = categoryCode;
    }

}
