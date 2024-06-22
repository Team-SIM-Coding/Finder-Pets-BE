package inf.saveanimals.controller.lost;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
import inf.saveanimals.service.posts.lostPets.LostCommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class LostCommentsController {

    private final LostCommentsService commentsService;

    @PostMapping("/lostPets/{pet_id}/comments")
    public void write(@PathVariable("pet_id") Long postId, @RequestBody LostCommentCreate request, @AuthenticationPrincipal User user) {
        commentsService.write(postId, request, user);
    }

    @DeleteMapping("/lostPets/comments/{comment_id}")
    public void delete(@PathVariable("comment_id") Long commentId, @AuthenticationPrincipal User user) {
        commentsService.delete(commentId);
    }

}
