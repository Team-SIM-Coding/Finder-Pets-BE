package inf.saveanimals.controller;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.service.UserService;
import inf.saveanimals.service.posts.sightedPets.SightedLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sighted")
@RequiredArgsConstructor
public class SightedLikesApiController {

    private final UserService userService;
    private final SightedLikeService likeService;

    @PostMapping("/{sightedPetsId}/like/{userId}")
    public ResponseEntity<String> like(@PathVariable("sightedPetsId") Long sightedPetsId, @PathVariable("userId") Long userId) {
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
