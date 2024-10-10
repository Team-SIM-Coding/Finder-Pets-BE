package inf.saveanimals.service.posts.lostPets;

import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.posts.CommentNotFoundException;
import inf.saveanimals.exception.posts.PostNotFoundException;
import inf.saveanimals.exception.users.UserNotFoundException;
import inf.saveanimals.repository.posts.lost.LostCommentsRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
import inf.saveanimals.response.posts.lostPets.LostPetCommentDto;
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
        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        LostComments comment = commentsRepository.save(create.toEntity(loginUser));
        comment.assignToUser(loginUser);

        lostPets.addComment(comment);
    }

    // 댓글 삭제
    public void delete(Long commentId) {
        LostComments comment = commentsRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        commentsRepository.delete(comment);
    }


    @Transactional(readOnly = true)
    public List<LostPetCommentDto> getCommentsByLostPetsId(Long lostPetsId) {
      return lostPetsRepository.findCommentByPetId(lostPetsId);
    }

    // 댓글 수정
    public void updateComment(Long commentId, String message) {
        LostComments comment = commentsRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        comment.update(message);
    }
}
