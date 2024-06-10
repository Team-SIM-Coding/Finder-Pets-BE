package inf.saveanimals.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 오류 예외 메시지 정의하기
 */
@Getter
public abstract class InfTeamException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public InfTeamException(String message) {
        super(message);
    }

    public InfTeamException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}