package inf.saveanimals.service.posts.lostPets;

import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.CommentNotFound;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.exception.ResourceNotFoundException;
import inf.saveanimals.repository.posts.lost.LostCommentsRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 실종- 댓글 서비스
 */

@Service
@RequiredArgsConstructor
@Transactional
public class LostCommentsService {

    private final LostPetsRepository lostPetsRepository;
    private final LostCommentsRepository commentsRepository;
    private final UserRepository userRepository;

    // 댓글 달기
    public void write(Long postId, LostCommentCreate create, User user) {
        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));


        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        LostComments comment = commentsRepository.save(create.toEntity(loginUser));

        lostPets.addComment(comment);
    }

    // 댓글 삭제
    public void delete(Long commentId) {
        LostComments comment = commentsRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);

        commentsRepository.delete(comment);
    }


    @Transactional(readOnly = true)
    public List<LostComments> getCommentsByLostPetsId(Long lostPetsId) {
        return commentsRepository.findByLostPetsId(lostPetsId);
    }
}
