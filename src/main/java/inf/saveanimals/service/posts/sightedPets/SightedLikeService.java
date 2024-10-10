package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.sighted.SightedLike;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.posts.LikesNotFoundException;
import inf.saveanimals.exception.posts.PostNotFoundException;
import inf.saveanimals.exception.users.UserNotFoundException;
import inf.saveanimals.repository.posts.sighted.SightedLikeRepository;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 제보-관심하트 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SightedLikeService {

    private final SightedLikeRepository likeRepository;
    private final SightedPetsRepository sightedPetsRepository;
    private final UserRepository userRepository;

    // 관심하트 누르기
    public Integer insert(Long sightedPetsId, User user) {
        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        SightedPets sightedPets = sightedPetsRepository.findById(sightedPetsId)
                .orElseThrow(PostNotFoundException::new);

        // 이미 관심하트 되어 있으면, 하트 취소된다.
        if (likeRepository.findByUserAndSightedPets(loginUser, sightedPets).isPresent()) {
            sightedPets.deleteLike();
        }

        SightedLike sightedLike = SightedLike.builder()
                .sightedPets(sightedPets)
                .user(loginUser)
                .build();

        likeRepository.save(sightedLike);
        sightedPets.addLike();

        return sightedPets.getTotalLike();
    }

    // 관심 하트 취소하기
    public void delete(Long sightedPetsId, User user) {
        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        SightedPets sightedPets = sightedPetsRepository.findById(sightedPetsId)
                .orElseThrow(PostNotFoundException::new);

        SightedLike sightedLike = likeRepository.findByUserAndSightedPets(loginUser, sightedPets)
                .orElseThrow(LikesNotFoundException::new);

        likeRepository.delete(sightedLike);
        sightedPets.deleteLike();
    }
}
