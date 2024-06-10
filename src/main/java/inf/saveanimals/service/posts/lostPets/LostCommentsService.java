package inf.saveanimals.service.posts.lostPets;

import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.exception.CommentNotFound;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.repository.posts.lost.LostCommentsRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 실종- 댓글 서비스
 */

@Service
@RequiredArgsConstructor
@Transactional
public class LostCommentsService {

    private final LostPetsRepository lostPetsRepository;
    private final LostCommentsRepository commentsRepository;

    // 댓글 달기
    public void write(Long postId, LostCommentCreate create) {
        LostPets lostPets = lostPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        LostComments comment = commentsRepository.save(create.toEntity());

        lostPets.addComment(comment);
    }

    // 댓글 삭제
    public void delete(Long commentId) {
        LostComments comment = commentsRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);

        commentsRepository.delete(comment);
    }
}
