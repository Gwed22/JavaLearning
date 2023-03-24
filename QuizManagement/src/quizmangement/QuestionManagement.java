package quizmangement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The class QuestionManagement is used to manage question bank
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class QuestionManagement {

    private String Q_FILE;                      //The URL of data file that stores all questions      
    private int numberOfQuestion;               //Number of questions that stored in data file
    private ArrayList<Question> questions;      //All intances of questions
    private AnswerManagement am;                //Instance of AnswerManagements

    /**
     * Creates instance for question management
     *
     * @param Q_FILE
     * @param am
     * @throws quizmanagement.QuestionException
     */
    public QuestionManagement(String Q_FILE, AnswerManagement am) throws QuestionException {
        if (Q_FILE.equals("")) {
            throw new QuestionException("The URL of answer data file can't be emty!");
        } else {
            this.Q_FILE = Q_FILE; //Inits the URL of data file thats stores question bank

            this.questions = new ArrayList<>(); //Creates empty question bank

            this.numberOfQuestion = 0;  //So, the number of question is 0

            this.am = am;  // Inits the answer management
        }

    }

    QuestionManagement(String questiontxt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Loads data of questions from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws QuestionException
     */
    public void loadQuestion() throws IOException, QuestionException {
        File qFile = new File(Q_FILE);

        if (!qFile.exists()) { //Checks is file created
            qFile.createNewFile();  //If not, ceates new file
            System.out.println("The data file question.txt is not exits."
                    + "Creating new data file question.txt..."
                    + "Done!");
            this.numberOfQuestion = 0; //New data file with the number of question is 0
        } else {
            //If file is exsited so loading this data file
            System.out.println("\nThe data file questions.txt is found."
                    + "Data of question is loading....");
            BufferedReader br = new BufferedReader(new FileReader(Q_FILE)); //Loads text file into buffer
            try {
                String qId, qContent, qMark;

                this.numberOfQuestion = Integer.parseInt(br.readLine()); //Reads number of answers
                for (int i = 0; i < this.numberOfQuestion; i++) {
                    //Reads answer's information
                    qId = br.readLine();
                    qContent = br.readLine();
                    qMark = br.readLine();

                    //Create new instance of Answer and adds to answer banks
                    this.questions.add(new Question(Integer.parseInt(qId),
                            Double.parseDouble(qMark),
                            qContent));

                }
            } finally {
                br.close();
            }
            System.out.println("Done! [" + this.numberOfQuestion + " question]");
        }

    }

    /**
     * Gets number of questions
     *
     * @return
     */
    public int getSize() {
        return this.numberOfQuestion;
    }

    /**
     * Adds new question to question bank
     *
     * @param qMark
     * @param qContent
     * @return
     * @throws QuestionException
     */
    public int addQuestion(double qMark, String qContent) throws QuestionException {

        this.questions.add(new Question(++this.numberOfQuestion, qMark, qContent));
        return this.numberOfQuestion;
    }

    /**
     * Finds question by question id and return the index of this question
     *
     * @param qId
     * @return
     */
    public int findQuestion(int qId) {
        for (int i = 0; i < this.questions.size(); i++) {
            Question q = this.questions.get(i);
            if (q.getQId() == qId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the question instance by question id
     *
     * @param qId
     * @return
     */
    public Question getQuestion(int qId) {
        int idx = this.findQuestion(qId);
        if (idx == -1) {
            return null;
        } else {
            return this.questions.get(idx);
        }

    }

    /**
     * Saves question bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveQuestion() throws IOException {

        try (FileWriter fw = new FileWriter(new File(Q_FILE), false) //Overwrite data file
                ) {
            System.out.print("\nQuestions is saving into data file question.txt...");

            fw.append(String.valueOf(this.numberOfQuestion) + "\n"); //Writes number of question
            for (int i = 0; i < this.numberOfQuestion; i++) {
                //Inits question's information
                int qId = this.questions.get(i).getQId();
                String qContent = this.questions.get(i).getQContent();
                double qMark = this.questions.get(i).getQMark();

                //Writes question;s information into data file
                fw.append(String.valueOf(qId) + "\n");
                fw.append(qContent + "\n");
                fw.append(String.valueOf(qMark) + "\n");
            }
        } finally { //Saves data file (from RAM to HDD)
            //Saves data file (from RAM to HDD)
            System.out.println("Done! [" + this.numberOfQuestion + " question]");
        }
    }

    /**
     * Checks that the user's answer is correct or incorrect
     *
     * @param qId
     * @param answers
     * @return
     */
    public boolean isQuestionCorrect(int qId, ArrayList<Answer> answers) {
        boolean isCorrect = true;
        for (int i = 0; i < answers.size(); i++) {
            //the answer of user is correct even if the user's selected is the same with anser's status
            isCorrect = isCorrect && answers.get(i).isCorrect();

        }
        return isCorrect;
    }

    /**
     * Gets the question formatted string that includes question content and all
     * answers that comes with random mod
     *
     * @param qId
     * @param isShuffle turn on/off random display answer mod
     * @return
     */
    public String showQuestion(int qId, boolean isShuffle) {
        Question q = getQuestion(qId);
        ArrayList<Answer> aList = am.getAnswer(qId, isShuffle);

        String str = "";
        str += q.toString();
        char aNo = 'a';
        for (int i = 0; i < aList.size(); i++, aNo++) {
            str += "   " + aNo + ". " + aList.get(i).toString();
        }
        return str;
    }

    /**
     * Gets the question formatted string that includes question content and all
     * answers that comes with list of answers
     *
     * @param qId
     * @param aList
     * @return
     */
    public String showQuestion(int qId, ArrayList<Answer> aList) {
        Question q = getQuestion(qId);

        String str = "";

        str += q.toString();

        char aNo = 'a';
        for (int i = 0; i < aList.size(); i++, aNo++) {
            str += "   " + aNo + ". " + aList.get(i).toString();
        }
        return str;
    }

    /**
     * Displays all question of question bank
     */
    public void showQuestionBank() {
        int qNo = 1;
        for (int i = 0; i < this.questions.size(); i++, qNo++) {
            Question q = this.questions.get(i);

            System.out.println(qNo + ". " + showQuestion(q.getQId(), false));
        }
    }

    /**
     * Gets the first qNumber of question bank
     *
     * @param qNumber
     * @param isShuffle
     * @return
     */
    public ArrayList<Question> getQuestionBank(int qNumber, boolean isShuffle) {
        ArrayList<Question> qList = new ArrayList<>();

        //Inits the index of all answer
        int[] idx = new int[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            idx[i] = i;
        }
        if (isShuffle) { //if the random mode is turned on
            int newIdx, tmp;
            Random ran = new Random();

            //Randomizes indexes of answer bank
            for (int i = 0; i < questions.size(); i++) {
                newIdx = ran.nextInt(questions.size());

                tmp = idx[i];
                idx[i] = idx[newIdx];
                idx[newIdx] = tmp;
            }
        }
        for (int i = 0; i < qNumber; i++) {
            qList.add(questions.get(idx[i]));
        }
        return qList;
    }
}
