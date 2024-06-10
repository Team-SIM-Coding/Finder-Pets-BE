package inf.saveanimals.service.posts.lostPets;

import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.lost.LostImg;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.SearchCondition;
import inf.saveanimals.request.posts.CreateImgRequest;
import inf.saveanimals.request.posts.lost.LostPetsCreate;
import inf.saveanimals.request.posts.lost.LostPetsEdit;
import inf.saveanimals.response.posts.lostPets.LostPetsDetailResponse;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import inf.saveanimals.exception.UserNotFound;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
 * 실종-본문 서비스
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LostPetsService {

    private final LostPetsRepository lostPetsRepository;
    private final UserRepository userRepository;
    private final LostImgService imgService;

    // 게시글 작성
    public Long write(Long userId, LostPetsCreate postDto, List<MultipartFile> fileList) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        LostPets savedPost = lostPetsRepository.save(postDto.toEntity(user));

        // 이미지 추가
        //이미지 등록
        for(int i=0; i<fileList.size();i++){
            CreateImgRequest imgRequest = new CreateImgRequest();

            if(i == 0)
                imgRequest.setYN(IsMainImg.Y);
            else
                imgRequest.setYN(IsMainImg.N);

            // 리스트 형태로 이미지들 저장
            LostImg postImage = imgService.saveImg(imgRequest, fileList.get(i));
            savedPost.uploadImg(postImage);
        }
        return savedPost.getId();
    }


    // 게시글 삭제
    public void deletePost(Long postId) {
        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        lostPetsRepository.delete(lostPets);
        //  이미지 삭제
    }


    // 게시글 수정
    public void edit(Long postId, LostPetsEdit postEdit) {
        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        lostPets.update(postEdit);
    }



    // 게시글 상태 - 실종된 반려동물을 찾음 (완료처리)
    public void finalizeCase(Long postId) {
        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        lostPets.finalizeCase();
    }

    /**
     *  조회
     */
    // 테스트용
    @Transactional(readOnly = true)
    public LostPets findById(Long postId) {
        return lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public LostPetsDetailResponse getPostDetail(Long postId) {
        // 조회수
        LostPets lostPet = findById(postId);
        lostPet.viewCount();

        return lostPet.toMissingPostDetailResponse();
    }


    /**
     * 페이징
     * - 검색조건
     * - 계정
     */

    // 검색 필터 -> 페이징
    @Transactional(readOnly = true)
    public Page<LostPetsThumbnailResponse> findPosts(SearchCondition condition, Pageable pageable) {
        return lostPetsRepository.findAllBySearchCondition(condition, pageable);
    }


    // 계정당 포스트 조회
    @Transactional(readOnly = true)
    public Page<LostPetsThumbnailResponse> findByAccount(Long userId, Pageable pageable) {
        return lostPetsRepository.findAllByAccount(userId, pageable);
    }


}
