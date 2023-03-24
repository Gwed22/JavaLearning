package fish;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class FishException extends Exception {

    /**
     * Creates new FishException
     *
     * @param message
     */
    public FishException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "FishException: " + super.getMessage();
    }
}
