package inf.saveanimals.controller.sighted;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.request.posts.lost.LostCommentEdit;
import inf.saveanimals.request.posts.sighted.SightedCommentCreate;
import inf.saveanimals.response.posts.sightedPets.SightedPetCommentDto;
import inf.saveanimals.service.posts.sightedPets.SightedCommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class SightedCommentsController {

    private final SightedCommentsService commentsService;

    // 댓글 등록
    @PostMapping("/post/sighted/{pet_id}/comment/register")
    public void write(@PathVariable("pet_id") Long postId, @RequestBody SightedCommentCreate request, @AuthenticationPrincipal User user) {
        commentsService.write(postId, request, user);
    }

    // 삭제
    @DeleteMapping("/post/sighted/comment/delete/{comment_id}")
    public void delete(@PathVariable("comment_id") Long commentId, @AuthenticationPrincipal User user) {
        commentsService.delete(commentId);
    }

    // 수정
    @PatchMapping("/post/sighted/comment/update/{comment_id}")
    public void updateComment(@PathVariable("comment_id") Long commentId, @RequestBody LostCommentEdit request, @AuthenticationPrincipal User user) {
        commentsService.updateComment(commentId, request.getComment());
    }

    // 조회
    @GetMapping("/post/sighted/comment/list/{pet_id}")
    List<SightedPetCommentDto> getCommentList(@PathVariable("pet_id") Long petId) {
        return commentsService.getCommentsByLostPetsId(petId);
    }
}
