package comicbook;

/**
 *
 * @author Group 4 - SE1607
 */
public class ComicBookException extends Exception {

    /**
     * Creates new AnswerException
     *
     * @param message
     */
    public ComicBookException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "ComicBookException: " + super.getMessage();
    }
}
