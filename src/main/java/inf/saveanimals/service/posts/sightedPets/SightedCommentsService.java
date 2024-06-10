package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.posts.sighted.SightedComments;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.exception.CommentNotFound;
import inf.saveanimals.exception.PostNotFound;
import inf.saveanimals.repository.posts.lost.LostCommentsRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.posts.sighted.SightedCommentsRepository;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
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

    // 댓글 달기
    public void write(Long postId, SightedCommentCreate create) {
        SightedPets sightedPets = sightedPetsRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        SightedComments comment = commentsRepository.save(create.toEntity());

        sightedPets.addComment(comment);
    }

    // 댓글 삭제
    public void delete(Long commentId) {
        SightedComments comment = commentsRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);

        commentsRepository.delete(comment);
    }
}
