package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.sighted.SightedLike;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.LikesNotFound;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.repository.posts.sighted.SightedLikeRepository;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SightedLikeService {

    private final SightedLikeRepository likeRepository;
    private final SightedPetsRepository sightedPetsRepository;

    // 관심하트 누르기
    public boolean insert(Long sightedPetsId, User user) {
        SightedPets sightedPets = sightedPetsRepository.findById(sightedPetsId)
                .orElseThrow(PostNotFound::new);

        // 이미 관심하트 되어 있으면, 에러 반환
        if (likeRepository.findByUserAndSightedPets(user, sightedPets).isPresent()) {
            sightedPets.deleteLike();
            return false;
        }

        SightedLike sightedLike = SightedLike.builder()
                .sightedPets(sightedPets)
                .user(user)
                .build();

        likeRepository.save(sightedLike);
        sightedPets.addLike();
        return true;
    }

    // 관심 하트 취소하기
    public void delete(Long sightedPetsId, User user) {
        SightedPets sightedPets = sightedPetsRepository.findById(sightedPetsId)
                .orElseThrow(PostNotFound::new);

        SightedLike sightedLike = likeRepository.findByUserAndSightedPets(user, sightedPets)
                .orElseThrow(LikesNotFound::new);

        likeRepository.delete(sightedLike);
    }
}
