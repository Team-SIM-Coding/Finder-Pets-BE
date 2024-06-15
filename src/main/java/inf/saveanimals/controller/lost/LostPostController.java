package inf.saveanimals.controller.lost;

import inf.saveanimals.domain.users.User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PostMapping("/posts/lost/create")
    public void write(@AuthenticationPrincipal User user,
                      @Validated @RequestPart(name = "postCreate") LostPetsCreate postCreate,
                      @RequestParam(name = "files") List<MultipartFile> multipartFiles) throws IOException {
        log.info("controller check= {}", user.getEmail());
        postService.write(user, postCreate, multipartFiles);
    }


    // 삭제
    @DeleteMapping("/posts/lost/{postId}")
    public void delete(@PathVariable("postId") Long postId, @AuthenticationPrincipal User user) {
        postService.deletePost(postId);
    }

    // 수정
    @PatchMapping("/posts/lost/{postId}")
    public void editPost(@PathVariable("postId") Long postId, @RequestParam("postEdit") LostPetsEdit postEdit, @AuthenticationPrincipal User user) {
        postService.edit(postId, postEdit);
    }

    // 이미지 삭제
    @DeleteMapping("/posts/lost/{postId}/{imgId}")
    public void deleteImg(@PathVariable("postId") Long postId,
                          @PathVariable("imgId") Long postImgId, @AuthenticationPrincipal User user) {
        imgService.deleteImg(postId, postImgId);
    }

    // 실종된 반려동물을 찾아, 완료상태로 변환
    @PatchMapping("/posts/lost/finalizeCase/{postId}")
    public void finalizeCase(@PathVariable("postId") Long postId, @AuthenticationPrincipal User user) {
        postService.finalizeCase(postId);
    }


    // 계정- 등록한 포스팅 페이징 조회
    @GetMapping("/posts/lost/myPost")
    public Page<LostPetsThumbnailResponse> searchByAccount(@AuthenticationPrincipal User user, Pageable pageable) {
        return postService.findByAccount(user, pageable);
    }

    // 검색 페이징
    @GetMapping("/posts/lost/search")
    public Page<LostPetsThumbnailResponse> search(SearchCondition condition, Pageable pageable) {
        return postService.findPosts(condition, pageable);
    }
}
