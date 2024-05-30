package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.sighted.SightedImg;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.exception.UserNotFound;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.CreateImgRequest;
import inf.saveanimals.request.posts.sighted.SightedPetsCreate;
import inf.saveanimals.request.posts.sighted.SightedPetsEdit;
import inf.saveanimals.response.posts.sightedPets.SightedPetsDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SightedPostService {

    private final SightedPetsRepository sightedPetsRepository;
    private final UserRepository userRepository;
    private final SightedImgService imgService; // 이미지 converter 로직


    // 게시글 작성
    public Long write(Long userId, SightedPetsCreate postDto, List<MultipartFile> fileList) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        SightedPets savedPost = sightedPetsRepository.save(postDto.toEntity(user));

        // 이미지 추가
        //이미지 등록
        for(int i=0; i<fileList.size();i++){
            CreateImgRequest imgRequest = new CreateImgRequest();

            if(i == 0)
                imgRequest.setYN(IsMainImg.Y);
            else
                imgRequest.setYN(IsMainImg.N);

            // 리스트 형태로 이미지들 저장
            SightedImg postImage = imgService.saveImg(imgRequest, fileList.get(i));
            savedPost.uploadImg(postImage);

        }
        return savedPost.getId();
    }

    // 게시글 삭제
    public void deletePost(Long postId) {
        SightedPets sightedPost = sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        sightedPetsRepository.delete(sightedPost);
        //  이미지 삭제
    }


    // 게시글 수정
    public void edit(Long postId, SightedPetsEdit postEdit) {
        SightedPets sightedPost = sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        sightedPost.update(postEdit);
    }

    // 게시글 상태 - 실종된 반려동물을 찾음 (완료처리)
    public void finalizeCase(Long postId) {
        SightedPets sightedPost = sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        sightedPost.finalizeCase();
    }



    /**
     *  조회
     */
    // 테스트용
    @Transactional(readOnly = true)
    public SightedPets findById(Long postId) {
        return sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public SightedPetsDetailResponse getPostDetail(Long postId) {
        return sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new)
                .toSightedPetsDetailResponse();
    }


    /**
     * 페이징
     * - 검색조건
     * - 계정
     */



}
