package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.posts.sighted.SightedImg;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.posts.PostNotFoundException;
import inf.saveanimals.exception.users.UserNotFoundException;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.CreateImgRequest;
import inf.saveanimals.controller.dto.SearchCondition;
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

import static inf.saveanimals.utils.constants.ImgConstants.THUMBNAIL_INDEX;

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
    public Long write(User user, SightedPetsCreate postDto, List<MultipartFile> multipartFiles) throws IOException {

        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 회원입니다."));

        SightedPets savedPost = sightedPetsRepository.save(postDto.toEntity(loginUser));

        // 이미지 업로드
        uploadImg(multipartFiles, savedPost);

        return savedPost.getId();
    }

    private void uploadImg(List<MultipartFile> fileList, SightedPets sightedPets) throws IOException {
        for (int i=0; i<fileList.size(); i++) {
            CreateImgRequest imgRequest = new CreateImgRequest();

            // 첫번째 이미지를 메인 이미지로 설정
            imgRequest.setYN(i == THUMBNAIL_INDEX ? IsMainImg.Y : IsMainImg.N);

            // 이미지를 저장하고 게시물에 업로드
            SightedImg sightedImg = imgService.saveImg(imgRequest, fileList.get(i));
            sightedPets.uploadImg(sightedImg);
        }
    }

    // 게시글 삭제
    public void deletePost(Long postId) {
        SightedPets sightedPost = sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        sightedPetsRepository.delete(sightedPost);
    }


    // 게시글 수정
    public void edit(Long postId, SightedPetsEdit postEdit, List<MultipartFile> multipartFiles) throws IOException {
        SightedPets sightedPets = sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 게시물 정보 업데이트
        sightedPets.update(postEdit);

        // 이미지 파일이 존재한다면, 이미지를 새로 추가
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            uploadImg_extra(multipartFiles, sightedPets);
        }
    }

    private void uploadImg_extra(List<MultipartFile> multipartFiles, SightedPets sightedPets) throws IOException {
        for (MultipartFile file : multipartFiles) {
            SightedImg sightedImg = imgService.addExtraImg(file);
            sightedPets.uploadImg(sightedImg);
        }
    }


    // 게시글 상태 - 실종된 반려동물을 찾음 (완료처리)
    public void finalizeCase(Long postId) {
        SightedPets sightedPost = sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        sightedPost.finalizeCase();
    }


    // 단건 조회
    @Transactional(readOnly = true)
    public SightedPetsDetailResponse getPostDetail(Long postId) {
        SightedPets sightedPets = findById(postId);
        sightedPets.viewCount();

        return SightedPetsDetailResponse.fromEntity(sightedPets);
    }


    /**
     * 페이징
     * - 검색조건
     */
    // 검색 필터 -> 페이징
    @Transactional(readOnly = true)
    public Page<SightedPetsThumbnailResponse> findPosts(SearchCondition condition, Pageable pageable) {
        return sightedPetsRepository.findAllBySearchCondition(condition, pageable);
    }


    @Transactional(readOnly = true)
    public SightedPets findById(Long postId) {
        return sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
    }

}
