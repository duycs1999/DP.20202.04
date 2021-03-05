package common.exception;

public class InternalServerException extends PaymentException {
    public InternalServerException(String message) {
        super(message);
    }
}
