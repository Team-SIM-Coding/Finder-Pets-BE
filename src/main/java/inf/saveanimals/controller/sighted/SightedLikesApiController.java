package inf.saveanimals.controller.sighted;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.response.posts.LikesResponse;
import inf.saveanimals.service.UserService;
import inf.saveanimals.service.posts.sightedPets.SightedLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SightedLikesApiController {

    private final SightedLikeService likeService;

    @PostMapping("/posts/sighted/{sightedPetsId}/like")
    public LikesResponse like(@PathVariable("sightedPetsId") Long sightedPetsId, @AuthenticationPrincipal User user) {
        Integer totalLikes = likeService.insert(sightedPetsId, user);

        return LikesResponse.of("관심하트 누르기 성공", totalLikes);
    }

    @DeleteMapping("/posts/sighted/{sightedPetsId}/like")
    public void deleteLikes(@PathVariable("sightedPetsId") Long sightedPetsId, @AuthenticationPrincipal User user) {
        likeService.delete(sightedPetsId, user);
    }

}
