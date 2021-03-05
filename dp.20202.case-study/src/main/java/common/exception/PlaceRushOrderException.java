package common.exception;

/**
 * The PlaceOrderException wraps all unchecked exceptions You can use this
 * exception to inform 
 * 
 * @author nguyenlm
 */
public class PlaceRushOrderException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public PlaceRushOrderException() {

	}

	public PlaceRushOrderException(String message) {
		super(message);
	}

}
