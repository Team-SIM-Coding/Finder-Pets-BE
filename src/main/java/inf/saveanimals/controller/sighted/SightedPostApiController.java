package inf.saveanimals.controller.sighted;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.District;
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
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
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
public class SightedPostApiController {

    private final SightedPetsService postService;
    private final SightedImgService imgService;
    private final PagedResourcesAssembler<SightedPetsThumbnailResponse> pagedResourcesAssembler;

    // 등록
    @PostMapping("/post/sighted/register")
    public void write(@AuthenticationPrincipal User user,
                      @Validated @RequestPart(name = "postCreate") SightedPetsCreate postCreate,
                      @RequestParam(name = "files") List<MultipartFile> multipartFiles) throws IOException {
        postService.write(user, postCreate, multipartFiles);
    }


    // 삭제
    @DeleteMapping("/post/sighted/delete/{pet_id}")
    public void delete(@PathVariable("pet_id") Long postId, @AuthenticationPrincipal User user) {
        postService.deletePost(postId);
    }

    // 수정
    @PatchMapping(value = "/post/sighted/update/{pet_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void editPost(@AuthenticationPrincipal User user, @PathVariable("pet_id") Long postId,
                         @RequestPart(name = "postEdit", required = false) SightedPetsEdit postEdit,
                         @RequestParam(name = "files", required = false) List<MultipartFile> multipartFiles) throws IOException {
        postService.edit(postId, postEdit, multipartFiles);
    }

    // 이미지 삭제
    @DeleteMapping("/post/sighted/{pet_id}/img")
    public void deleteImg(@PathVariable("pet_id") Long postId, @RequestParam ("img") String imgUrl, @AuthenticationPrincipal User user) {
        imgService.deleteImg(postId, imgUrl);
    }

    // 실종된 반려동물을 찾아, 완료상태로 변환
    @PatchMapping("/post/sighted/{pet_id}/completed")
    public void finalizeCase(@PathVariable("pet_id") Long postId, @AuthenticationPrincipal User user) {
        postService.finalizeCase(postId);
    }



    // 검색 페이징
    @GetMapping("/post/sighted/all")
    public PagedModel<EntityModel<SightedPetsThumbnailResponse>> search(@AuthenticationPrincipal User user, Pageable pageable,
                                                                        @RequestParam(required = false, name= "animal") BreedGroup animal,
                                                                        @RequestParam(required = false, name= "kind") Breed kind,
                                                                        @RequestParam(required = false, name = "city") City city,
                                                                        @RequestParam(required = false, name = "district") District district) {

        SearchCondition condition = new SearchCondition(animal,kind, city, district);

        Page<SightedPetsThumbnailResponse> response = postService.findPosts(condition, pageable);

        return pagedResourcesAssembler.toModel(response);
    }

    // 단건 조회
    @GetMapping("/post/sighted/{pet_id}")
    public SightedPetsDetailResponse findById(@PathVariable("pet_id") Long postId) {
        return postService.getPostDetail(postId);
    }
}
