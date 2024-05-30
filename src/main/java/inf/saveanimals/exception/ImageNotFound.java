package inf.saveanimals.exception;

/**
 * status -> 404
 */
public class ImageNotFound extends InfTeamException {

    private static final String MESSAGE = "존재하지 않는 이미지입니다.";

    public ImageNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
