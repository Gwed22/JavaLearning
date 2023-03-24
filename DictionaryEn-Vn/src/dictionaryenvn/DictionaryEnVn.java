/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryenvn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.lang.String;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class DictionaryEnVn {

    /**
     * @param args the command line arguments
     *
     */
    public static DictionaryEnVn dm;
    private String A_FILE; //The URL of data file that stores all comics
    private int numberOfWords; //Number of comics that stored in data file
    private Hashtable<String, String> word;

    public DictionaryEnVn(String A_FILE) {
        this.word = new Hashtable<>();
        this.A_FILE = A_FILE;
    }

    /**
     * Loads data of comics from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws comicmanagement.ComicException
     */
    public void loadWord() throws IOException, DictionaryException {
        File aFile = new File(A_FILE);
        if (!aFile.exists()) {     //Check is file created
            aFile.createNewFile();//If not, creates new file
            System.out.println("The data file EnVn.txt is not exist."
                    + "Creating new data file EnVn.txt... "
                    + "Done!");
            this.numberOfWords = 0; //New data file with the number of answer is 0      
        } else {

            //If file is existed, so loading this data file
            System.out.print("\nThe data file EnVn.txt is found. "
                    + "Data of words is loading...");
            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(A_FILE))) {
                String en, vn;

                this.numberOfWords = Integer.parseInt(br.readLine()); //Reads number of answers

                for (int i = 0; i < this.numberOfWords; i++) {
                    Hashtable<String, String> En
                            = new Hashtable<String, String>();
                    //Reads answer's information
                    en = br.readLine();
                    vn = br.readLine();

                    //Create new instance of words and adds of words bank
                    this.word.put(en, vn);
                }
            }
            System.out.println("Done! [" + this.numberOfWords + " words]");

        }
    }

    /**
     * get the number of comic
     *
     * @return
     */
    public int getSize() {
        return this.numberOfWords;
    }

    public int addWord(String en, String vn) throws DictionaryException {
        word.put(en, vn);
        return this.numberOfWords++;
    }

    public void saveWord() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(A_FILE), false);
        try {
            System.out.print("\nWord is saving into data file EnVn.txt... \n");

            //Writes number of answer
            fw.append(String.valueOf(this.numberOfWords) + "\n");

            for (String key : word.keySet()) {
                String value = word.get(key);

                //Writes answer's information into data file
                fw.append(key + "\n");
                fw.append(value + "\n");
            }
        } finally {
            //Saves data file (from RAM into HDD)
            fw.close();
            System.out.println("Done! [" + this.numberOfWords + " words]");
        }
    }

    public void findWordbyEn(String strUserEntered) {
        Hashtable<String, String> FindKW
                = new Hashtable<String, String>();
        Hashtable<String, String> Find
                = new Hashtable<String, String>();
        for (String key : word.keySet()) {
            String value = word.get(key);
            if (key.toLowerCase().equals(strUserEntered.toLowerCase())) {
                Find.put(value, key);
            } else if (key.toLowerCase().contains(strUserEntered.toLowerCase())) {
                FindKW.put(value, key);
            }
        }
        if (Find.isEmpty() && FindKW.isEmpty()) {
            System.out.println("Not found!");
        } else {
            //show if found
            this.displayEn(Find, FindKW);
        }
    }

    public void findWordbyVn(String strUserEntered) {
        Hashtable<String, String> Find
                = new Hashtable<String, String>();
        Hashtable<String, String> FindKW
                = new Hashtable<String, String>();
        for (String key : word.keySet()) {
            String value = word.get(key);
            if (value.toLowerCase().equals(strUserEntered.toLowerCase())) {
                Find.put(key, value);
            } else if (value.toLowerCase().contains(strUserEntered.toLowerCase())) {
                FindKW.put(key, value);
            }
        }
        if (Find.isEmpty() && FindKW.isEmpty()) {
            System.out.println("Not found!");
        } else {
            //show if found
            this.displayVn(Find, FindKW);
        }
    }

    public void displayVn(Hashtable<String, String> Find, Hashtable<String, String> FindKW) {
        int i = 1;
        if (FindKW.isEmpty()) {
            System.out.println("+-----+----------------+----------------+");
            System.out.println("| No. |   Vietnamese   |     English    |");
            System.out.println("+-----+----------------+----------------+");
            for (String key : Find.keySet()) {
                String value = Find.get(key);
                System.out.printf("|%4d | %-14s | %-14s |\n", i++, value, key);
            }
            System.out.println("+---------------------------------------+");
        } else if (Find.isEmpty()) {
            System.out.println("+-----+----------------+");
            System.out.println("| No. |   Vietnamese   |");
            System.out.println("+-----+----------------+");
            for (String key : FindKW.keySet()) {
                String value = FindKW.get(key);
                System.out.printf("|%4d | %-14s |\n", i++, value);
            }
            System.out.println("+----------------------+");
        }
    }

    public void displayEn(Hashtable<String, String> Find, Hashtable<String, String> FindKW) {

        int i = 1;
        if (FindKW.isEmpty()) {
            System.out.println("+-----+----------------+----------------+");
            System.out.println("| No. |     English    |   Vietnamese   |");
            System.out.println("+-----+----------------+----------------+");
            for (String key : Find.keySet()) {
                String value = Find.get(key);
                System.out.printf("|%4d | %-14s | %-14s |\n", i++, value, key);
            }
            System.out.println("+---------------------------------------+");
        } else if (Find.isEmpty()) {
            System.out.println("+-----+----------------+");
            System.out.println("| No. |     English    |");
            System.out.println("+-----+----------------+");
            for (String key : FindKW.keySet()) {
                String value = FindKW.get(key);
                System.out.printf("|%4d | %-14s |\n", i++, value);
            }
            System.out.println("+----------------------+");
        }

    }

    public boolean isWordValid(String en) {
        for (String key : word.keySet()) {
            if (key.toLowerCase().equals(en.toLowerCase()) == true) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getWord(int numberOfWord) {
        ArrayList<String> List = new ArrayList<String>();
        ArrayList<String> ListFull = new ArrayList<String>();

        for (String key : word.keySet()) {
            String value = word.get(key);
            ListFull.add(word.get(key));
        }
        //Inits the index of all answer
        int[] idx = new int[word.size()];

        for (int i = 0; i < word.size(); i++) {
            idx[i] = i;
        }
        int newIdx, tmp;
        Random ran = new Random();

        //Randomizes indexes of answer bank
        for (int i = 0; i < word.size(); i++) {
            newIdx = ran.nextInt(word.size());
            tmp = idx[i];
            idx[i] = idx[newIdx];
            idx[newIdx] = tmp;
        }

        //Pick word in the storage
        for (int i = 0; i < numberOfWord; i++) {
            List.add(ListFull.get(idx[i]));
        }
        return List;
    }

    public void Testing(ArrayList<String> List) {
        int No = 1;
        for (int i = 0; i < List.size(); i++) {
            System.out.println(No + ". " + List.get(i));
            No++;
        }
    }

    public boolean isAnswerCorrect(String ans, String wordOfTest) {
        boolean isCorrect = false;

        for (String key : word.keySet()) {
            if (key.toLowerCase().equals(ans.toLowerCase())) {
                if (word.get(key).toLowerCase().equals(wordOfTest.toLowerCase())) {
                    isCorrect = true;
                    break;
                }
            }
        }
        return isCorrect;
    }

    public String CorrectA(String query) {
        String Correct = "";
        for (String key : word.keySet()) {
            String value = word.get(key);
            if (value.toLowerCase().equals(query.toLowerCase())) {
                Correct += key;
            }
        }
        return Correct;
    }
    public String AnsEnter(String ans){
    String AnsEnter = ans;
    return AnsEnter;
    }
}
