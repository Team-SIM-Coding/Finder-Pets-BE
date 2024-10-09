package inf.saveanimals.utils.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class ResponseConstants {
    public static final ResponseEntity<String> DUPLICATION_EMAIL = new ResponseEntity<>("이미 가입된 이메일이 존재합니다.", HttpStatus.CONFLICT);

}
