package inf.saveanimals.request.posts;

import inf.saveanimals.domain.posts.common.IsMainImg;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
