package inf.saveanimals.controller.sighted;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
import inf.saveanimals.request.posts.sighted.SightedCommentCreate;
import inf.saveanimals.service.posts.sightedPets.SightedCommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class SightedCommentsController {

    private final SightedCommentsService commentsService;

    @PostMapping("/sightedPets/{pet_id}/comments")
    public void write(@PathVariable("pet_id") Long postId, @RequestBody SightedCommentCreate request, @AuthenticationPrincipal User user) {
        commentsService.write(postId, request, user);
    }

    @DeleteMapping("/sightedPets/comments/{comment_id}")
    public void delete(@PathVariable("comment_id") Long commentId, @AuthenticationPrincipal User user) {
        commentsService.delete(commentId);
    }
}
