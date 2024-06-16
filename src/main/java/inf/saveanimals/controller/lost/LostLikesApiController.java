package inf.saveanimals.controller.lost;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.response.posts.LikesResponse;
import inf.saveanimals.service.UserService;
import inf.saveanimals.service.posts.lostPets.LostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LostLikesApiController {

    private final LostLikeService likeService;

    @PostMapping("/posts/lost/{lostPetsId}/like")
    public LikesResponse like(@PathVariable("lostPetsId") Long sightedPetsId, @AuthenticationPrincipal User user) {

        Integer totalLikes = likeService.insert(sightedPetsId, user);

        return LikesResponse.of("좋아요 누르기 성공", totalLikes);
    }

    @DeleteMapping("/posts/lost/{lostPetsId}/like")
    public void deleteLike(@PathVariable("lostPetsId") Long sightedPetsId, @AuthenticationPrincipal User user) {
        likeService.delete(sightedPetsId, user);
    }
}
