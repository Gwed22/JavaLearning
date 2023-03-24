package quizmangement;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class QuestionException extends Exception {

    /**
     * Creates new QuestionException
     *
     * @param message
     */
    public QuestionException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "QuestionException: " + super.getMessage();
    }

}
