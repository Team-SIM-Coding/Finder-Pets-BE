package inf.saveanimals.controller.lost;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
import inf.saveanimals.service.posts.lostPets.LostCommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LostCommentsController {

    private final LostCommentsService commentsService;

    @PostMapping("/lostPets/{postId}/comments")
    public void write(@PathVariable("postId") Long postId, @RequestBody LostCommentCreate request, @AuthenticationPrincipal User user) {
        commentsService.write(postId, request, user);
    }

    @PostMapping("/lostPets/comments/{commentId}/delete")
    public void delete(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal User user) {
        commentsService.delete(commentId);
    }

}
