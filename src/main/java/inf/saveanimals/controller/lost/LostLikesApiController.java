package inf.saveanimals.controller.lost;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.service.UserService;
import inf.saveanimals.service.posts.lostPets.LostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lost")
@RequiredArgsConstructor
public class LostLikesApiController {

    private final UserService userService;
    private final LostLikeService likeService;

    @PostMapping("/{lostPetsId}/like/{userId}")
    public ResponseEntity<String> like(@PathVariable("lostPetsId") Long sightedPetsId, @PathVariable("userId") Long userId) {
        User user = userService.findById(userId);

        boolean liked = likeService.insert(sightedPetsId, user);
        // 관심하트를 토글하는 로직 구현
        if (liked) {
            return ResponseEntity.ok("liked");
        } else {
            return ResponseEntity.ok("unliked");
        }
    }
}
