package inf.saveanimals.request.posts;

import inf.saveanimals.domain.posts.common.IsMainImg;
import lombok.Data;

@Data
public class CreateImgRequest {
    private IsMainImg YN;


    public CreateImgRequest() {
        YN = IsMainImg.N;
    }


}