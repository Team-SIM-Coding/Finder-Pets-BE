package inf.saveanimals.controller.sighted;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.controller.dto.SearchCondition;
import inf.saveanimals.request.posts.sighted.SightedPetsCreate;
import inf.saveanimals.request.posts.sighted.SightedPetsEdit;
import inf.saveanimals.response.posts.sightedPets.SightedPetsDetailResponse;
import inf.saveanimals.response.posts.sightedPets.SightedPetsThumbnailResponse;
import inf.saveanimals.service.posts.sightedPets.SightedImgService;
import inf.saveanimals.service.posts.sightedPets.SightedPetsService;
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
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class SightedPostController {

    private final SightedPetsService postService;
    private final SightedImgService imgService;

    // 등록
    @PostMapping("/posts/sighted")
    public void write(@AuthenticationPrincipal User user,
                      @Validated @RequestPart(name = "postCreate") SightedPetsCreate postCreate,
                      @RequestParam(name = "files") List<MultipartFile> multipartFiles) throws IOException {
        postService.write(user, postCreate, multipartFiles);
    }


    // 삭제
    @DeleteMapping("/posts/sighted/{pet_id}")
    public void delete(@PathVariable("pet_id") Long postId, @AuthenticationPrincipal User user) {
        postService.deletePost(postId);
    }

    // 수정
    @PatchMapping("/posts/sighted/{pet_id}")
    public void editPost(@PathVariable("pet_id") Long postId, @RequestPart("postEdit") SightedPetsEdit postEdit, @AuthenticationPrincipal User user) {
        postService.edit(postId, postEdit);
    }

    // 이미지 삭제
    @DeleteMapping("/posts/sighted/{pet_id}/{imgId}")
    public void deleteImg(@PathVariable("pet_id") Long postId,
                          @PathVariable("imgId") Long postImgId, @AuthenticationPrincipal User user) {
        imgService.deleteImg(postId, postImgId);
    }

    // 실종된 반려동물을 찾아, 완료상태로 변환
    @PatchMapping("/posts/sighted/finalizeCase/{pet_id}")
    public void finalizeCase(@PathVariable("pet_id") Long postId, @AuthenticationPrincipal User user) {
        postService.finalizeCase(postId);
    }



    // 검색 페이징
    @GetMapping("/posts/sighted/search")
    public Page<SightedPetsThumbnailResponse> search(SearchCondition condition, Pageable pageable) {
        return postService.findPosts(condition, pageable);
    }

    @GetMapping("/posts/sighted/view/{pet_id}")
    public SightedPetsDetailResponse findById(@PathVariable("pet_id") Long postId) {
        return postService.getPostDetail(postId);
    }
}
