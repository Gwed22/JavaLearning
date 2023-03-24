package quizmangement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The class AnswerManagemet is used to manage answer bank
 *
 * @author Do Huynh Anh vu CE171446
 */
public class AnswerManagement {

    private String A_FILE;
    private int numberOfAnswer;
    private ArrayList<Answer> answers;

    /**
     * Creates instance for answer management
     *
     * @param A_FILE
     * @throws quizmanagemtn.AnswerException
     */
    public AnswerManagement(String A_FILE) throws AnswerException {
        if (A_FILE.equals("")) {
            throw new AnswerException("The URL of answer data file can't be empty!");
        } else {
            this.A_FILE = A_FILE;
            this.answers = new ArrayList<Answer>();
            this.numberOfAnswer = 0;
        }
    }

    /**
     * Loads data of answer from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws AnswerException
     */
    public void loadAnswers() throws IOException, AnswerException {
        File aFile = new File(A_FILE);

        if (!aFile.exists()) {      //Checks is file creatd
            aFile.createNewFile();  //If not, ceates new file
            System.out.println("The data file answers.txt is not exits."
                    + "Creating new data file answers.txt"
                    + "Done");
            this.numberOfAnswer = 0;    //New data file with the number of answer is 0
        } else {
            //If file is existed, so lodaing this data file
            System.out.println("\nThe date file answers.txt is found. "
                    + "Data of answer is loading...");
            //Loads text file into buffer       
            try (BufferedReader br = new BufferedReader(new FileReader(A_FILE))) {
                String qId, aId, aContent, aStatus;

                this.numberOfAnswer = Integer.parseInt(br.readLine()); //Reads number of answers

                for (int i = 0; i < this.numberOfAnswer; i++) {
                    //Reads answer's infomation
                    aId = br.readLine();
                    aContent = br.readLine();
                    aStatus = br.readLine();
                    qId = br.readLine();
                    //Create new instance of Answer and adds to answer bank
                    this.answers.add(new Answer(Integer.parseInt(aId),
                            aContent,
                            Boolean.parseBoolean(aStatus),
                            Integer.parseInt(qId)));
                }
            }
            System.out.println("Done! [" + this.numberOfAnswer + " answer]");

        }

    }

    /**
     * Adds new answer to answer bank
     *
     * @param aContent
     * @param aStatus
     * @param qId
     * @return
     * @throws AnswerException
     */
    public int addAnswer(String aContent, boolean aStatus, int qId) throws AnswerException {

        this.answers.add(new Answer(++this.numberOfAnswer, aContent, aStatus, qId));
        return this.numberOfAnswer;
    }

    /**
     * Finds answer by answer id and return the index of this answer
     *
     * @param aId
     * @return
     */
    public int findAnswer(int aId) {
        for (int i = 0; i < this.answers.size(); i++) {
            Answer a = this.answers.get(i);
            if (a.getAId() == aId) {
                return i;
            }

        }
        return -1;
    }

    /**
     * Finds the answer instance by answer id
     *
     * @param aId
     * @return
     */
    public Answer getAnswer(int aId) {
        int idx = this.findAnswer(aId);
        if (idx == -1) {
            return null;
        } else {
            return this.answers.get(idx);
        }
    }

    /**
     * Saves answer bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveAnswer() throws IOException {
        try ( //Overwrite data file
                FileWriter fw = new FileWriter(new File(A_FILE), false)) {
            System.out.print("\nAnswer is saving into data file answer.txt...");
            //Wirtes numbe of answer

            fw.append(String.valueOf(this.numberOfAnswer) + "\n");
            for (int i = 0; i < this.numberOfAnswer; i++) {
                //Inits answer's information
                int aId = this.answers.get(i).getAId();
                String aContent = this.answers.get(i).getAContent();
                boolean aStatus = this.answers.get(i).getAStatus();
                int qId = this.answers.get(i).getQId();

                //Writes answer's information into data files
                fw.append(String.valueOf(aId) + "\n");
                fw.append(aContent + "\n");
                fw.append(String.valueOf(aStatus) + "\n");
                fw.append(String.valueOf(qId) + "\n");
            }
        } finally {
//Saves data file (from Ram into HDD)
            System.out.println("Done! [" + this.numberOfAnswer + " answer ]");

        }
    }

    /**
     * Gets all answer that belongs to question that identifies by question id
     *
     * @param qId
     * @return
     */
    public ArrayList<Answer> getAnswer(int qId, boolean isShuffle) {
        ArrayList<Answer> aList = new ArrayList<>();

        for (int i = 0; i < this.answers.size(); i++) {
            Answer a = this.answers.get(i);
            if (a.getQId() == qId) {
                aList.add(a);
            }
        }
        //Inits the index of all answer
        int[] idx = new int[aList.size()];
        for (int i = 0; i < aList.size(); i++) {
            idx[i] = i;
        }
        if (isShuffle) { //If the random mode is turned on
            int newIdx, tmp;
            Random ran = new Random();

            //Randomized indexes of answer bank
            for (int i = 0; i < aList.size(); i++) {
                newIdx = ran.nextInt(aList.size());
                tmp = idx[i];
                idx[i] = idx[newIdx];
                idx[newIdx] = tmp;
            }
        }
        ArrayList<Answer> result = new ArrayList<>();
        for (int i = 0; i < aList.size(); i++) {
            result.add(aList.get(idx[i]));
        }

        return result;

    }

}
