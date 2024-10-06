package inf.saveanimals.exception;

/**
 * status -> 404 오류
 */
public class InvalidTokenException extends InfTeamException {

    private static final String MESSAGE = "유효하지 않는 토큰입니다.";

    public InvalidTokenException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
