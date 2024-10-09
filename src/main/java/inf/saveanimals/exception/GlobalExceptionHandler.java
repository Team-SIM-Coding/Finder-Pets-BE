package inf.saveanimals.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static inf.saveanimals.utils.constants.ResponseConstants.DUPLICATION_EMAIL;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public final ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException exception) {
        log.info("중복된 이메일입니다.", exception);
        return DUPLICATION_EMAIL;
    }

}
