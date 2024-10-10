package inf.saveanimals.utils.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class ResponseConstants {
    public static final ResponseEntity<String> DUPLICATION_EMAIL = new ResponseEntity<>("이미 가입된 이메일이 존재합니다.", HttpStatus.CONFLICT);
    public static final ResponseEntity<String> USER_NOT_FOUND = new ResponseEntity<>("가입하지 않은 Email 이거나, 잘못된 password 입니다.", HttpStatus.NOT_FOUND);

    public static final ResponseEntity<String> IMG_NOT_FOUND = new ResponseEntity<>("존재하지 않는 게시물 이미지입니다.", HttpStatus.BAD_REQUEST);
    public static final ResponseEntity<String> POST_NOT_FOUND = new ResponseEntity<>("존재하지 않는 게시물입니다.", HttpStatus.BAD_REQUEST);
    public static final ResponseEntity<String> LIKE_NOT_FOUND = new ResponseEntity<>("좋아요를 누르지 않았습니다.", HttpStatus.BAD_REQUEST);
    public static final ResponseEntity<String> COMMENT_NOT_FOUND = new ResponseEntity<>("존재하지 않는 댓글입니다.", HttpStatus.BAD_REQUEST);
    public static final ResponseEntity<String> LIKE_ALREADY_EXISTS_EXCEPTION = new ResponseEntity<>("이미 좋아요 버튼을 눌렀습니다.", HttpStatus.BAD_REQUEST);

}
