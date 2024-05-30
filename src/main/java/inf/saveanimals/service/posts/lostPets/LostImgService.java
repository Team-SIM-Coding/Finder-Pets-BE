package inf.saveanimals.service.posts.lostPets;

import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.lost.LostImg;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.exception.ImageNotFound;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.repository.posts.lost.LostImgRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
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
public class LostImgService {
    private final FileService fileService;
    private final LostImgRepository imgRepository;
    private final LostPetsRepository postRepository;

    // 이미지 업로드
    public LostImg saveImg(CreateImgRequest imgRequest, MultipartFile multipartFile) throws IOException {
        String imgUrl = fileService.storeFile(multipartFile);

        LostImg imgEntity = LostImg.builder()
                .imgUrl(imgUrl)
                .isMainImg(imgRequest.getYN())
                .build();

        return imgRepository.save(imgEntity);
    }

    // 이미지 삭제
    public void deleteImg(Long postId, Long imgId) {
        LostImg postImage = imgRepository.findById(imgId)
                .orElseThrow(ImageNotFound::new);

        LostPets postEntity = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        postEntity.removeImg(postImage);
        imgRepository.delete(postImage); // 연관관계 관리
    }

    // 추가로 이미지를 넣고 싶을 때?
    public void addExtraImage(Long postId, List<MultipartFile> fileList) throws IOException {
        LostPets postEntity = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        for(int i=0; i<fileList.size();i++){
            CreateImgRequest imgRequest = new CreateImgRequest();

            imgRequest.setYN(IsMainImg.N);

            // 리스트 형태로 이미지들 저장
            LostImg postImage = saveImg(imgRequest, fileList.get(i));
            postEntity.uploadImg(postImage);
        }
    }

}