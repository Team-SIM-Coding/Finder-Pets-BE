package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.lost.LostLike;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.posts.sighted.SightedLike;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.posts.LikeAlreadyExistsException;
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

        // 이미 관심하트 되어 있으면, 취소 처리
        if (likeRepository.existsByUserAndSightedPets(loginUser, sightedPets)) {
            throw new LikeAlreadyExistsException();

        }

        saveLikeEntity(sightedPets, loginUser); // 관심 엔티티 생성
        sightedPets.addLike(); // 연관 관계 -> 게시물에 대한 관심수 카운팅

        return sightedPets.getTotalLike();
    }

    private void saveLikeEntity(SightedPets sightedPets, User loginUser) {
        SightedLike sightedLike = SightedLike.builder()
                .sightedPets(sightedPets)
                .user(loginUser)
                .build();

        likeRepository.save(sightedLike);
    }


    // 관심 하트 취소하기
    public void delete(Long sightedPetsId, User user) {
        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        SightedPets sightedPets = sightedPetsRepository.findById(sightedPetsId)
                .orElseThrow(PostNotFoundException::new);

        SightedLike sightedLike = likeRepository.findByUserAndSightedPets(loginUser, sightedPets)
                .orElseThrow(LikesNotFoundException::new);

        likeRepository.delete(sightedLike); // 관심 엔티티 삭제
        sightedPets.deleteLike(); // 연관 엔티티-좋아요 취소처리
    }
}
