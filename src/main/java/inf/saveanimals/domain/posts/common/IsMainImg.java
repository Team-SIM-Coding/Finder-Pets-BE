package inf.saveanimals.domain.posts.common;

/**
 * 썸네일 메인 이미지 체크를 위한
 */
public enum IsMainImg {
    Y("Y", "대표이미지"),
    N("N", "세부 이미지");

    private String YN;
    private String kCode;

    IsMainImg(String YN, String kCode) {
        this.YN = YN;
        this.kCode = kCode;
    }


}
