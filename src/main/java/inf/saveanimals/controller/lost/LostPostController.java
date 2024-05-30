package inf.saveanimals.controller.lost;

import inf.saveanimals.request.posts.SearchCondition;
import inf.saveanimals.request.posts.lost.LostPetsCreate;
import inf.saveanimals.request.posts.lost.LostPetsEdit;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import inf.saveanimals.service.posts.lostPets.LostImgService;
import inf.saveanimals.service.posts.lostPets.LostPetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LostPostController {

    private final LostPetsService postService;
    private final LostImgService imgService;

    // 등록
    @PostMapping("/{userId}")
    public void write(@PathVariable("userId") Long userId,
                      @Validated @RequestPart(name = "postCreate") LostPetsCreate postCreate,
                      @RequestParam(name = "multipartFiles") List<MultipartFile> multipartFiles) throws IOException {
        postService.write(userId, postCreate, multipartFiles);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long postId) {
        postService.deletePost(postId);
    }

    // 수정
    @PatchMapping("/{id}")
    public void editPost(@PathVariable("id") Long postId, @RequestParam("postEdit") LostPetsEdit postEdit) {
        postService.edit(postId, postEdit);
    }

    // 이미지 삭제
    @DeleteMapping("/post/{postId}/{imgId}")
    public void deleteImg(@PathVariable("postId") Long postId,
                          @PathVariable("imgId") Long postImgId) {
        imgService.deleteImg(postId, postImgId);
    }

    // 실종된 반려동물을 찾아, 완료상태로 변환
    @PatchMapping("/{postId}")
    public void finalizeCase(@PathVariable("postId") Long postId) {
        postService.finalizeCase(postId);
    }


    // 계정- 등록한 포스팅 페이징 조회
    @GetMapping("/{userId}")
    public Page<LostPetsThumbnailResponse> searchByAccount(@PathVariable("userId") Long userId, Pageable pageable) {
        return postService.findByAccount(userId, pageable);
    }

    // 검색 페이징
    @GetMapping
    public Page<LostPetsThumbnailResponse> search(SearchCondition condition, Pageable pageable) {
        return postService.findPosts(condition, pageable);
    }
}
