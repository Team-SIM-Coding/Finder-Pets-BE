package inf.saveanimals.request.posts;

import inf.saveanimals.domain.posts.common.IsMainImg;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 이미지 생성할 때
 * - 사용은 안함 (구현중 적은것)
 */
@Data
public class PetsImgCreate {

    private IsMainImg YN;
    private List<MultipartFile> multipartFileList;
    @Builder
    public PetsImgCreate(IsMainImg YN, List<MultipartFile> multipartFileList) {
        this.YN = YN;
        this.multipartFileList = multipartFileList;
    }
}
