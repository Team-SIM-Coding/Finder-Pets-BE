package inf.saveanimals.request.posts;

import inf.saveanimals.domain.posts.common.IsMainImg;
import lombok.Data;

/**
 * 이미지 생성시 (썸네일 체크용)
 */
@Data
public class CreateImgRequest {
    private IsMainImg YN;


    public CreateImgRequest() {
        YN = IsMainImg.N;
    }


}