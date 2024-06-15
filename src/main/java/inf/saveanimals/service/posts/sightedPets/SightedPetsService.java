package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.sighted.SightedImg;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.exception.ResourceNotFoundException;
import inf.saveanimals.exception.UserNotFound;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.CreateImgRequest;
import inf.saveanimals.request.posts.SearchCondition;
import inf.saveanimals.request.posts.sighted.SightedPetsCreate;
import inf.saveanimals.request.posts.sighted.SightedPetsEdit;

import inf.saveanimals.response.posts.sightedPets.SightedPetsDetailResponse;
import inf.saveanimals.response.posts.sightedPets.SightedPetsThumbnailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 제보-본문 서비스
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SightedPetsService {

    private final SightedPetsRepository sightedPetsRepository;
    private final UserRepository userRepository;
    private final SightedImgService imgService; // 이미지 converter 로직


    // 게시글 작성
    public Long write(User user, SightedPetsCreate postDto, List<MultipartFile> fileList) throws IOException {
        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        SightedPets savedPost = sightedPetsRepository.save(postDto.toEntity(loginUser));

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
    // 검색 필터 -> 페이징
    @Transactional(readOnly = true)
    public Page<SightedPetsThumbnailResponse> findPosts(SearchCondition condition, Pageable pageable) {
        return sightedPetsRepository.findAllBySearchCondition(condition, pageable);
    }


    // 계정당 포스트 조회
    @Transactional(readOnly = true)
    public Page<SightedPetsThumbnailResponse> findByAccount(User user, Pageable pageable) {
        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        return sightedPetsRepository.findAllByAccount(loginUser.getId(), pageable);
    }



}
