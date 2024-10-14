package inf.saveanimals.exception;

import inf.saveanimals.exception.posts.*;
import inf.saveanimals.exception.users.DuplicateEmailException;
import inf.saveanimals.exception.users.DuplicateNameException;
import inf.saveanimals.exception.users.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static inf.saveanimals.utils.constants.ResponseConstants.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public final ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException exception) {
        log.info("중복된 이메일입니다.", exception);
        return DUPLICATION_EMAIL;
    }


    @ExceptionHandler(ImageNotFoundException.class)
    public final ResponseEntity<String> handleImageNotFoundException(ImageNotFoundException exception) {
        log.info("존재하지 않는 게시물 이미지입니다.", exception);
        return IMG_NOT_FOUND;
    }

    @ExceptionHandler(PostNotFoundException.class)
    public final ResponseEntity<String> handlePostNotFoundException(PostNotFoundException exception) {
        log.info("존재하지 않는 글입니다.", exception);
        return POST_NOT_FOUND;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<String> handleUserNotFoundException(
            UserNotFoundException exception) {
        log.debug("존재하지 않는 Email 또는 password 불일치", exception);
        return USER_NOT_FOUND;
    }

    @ExceptionHandler(LikesNotFoundException.class)
    public final ResponseEntity<String> handleLikesNotFoundException(LikesNotFoundException exception) {
        log.info("관심 하트가 없습니다.", exception);
        return LIKE_NOT_FOUND;
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public final ResponseEntity<String> handle(CommentNotFoundException exception) {
        log.info("존재하지 않는 댓글입니다.", exception);
        return COMMENT_NOT_FOUND;
    }

    @ExceptionHandler(LikeAlreadyExistsException.class)
    public final ResponseEntity<String> handleLikeAlreadyExistsException(LikeAlreadyExistsException exception) {
        log.info("이미 좋아요 버튼을 눌렀습니다.", exception);
        return LIKE_ALREADY_EXISTS_EXCEPTION;
    }

    // 입력 오류
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        if (ex.getMessage().contains("BreedGroup")) {
            String errorMessage = "동물 입력값이 잘못되었습니다. 허용 가능한 값은 [ETC, CAT, DOG]입니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        if (ex.getMessage().contains("Breed")) {
            String errorMessage = "품종 입력값이 잘못되었습니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력 값이 올바르지 않습니다.");
    }


}
