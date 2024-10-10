package inf.saveanimals.controller.lost;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.response.posts.LikesResponse;
import inf.saveanimals.service.posts.lostPets.LostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class LostLikesApiController {

    private final LostLikeService likeService;

    @PostMapping("/post/lost/{pet_id}/like")
    public void like(@PathVariable("pet_id") Long postId, @AuthenticationPrincipal User user) {
        likeService.insert(postId, user);
    }

    @DeleteMapping("/post/lost/{pet_id}/like")
    public void deleteLike(@PathVariable("pet_id") Long postId, @AuthenticationPrincipal User user) {
        likeService.delete(postId, user);
    }
}
