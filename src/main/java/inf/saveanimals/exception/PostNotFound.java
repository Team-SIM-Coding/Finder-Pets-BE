package inf.saveanimals.exception;

/**
 * status -> 404 오류
 */
public class PostNotFound extends InfTeamException {

    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
