package inf.saveanimals.exception;

/**
 * status -> 404 오류
 */
public class LikesNotFound extends InfTeamException {

    private static final String MESSAGE = "관심 하트가 없습니다.";

    public LikesNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
