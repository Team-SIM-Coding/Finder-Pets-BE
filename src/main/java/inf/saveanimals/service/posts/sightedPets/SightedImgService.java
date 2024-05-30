package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.sighted.SightedImg;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.exception.ImageNotFound;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.repository.posts.sighted.SightedImgRepository;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.request.posts.CreateImgRequest;
import inf.saveanimals.service.posts.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class SightedImgService {

    private final FileService fileService;
    private final SightedImgRepository imgRepository;
    private final SightedPetsRepository postRepository;

    // 이미지 업로드
    public SightedImg saveImg(CreateImgRequest imgRequest, MultipartFile multipartFile) throws IOException {
        String imgUrl = fileService.storeFile(multipartFile);

        SightedImg imgEntity = SightedImg.builder()
                .imgUrl(imgUrl)
                .isMainImg(imgRequest.getYN())
                .build();

        return imgRepository.save(imgEntity);
    }

    // 이미지 삭제
    public void deleteImg(Long postId, Long imgId) {
        SightedImg postImage = imgRepository.findById(imgId)
                .orElseThrow(ImageNotFound::new);

        SightedPets postEntity = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        postEntity.removeImg(postImage);
        imgRepository.delete(postImage); // 연관관계 관리
    }

    // 추가로 이미지를 넣고 싶을 때?
    public void addExtraImage(Long postId, List<MultipartFile> fileList) throws IOException {
        SightedPets postEntity = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);


        for(int i=0; i<fileList.size();i++){
            CreateImgRequest imgRequest = new CreateImgRequest();

            imgRequest.setYN(IsMainImg.N);

            // 리스트 형태로 이미지들 저장
            SightedImg postImage = saveImg(imgRequest, fileList.get(i));
            postEntity.uploadImg(postImage);
        }
    }

}
