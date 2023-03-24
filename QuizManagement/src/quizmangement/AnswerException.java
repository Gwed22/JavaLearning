package quizmangement;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class AnswerException extends Exception {

    /**
     * Creates new AnswerException
     *
     * @param message
     */
    public AnswerException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "AnswerException: " + super.getMessage();
    }

}
