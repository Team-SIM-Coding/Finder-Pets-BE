package inf.saveanimals.service.posts.lostPets;

import inf.saveanimals.domain.posts.lost.LostLike;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.posts.LikeAlreadyExistsException;
import inf.saveanimals.exception.posts.LikesNotFoundException;
import inf.saveanimals.exception.posts.PostNotFoundException;
import inf.saveanimals.exception.users.UserNotFoundException;
import inf.saveanimals.repository.posts.lost.LostLikeRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
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
    private final UserRepository userRepository;

    /**
     * 관심하트 누르기
     */
    public Integer insert(Long postId, User user) {
        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 회원입니다."));

        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 이미 관심하트 되어 있으면, 취소 처리
        if (likeRepository.existsByUserAndLostPets(loginUser, lostPets)) {
            //lostPets.deleteLike();
            throw new LikeAlreadyExistsException();

        }

        saveLikeEntity(lostPets, loginUser); // 관심 엔티티 생성
        lostPets.addLike(); // 연관 관계 -> 게시물에 대한 관심수 카운팅

        return lostPets.getTotalLike();
    }

    private void saveLikeEntity(LostPets lostPets, User loginUser) {
        LostLike lostLike = LostLike.builder()
                .lostPets(lostPets)
                .user(loginUser)
                .build();

        likeRepository.save(lostLike);
    }

    /**
     *관심 하트 취소하기
     */
    public void delete(Long postId, User user) {
        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 회원입니다."));

        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        LostLike lostLike = likeRepository.findByUserAndLostPets(loginUser, lostPets)
                .orElseThrow(LikesNotFoundException::new);

        likeRepository.delete(lostLike); // 관심 엔티티 삭제
        lostPets.deleteLike(); // 연관 엔티티-좋아요 취소처리
    }
}
