package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.sighted.SightedImg;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.exception.posts.ImageNotFoundException;
import inf.saveanimals.exception.posts.PostNotFoundException;
import inf.saveanimals.repository.posts.sighted.SightedImgRepository;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.request.posts.CreateImgRequest;
import inf.saveanimals.service.posts.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 제보-이미지 서비스
 */
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

        return savePetImg(imgRequest.getYN(), imgUrl);
    }

    private SightedImg savePetImg(IsMainImg isMainImg, String imgUrl) {
        SightedImg imgEntity = SightedImg.builder()
                .imgUrl(imgUrl)
                .isMainImg(isMainImg)
                .build();

        return imgRepository.save(imgEntity);
    }

    // 이미지 삭제
    public void deleteImg(Long postId, String imgUrl) {
        SightedImg postImage = imgRepository.findByImgUrl(imgUrl)
                .orElseThrow(ImageNotFoundException::new);

        SightedPets postEntity = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 부모엔티티 컬렉션에서 해당 이미지 제거
        postEntity.removeImg(postImage);

        // 삭제하려는 이미지가 '대표 이미지'인 경우 새로운 대표 이미지를 설정해야 합니다.
        if (postImage.getIsMainImg() == IsMainImg.Y) {
            postEntity.updateMainImg();
        }

        imgRepository.delete(postImage);
    }


    // 게시물 수정 - 추가 이미지 업로드
    public SightedImg addExtraImg(MultipartFile multipartFile) throws IOException {
        String imgUrl = fileService.storeFile(multipartFile);

        return savePetImg(IsMainImg.N, imgUrl);
    }

}
