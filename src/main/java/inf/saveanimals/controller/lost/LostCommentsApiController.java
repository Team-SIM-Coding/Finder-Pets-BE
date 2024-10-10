package inf.saveanimals.controller.lost;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
import inf.saveanimals.request.posts.lost.LostCommentEdit;
import inf.saveanimals.response.posts.lostPets.LostPetCommentDto;
import inf.saveanimals.service.posts.lostPets.LostCommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class LostCommentsApiController {

    private final LostCommentsService commentsService;

    @PostMapping("/post/lost/{pet_id}/comment/register")
    public void write(@PathVariable("pet_id") Long postId, @RequestBody LostCommentCreate request, @AuthenticationPrincipal User user) {
        commentsService.write(postId, request, user);
    }

    @DeleteMapping("/post/lost/comment/delete/{comment_id}")
    public void delete(@PathVariable("comment_id") Long commentId, @AuthenticationPrincipal User user) {
        commentsService.delete(commentId);
    }

    @PatchMapping("/post/lost/comment/update/{comment_id}")
    public void updateComment(@PathVariable("comment_id") Long commentId, @RequestBody LostCommentEdit request, @AuthenticationPrincipal User user) {
        commentsService.updateComment(commentId, request.getComment());
    }

    @GetMapping("/posts/lost/comment/list/{pet_id}")
    List<LostPetCommentDto> getCommentList(@PathVariable("pet_id") Long petId) {
        return commentsService.getCommentsByLostPetsId(petId);
    }

}
