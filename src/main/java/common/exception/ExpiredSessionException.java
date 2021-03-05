package common.exception;

/**
 * @author
 */
public class ExpiredSessionException extends AimsException {

    public ExpiredSessionException() {
        super("ERROR: Your session has expired. Please login again!");
    }
}
