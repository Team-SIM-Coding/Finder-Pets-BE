package inf.saveanimals.service.posts.lostPets;

import inf.saveanimals.domain.posts.lost.LostLike;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.posts.sighted.SightedLike;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.LikesNotFound;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.repository.posts.lost.LostLikeRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.posts.sighted.SightedLikeRepository;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 실종 - 관심하트 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LostLikeService {

    private final LostLikeRepository likeRepository;
    private final LostPetsRepository lostPetsRepository;


    // 관심하트 누르기
    public boolean insert(Long postId, User user) {
       LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        // 이미 관심하트 되어 있으면, 에러 반환
        if (likeRepository.findByUserAndLostPets(user, lostPets).isPresent()) {
            lostPets.deleteLike();
            return false;
        }

        LostLike lostLike = LostLike.builder()
                .lostPets(lostPets)
                .user(user)
                .build();

        likeRepository.save(lostLike);
        lostPets.addLike();
        return true;
    }

    // 관심 하트 취소하기
    public void delete(Long postId, User user) {
        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        LostLike lostLike = likeRepository.findByUserAndLostPets(user, lostPets)
                .orElseThrow(LikesNotFound::new);

        likeRepository.delete(lostLike);
    }
}
