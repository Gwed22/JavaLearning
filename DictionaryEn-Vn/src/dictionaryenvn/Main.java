package dictionaryenvn;

import java.awt.AWTEventMulticaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class Main {

    private static DictionaryEnVn dm;

    /**
     *
     * @param args the command line arguments
     * @throws IOException
     * @throws DictionaryException
     */
    public static void main(String[] args) throws IOException, DictionaryException {

        try {
            dm = new DictionaryEnVn("src/data/EnVn.txt");
            dm.loadWord();

            Scanner cin = new Scanner(System.in);
            int func = 0;
            boolean isValid;
            String strUserEntered = "";

            do {
                //Show menu
                System.out.println("\n ----- English - Vietnamese Dictionary -----\n");
                System.out.println("1. Search word by English.");
                System.out.println("2. Search word by Vietnamese.");
                System.out.println("3. Add new word.");
                System.out.println("4. Vocabulary testing.");
                System.out.println("5. Quit.");
                //Gets funcetion that selected by user
                do {
                    isValid = false;
                    try {
                        System.out.print("\t Please enter the function: ");
                        func = Integer.parseInt(cin.nextLine());
                        if (func <= 0 || func > 5) {
                            System.out.println("Error: Please choose the function from 1 to 5!");
                            isValid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please choose the function from 1 to 5!");
                        isValid = true;
                    }
                } while (isValid == true);

                switch (func) {
                    case 1:
                        do {
                            try {
                                System.out.print("Please enter the word you want to search: ");
                                strUserEntered = cin.nextLine().trim();
                                if (strUserEntered.equals("")) {
                                    System.out.println("Error: The word can't be empty");
                                } else {
                                    dm.findWordbyEn(strUserEntered);
                                    System.out.print("Do you want to search again? (Yes/No) ");
                                    strUserEntered = cin.nextLine().trim();
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Word can't be empty");
                            }
                        } while (strUserEntered.equals("") || strUserEntered.toLowerCase().equals("yes"));
                        break;
                    case 2:
                        do {
                            System.out.print("Please enter the vietnamese meaning you want to search: ");
                            strUserEntered = cin.nextLine().trim();
                            if (strUserEntered.equals("")) {
                                System.out.println("Error: The vietnamese meaning can't be empty");
                            } else {
                                dm.findWordbyVn(strUserEntered);
                                System.out.print("Do you want to search again? (Yes/No) ");
                                strUserEntered = cin.nextLine().trim();
                            }
                        } while (strUserEntered.equals("") || strUserEntered.toLowerCase().equals("yes"));
                        break;
                    case 3:
                        System.out.println("\n----- [ADD NEW WORD] -----");
                        String vn = "";
                        String en = "";

                        isValid = false; //Allow the function to execute again or not
                        //Get the id of the fish
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter new word: ");
                                en = cin.nextLine().trim();
                                if (en.equals("")) {
                                    System.out.println("Error: Word can't be empty!");
                                    isValid = true;
                                } else if (dm.isWordValid(en)) {
                                    System.out.println("Error: Word has been existed! Please enter another word!");
                                    isValid = true;
                                } else if (en.length() > 13) {
                                    System.out.println("Error: Word can be only 15 characters!");
                                    isValid = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Word can't be empty");
                                isValid = true;
                            }
                        } while (isValid);
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter vietnamese meaning of word: ");
                                vn = cin.nextLine().trim();
                                if (en.equals("")) {
                                    System.out.println("Error: Vietnamese meaning can't be empty!");
                                    isValid = true;
                                } else if (vn.length() > 13) {
                                    System.out.println("Error: Vietnamese meaning can be only 15 characters!");
                                    isValid = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Vietnamese meaning can't be empty");
                                isValid = true;
                            }
                        } while (isValid);
                        dm.addWord(en, vn);
                        System.out.println("New word has been add successfully!");
                        break;
                    case 4:
                        Hashtable<Integer, String> Wrong1
                                = new Hashtable<Integer, String>();
                        Hashtable<String, String> Wrong2
                                = new Hashtable<String, String>();
                        ArrayList<String> Ans = new ArrayList<>();
                        int numberOfWord = 10;
                        int mark = 0;
                        int correctCount = 0;

                        System.out.println("----- Vocabulary [EXAMINATION] (" + numberOfWord + " words) -----");

                        //Generates the test
                        System.out.print("+++ The test is generating... ");
                        ArrayList<String> List = dm.getWord(numberOfWord);
                        System.out.println("Done! +++");

                        System.out.println("\n######################");
                        System.out.println("#       TESTING      #");

                        int qNo = 1;
                        String ans;

                        for (int i = 0; i < List.size(); i++, qNo++) {
                            System.out.println("######################");
                            System.out.println(qNo + ". " + List.get(i));

                            do {
                                //Gets the answer of user
                                System.out.print("    >>> Please enter your answer: ");
                                ans = cin.nextLine();
                                ans = ans.replaceAll("\\s\\s+", " ").trim();
                                Ans.add(ans);
                                if (ans.equals("")) {
                                    System.out.println("Error: Vietnamese word can't be empty!");
                                }
                            } while (ans.equals(""));

                            boolean isUserCorrect = dm.isAnswerCorrect(ans, List.get(i));
                            if (isUserCorrect) {
                                mark += 1;
                                correctCount++;
                                System.out.println("    +++ Congratulation! Your answer is CORRECT!!!");
                            } else {
                                System.out.println("    --- So sad! Your answer is WRONG!!!");
                                Wrong1.put(qNo, List.get(i));
                            }
                            cin.nextLine();
                        }

                        System.out.println("++++++++++++++++++++++++++");
                        System.out.println("You are FINISH!!!");
                        System.out.println("Correct rate is " + correctCount + "/" + 10 + " (" + String.format("%.2f", ((double) mark * 100 / 10)) + "%)");
                        System.out.println("Total mark is " + String.format("%d", mark) + "/" + 10 + " (" + String.format("%.2f", ((double) mark * 100 / 10)) + "%)");
                        if (correctCount < 10) {
                            System.out.println("+-----+----------------+----------------+----------------+");
                            System.out.println("| No. |   Vietnamese   | Answer Entered |    English     |");
                            System.out.println("+-----+----------------+----------------+----------------+");
                            for (Integer WrongqNo : Wrong1.keySet()) {
                                String Question = Wrong1.get(WrongqNo);
                                String CorrectA = dm.CorrectA(List.get(--WrongqNo));
                                System.out.printf("|%4d | %-14s | %-14s | %-14s |\n", ++WrongqNo, Question, Ans.get(WrongqNo), CorrectA);
                            }
                            System.out.println("+-----+----------------+----------------+----------------+");
                        }
                        cin.nextLine();
                        break;
                    case 5:
                        //Quit the program
                        System.out.println("\n-----------------");
                        System.out.println("Thanks for using our software!\n" + "See you again!");
                        break;
                    default:
                        System.out.println("Error: The function must be from 1 to 6!");
                }

            } while (func != 5);
        } catch (Exception e) {
            System.out.println("Error: You must enter a number!");
        } finally {
            try {
                dm.saveWord();//Save fishes
            } catch (IOException e) {
                System.out.println("Exception: Can't save words!");
            }
        }
    }
}
