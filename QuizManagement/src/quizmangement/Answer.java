package quizmangement;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class Answer {

    private int aID;            //Answer Id
    private String aContent;    //Answer content
    private boolean aSelected;  //Answer is correct or not
    private boolean aStatus;    //Answer is selected or not 
    private int qId;            //Question id that answer belongs to

    /**
     * Gets the id of answer
     *
     * @return
     */
    public int getAID() {
        return aID;
    }

    /**
     * Sets id for answer
     *
     * @param aID answer id must be a positive integer
     * @throws AnswerException
     */
    public void setAID(int aID) throws AnswerException {
        if (aID <= 0) {
            throw new AnswerException("Answer ID must be a positive integer");
        } else {
            this.aID = aID;
        }
    }

    /**
     * Gets the content of answer
     *
     * @return
     */
    public String getAContent() {
        return aContent;
    }

    /**
     * Sets content for answer
     *
     * @param aContent
     * @throws quizmanagement.AnswerException
     */
    public void setAContent(String aContent) throws AnswerException {
        if (aContent.equals("")) {
            this.aContent = aContent;
        }
    }

    /**
     * Check the answer is selected or not
     *
     * @return
     */
    public boolean isASelected() {
        return aSelected;
    }

    /**
     * Selects or un-selects answer
     *
     * @param aSelected
     */
    public void setASelected(boolean aSelected) {
        this.aSelected = aSelected;
    }

    /**
     * Check the answer is true or not
     *
     * @return
     */
    public boolean isAStatus() {
        return aStatus;
    }

    /**
     * Sets status for answer
     *
     * @param aStatus
     */
    public void setAStatus(boolean aStatus) {
        this.aStatus = aStatus;
    }

    /**
     * Gets question id that answer is belongs to
     *
     * @return
     */
    public int getQId() {
        return qId;
    }

    /**
     * Sets id for question
     *
     * @param qId question id must be a positive integer
     * @throws quizmanagement.AnswerException
     */
    public void setQId(int qId) throws AnswerException {
        if (qId <= 0) {
            throw new AnswerException("Question ID must be a positive integer");
        } else {
            this.qId = qId;
        }
    }

    /**
     * Creates new answer
     *
     * @param aID
     * @param aContent
     * @param aSelected
     * @param aStatus
     * @param qId
     * @throws AnswerException
     */
    public Answer(int aID, String aContent, boolean aSelected, boolean aStatus, int qId) throws AnswerException {
        this.setAID(aID);
        this.setAContent(aContent);
        this.setASelected(aSelected);
        this.setAStatus(aStatus);
        this.setQId(qId);
    }

    /**
     * Check if the user answer is correct or not
     *
     * @return
     */
    public boolean isCorrect() {
        return this.aStatus == this.aSelected;
    }

    @Override
    public String toString() {
        return this.aContent + "\n";
    }

}
