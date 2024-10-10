package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.sighted.SightedComments;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.posts.CommentNotFoundException;
import inf.saveanimals.exception.posts.PostNotFoundException;
import inf.saveanimals.exception.users.UserNotFoundException;
import inf.saveanimals.repository.posts.sighted.SightedCommentsRepository;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.sighted.SightedCommentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 제보-댓글 서비스
 */

@Service
@RequiredArgsConstructor
@Transactional
public class SightedCommentsService {

    private final SightedPetsRepository sightedPetsRepository;
    private final SightedCommentsRepository commentsRepository;
    private final UserRepository userRepository;

    // 댓글 달기
    public void write(Long postId, SightedCommentCreate create, User user) {
        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        SightedPets sightedPets = sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        SightedComments comment = commentsRepository.save(create.toEntity(loginUser));

        sightedPets.addComment(comment);
    }

    // 댓글 삭제
    public void delete(Long commentId) {
        SightedComments comment = commentsRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        commentsRepository.delete(comment);
    }
}
