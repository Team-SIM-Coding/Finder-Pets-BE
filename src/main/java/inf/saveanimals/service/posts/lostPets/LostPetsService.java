package inf.saveanimals.service.posts.lostPets;

import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.lost.LostImg;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.posts.PostNotFoundException;
import inf.saveanimals.exception.users.UserNotFoundException;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.controller.dto.SearchCondition;
import inf.saveanimals.request.posts.CreateImgRequest;
import inf.saveanimals.request.posts.lost.LostPetsCreate;
import inf.saveanimals.request.posts.lost.LostPetsEdit;
import inf.saveanimals.response.posts.lostPets.LostPetsDetailResponse;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

import static inf.saveanimals.utils.constants.ImgConstants.THUMBNAIL_INDEX;

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

    /**
     * 게시글 작성
     */
    public Long write(User user, LostPetsCreate postDto, List<MultipartFile> multipartFiles) throws IOException {

        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 회원입니다."));

        log.info("login user info ={}", loginUser.getEmail());

        LostPets savedPost = lostPetsRepository.save(postDto.toEntity(loginUser));

        // 이미지 업로드
        uploadImg(multipartFiles, savedPost);

        return savedPost.getId();
    }

    private void uploadImg(List<MultipartFile> fileList, LostPets lostPets) throws IOException {
        for (int i=0; i<fileList.size(); i++) {
            CreateImgRequest imgRequest = new CreateImgRequest();

            // 첫번쨰 이미지를 메인 이미지로 설정
            imgRequest.setYN(i == THUMBNAIL_INDEX ? IsMainImg.Y : IsMainImg.N);

            // 이미지를 저장하고 게시물에 업로드
            LostImg lostImg = imgService.saveImg(imgRequest, fileList.get(i));
            lostPets.uploadImg(lostImg);
        }
    }


    /**
     * 게시글 삭제
     */
    public void deletePost(Long postId) {
        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        lostPetsRepository.delete(lostPets);
    }


    /**
     * 게시글 수정
     */
    public void edit(Long postId, LostPetsEdit postEdit, List<MultipartFile> multipartFiles) throws IOException {
        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 게시글 정보 업데이트
        lostPets.update(postEdit);

        // 이미지 파일이 존재한다면, 이미지를 새로 추가
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            uploadImg_extra(multipartFiles, lostPets);
        }
    }

    private void uploadImg_extra(List<MultipartFile> multipartFiles, LostPets lostPets) throws IOException {
        for (MultipartFile file : multipartFiles) {
            LostImg lostImg = imgService.addExtraImg(file);
            lostPets.uploadImg(lostImg);
        }
    }

    /**
     * 게시글 상태 - 실종된 반려동물을 찾음 (완료처리)
     */
    public void finalizeCase(Long postId) {
        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        lostPets.finalizeCase();
    }

    /**
     * 단건 조회
     */
    @Transactional(readOnly = true)
    public LostPetsDetailResponse getPostDetail(Long postId) {
        // 조회수
        LostPets lostPet = findById(postId);
        lostPet.viewCount();

        return LostPetsDetailResponse.fromEntity(lostPet);
    }


    /**
     * 페이징
     * - 검색조건
     */
    // 검색 필터 -> 페이징
    @Transactional(readOnly = true)
    public Page<LostPetsThumbnailResponse> findPosts(SearchCondition condition, Pageable pageable) {
        return lostPetsRepository.findAllBySearchCondition(condition, pageable);
    }



    /**
     *  조회
     */
    // 테스트용
    @Transactional(readOnly = true)
    public LostPets findById(Long postId) {
        return lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
    }


}
