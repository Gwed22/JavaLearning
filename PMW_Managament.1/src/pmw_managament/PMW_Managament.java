/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmw_managament;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Group 4 - PRO192
 */
public class PMW_Managament {

    private ArrayList<PMWData> pm;
    private String P_FILE;
    private int numberOfIncome;

    Scanner cin = new Scanner(System.in);

    /**
     * Created instance for Wallet
     * @param P_FILE
     */
    public PMW_Managament(String P_FILE) throws PMWException {
        if (P_FILE.equals("")) {
            throw new PMWException("The URL can't be empty");
        }
        this.pm = new ArrayList<PMWData>();
        this.P_FILE = P_FILE;
        this.numberOfIncome = 0;
    }

    /**
     * Load data of wallet for data file and stored it
     * @throws IOException
     * @throws PMWException
     */
    public void loadData() throws IOException, PMWException {
        File pFile = new File(P_FILE);
        if (!pFile.exists()) {     //Check is file created
            pFile.createNewFile();//If not, creates new file
            System.out.println("The data file wallet.txt is not exist."
                    + "Creating new data file wallet.txt... "
                    + "Done!");
            this.numberOfIncome = 0; //New data file with the number of transaction is 0      
        } else {

            //If file is existed, so loading this data file
            System.out.print("\nThe data file wallet.txt is found. "
                    + "Data of transactions is loading... ");
            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(P_FILE))) {
                String ID, Date, cause, money;

                this.numberOfIncome = Integer.parseInt(br.readLine()); //Reads number of transaction

                for (int i = 0; i < this.numberOfIncome; i++) {

                    //Reads wallet's information
                    ID = br.readLine();
                    Date = br.readLine();
                    cause = br.readLine();
                    money = br.readLine();

                    //Create new instance of transaction and adds of wallet
                    this.pm.add(new PMWData(Integer.parseInt(ID), Date, cause, Long.parseLong(money)));
                }

            }
            if (this.numberOfIncome == 1) {
                System.out.println("Done! [" + this.numberOfIncome + " transaction]");
            } else {
                System.out.println("Done! [" + this.numberOfIncome + " transactions]");
            }
        }
    }

    /**
     * Save data of Wallet into file wallet.TXT
     * @throws IOException
     */
    public void saveData() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(P_FILE), false);
        try {
            System.out.print("\nTransaction is saving into data file wallet.txt... ");

            //Writes number of answer
            fw.append(String.valueOf(this.numberOfIncome) + "\n");

            for (int i = 0; i < this.numberOfIncome; i++) {

                fw.append(String.valueOf(this.pm.get(i).getID()) + "\n");
                fw.append(this.pm.get(i).getDate() + "\n");
                fw.append(this.pm.get(i).getCause() + "\n");
                fw.append(this.pm.get(i).getMoney() + "\n");

            }
        } finally {
            //Saves data file (from RAM into HDD)
            fw.close();
            if (this.numberOfIncome == 1) {
                System.out.println("Done! [" + this.numberOfIncome + " transaction]");
            } else {
                System.out.println("Done! [" + this.numberOfIncome + " transactions]");
            }
        }
    }
    final static String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * Check date valid or invalid
     * @param date
     * @return
     */
    public static boolean isDateValid(String date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        df.setLenient(false);
        try {
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * add new date into Wallet
     * @param ID
     * @param Date
     * @param cause
     * @param money
     * @return
     * @throws PMWException
     */
    public int addDate(int ID, String Date, String cause, Long money) throws PMWException {
        this.pm.add(new PMWData(ID, Date, cause, money));
        return ++this.numberOfIncome;
    }

    /**
     * Display result on screen
     * @param newList
     */
    public void display(ArrayList<PMWData> newList) {
        sort(newList);
        System.out.printf("+------+-------+---------------+----------------------------------------------------+--------------------------+\n");
        System.out.printf("| No.  |  ID   |     Date      |                       Reason                       |     Earning/Spending     |\n");
        System.out.printf("+------+-------+---------------+----------------------------------------------------+--------------------------+\n");
        for (int i = 0; i < newList.size(); i++) {
            PMWData pm = newList.get(i);
            int number = i + 1;
            System.out.printf("| %4s | %5d |   %10s  | %-50s | %,20d VND |\n", number, pm.getID(), pm.getDate(), pm.getCause(), pm.getMoney());
            System.out.printf("+------+-------+---------------+----------------------------------------------------+--------------------------+\n");
        }

    }

    /**
     * Check date exist or not
     * @param date
     * @return
     */
    public boolean isDateExist(String date) {
        for (PMWData item : pm) {
            if (item.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check ID exist or not
     * @param ID
     * @return
     */
    public boolean isIdExist(int ID) {
        for (PMWData item : pm) {
            if (item.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check reason exist or not
     * @param s
     * @return
     */
    public boolean isReasonExist(String s) {
        for (PMWData item : pm) {
            if (item.getCause().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method is add new transaction into Wallet
     * @throws PMWException
     * @throws ParseException
     */
    public void add() throws PMWException, ParseException {
        boolean isValid;
        PMWData cb = new PMWData();
        int ID = 0;
        String date = "";
        Date date2 = new Date();
        String cause = "";
        Long money = null;
        System.out.println("\n\t ----- [ADD TRANSACTION] -----");
        do {
            try {
                //Gets date by user
                isValid = true;
                System.out.print("Please enter the date: ");
                date = cin.nextLine().trim();
                Date date1 = new SimpleDateFormat(DATE_FORMAT).parse(date);
                int test = date2.compareTo(date1);
                //Check exception
                if (!isDateValid(date)) {
                    System.out.println("Error: Date is not valid");
                    isValid = false;
                } else if (test <= 0) {
                    System.out.println("Error: Please enter today or yesterday!");
                    isValid = false;
                } else if (!(date.matches("\\d{4}-\\d{2}-\\d{2}"))) {
                    System.out.println("Error: Please enter follow format YYYY-MM-DD!");
                    isValid = false;
                } else {
                    do {
                        try {
                            //Gets new ID by user
                            isValid = true;
                            System.out.print("Please enter ID: ");
                            ID = Integer.parseInt(cin.nextLine().trim());
                            if (isIdExist(ID)) {
                                System.out.println("Error: ID exist!");
                                isValid = false;
                            } else if (ID < 0) {
                                System.out.println("Error: Please enter a positive number!");
                                isValid = false;
                            } else if (String.valueOf(ID).length() > 4) {
                                System.out.println("Error: ID is too large, please enter ID less than 9999!");
                                isValid = false;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter number!");
                            isValid = false;
                        }
                    } while (isValid == false);
                    do {
                        try {
                            isValid = true;
                            System.out.print("Please enter reason: "); //Gets new reason by user
                            cause = cin.nextLine().trim();
                            //Check exception
                            if (cause.equals("")) {
                                System.out.println("Error: Reason can't be empty!");
                                isValid = false;
                            } else if (cause.length() > 50) //If reason too long error report 
                            {
                                System.out.println("Error: Reason is too long, please enter reason less than 50 characters!");
                                isValid = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Reason can't be empty!");
                            isValid = false;
                        }
                    } while (isValid == false);
                    do {
                        try {
                            isValid = true;
                            System.out.print("Please enter earn or spend: "); //Gets new earn or spend by user
                            money = Long.parseLong(cin.nextLine().trim());
                            if (String.valueOf(money).length() > 15) //If money too long error report
                            {
                                System.out.println("Error: Money is too large, please enter money less than 15 characters!");
                                isValid = false;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter number!");
                            isValid = false;
                        }
                    } while (isValid == false);
                    addDate(ID, date, cause, money);
                    display(pm);
                }
            } catch (ParseException e) {
                System.out.println("Error: Please enter follow format YYYY-MM-DD!");
                isValid = false;
            }

        } while (isValid == false);
    }

    /**
     * Update new transaction
     */
    public void update() {
        boolean isValid;
        boolean isID = false;
        String str1;
        String date = "";
        ArrayList<PMWData> cb = new ArrayList<>();
        Long money = null;
        int ID = 0;
        int func = 0;
        System.out.println("\n\t ----- [UPDATE TRANSACTION] -----");
        do {
            isValid = true;
            System.out.print("Please enter date you want update: "); //Gets date user want to update
            date = cin.nextLine().trim();
            if (!(date.matches("\\d{4}-\\d{2}-\\d{2}"))) //Check date if not format
            {
                System.out.println("Error: Please enter follow format YYYY-MM-DD!");
                isValid = false;
            } else if (!isDateExist(date)) //Check date if not exit
            {
                System.out.println("Error: Don't have information of date or Date invalid!");
                isValid = false;
            } else if (!isDateValid(date)) //Check date if not invalid
            {
                System.out.println("Error: Date invalid!");
                isValid = false;
            }
        } while (isValid == false);

        for (PMWData item : pm) {
            if (item.getDate().equals(date)) {
                cb.add(item);
            }
        }
        display(cb);
        do {
            try {
                isValid = true;
                //Gets ID want to update
                System.out.print("Please enter ID: ");
                ID = Integer.parseInt(cin.nextLine().trim());
                //Check error ID if not exist
                for (PMWData item : cb) {
                    if (item.getID() == ID) {
                        isID = true;
                    }
                }
                if (isID == false) {
                    System.out.println("Error: ID is not exist!");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter number!");
                isValid = false;
            }
        } while (isValid == false);
        PMWData a = new PMWData();
        for (PMWData item : cb) {
            if (item.getID() == ID) {
                a = item;
            }
        }
        do {
            isValid = true;
            //Show menu update
            System.out.println("Choice one you want update: ");
            System.out.println("1. Reason.");
            System.out.println("2. Income.");
            System.out.println("3. Both");
            do {
                try {
                    isValid = true;
                    //Gets func by user want to update
                    System.out.print("\t Please enter function you want update: ");
                    func = Integer.parseInt(cin.nextLine().trim());
                    if (func <= 0 || func > 3) {
                        System.out.println("Error: Please choose the function from 1 to 3!");
                        isValid = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter number!");
                    isValid = false;
                }
            } while (isValid == false);
            switch (func) {
                case 1:
                    do {
                        try {
                            //Enter new reason
                            isValid = true;
                            System.out.print("Enter your new reason: ");
                            str1 = cin.nextLine().trim();
                            str1 = str1.replaceAll("\\s\\s+", " ").trim();
                            
                            if (str1.equals("")) {
                                System.out.println("Error: New reason can't be empty!");
                                isValid = false;
                            } else if (isReasonExist(str1)) {
                                System.out.println("Error: You enter old reason!");
                                isValid = false;
                            } else {
                                a.setCause(str1);
                                System.out.println("Your new reason has been update!");
                                isValid = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: New reason can't be empty!");
                            isValid = false;
                        }
                    } while (isValid == false);
                    break;
                case 2:
                    do {
                        try {
                            //User enter new earn/spend
                            isValid = true;
                            System.out.print("Enter your new earn/spend: ");
                            money = Long.parseLong(cin.nextLine().trim());
                            a.setMoney(money);
                            System.out.println("Your new earn/spend has been update!");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter number!");
                            isValid = false;
                        }
                    } while (isValid == false);

                    break;
                case 3:
                    do {
                        try {
                            //Enter new reason by user
                            isValid = true;
                            System.out.print("Enter your new reason: ");
                            str1 = cin.nextLine().trim();
                            str1 = str1.replaceAll("\\s\\s+", " ").trim();

                            if (str1.equals("")) {
                                System.out.println("Error: New reason can't be empty!");
                                isValid = false;
                            } else if (isReasonExist(str1) == true) {
                                System.out.println("Error: You enter old reason!");
                                isValid = false;
                            } else {
                                a.setCause(str1);
                                System.out.println("Your new reason has been update!");
                                isValid = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: New reason can't be empty!");
                            isValid = false;
                        }
                    } while (isValid == false);
                    do {
                        try {
                            System.out.print("Enter your new transaction: ");
                            money = Long.parseLong(cin.nextLine().trim());
                            a.setMoney(money);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter number!");
                            isValid = false;
                        }
                    } while (isValid == false);
                    System.out.println("Your new date has been update!");
                    break;
            }
            display(pm);
        } while (isValid == false);
    }

    /**
     * Method is deleted TRANSACTION
     */
    public void deleted() {
        String str1 = null;
        String date = "";
        String reason = "";
        Long money = null;
        int ID = 0;
        int func = 0;
        boolean isValid = true;
        boolean isID = true;
        System.out.println("\n\t ----- [DELETE TRANSACTION] -----");
        display(pm);
        do {
            isValid = true;
            //Gets date user want to deleted
            System.out.print("Please enter date you want deteled: ");
            date = cin.nextLine().trim();
            date = date.replaceAll("\\s\\s+", " ").trim();
            if (!(date.matches("\\d{4}-\\d{2}-\\d{2}"))) {
                System.out.println("Error: Please enter follow format YYYY-MM-DD!");
                isValid = false;
            } else if (!isDateExist(date) || !isDateValid(date)) {
                System.out.println("Error: Don't have information of date or Date invalid");
                isValid = false;
            }
        } while (isValid == false);
        ArrayList<PMWData> cb = new ArrayList<>();
        for (PMWData item : pm) {
            if (item.getDate().equals(date)) {
                cb.add(item);
            }
        }
        display(cb);

        do {
            try {
                isValid = true;
                System.out.print("Please enter ID: ");
                ID = Integer.parseInt(cin.nextLine().trim());
                for (PMWData item : cb) {
                    if (item.getID() == ID) {
                        isID = true;
                    } else {
                        isID = false;
                    }
                }
                if (isID == false) {
                    System.out.println("Error: ID is not exist!");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter number!");
                isValid = false;
            }
        } while (isValid == false);
        do {
            do {
                try {
                    //Ask user want to delete or not
                    System.out.print("Do you really want to delete? (Yes/No) ");
                    str1 = cin.nextLine().trim();
                    if ((!(str1.toLowerCase().equals("yes") || (str1.toLowerCase().equals("no"))))) {
                        System.out.println("Error: You must type 'Yes' or 'No'!");
                    } else if (str1.toLowerCase().equals("yes")) {
                        PMWData cb1 = new PMWData();
                        for (PMWData item : pm) {
                            if (item.getID() == ID) {
                                cb1 = item;
                            }
                        }
                        pm.remove(cb1);
                        display(pm);
                        --numberOfIncome;
                        System.out.println("Transaction has been delete successfully!");
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: You must type 'Yes' or 'No'!");
                }
            } while (str1.toLowerCase().equals("yes"));
            if (str1.toLowerCase().equals("no")) {
                System.out.println("Transaction has not been delete!");
            }
        } while (!(str1.toLowerCase().equals("yes") || str1.toLowerCase().equals("no")));
    }

    /**
     * Static money of wallet
     * @throws ParseException
     * @throws PMWException
     */
    public void Static() throws ParseException, PMWException {
        ArrayList<PMWData> cb = new ArrayList<>();
        boolean isValid = true;
        String dateF = "";
        String dateT = "";
        Date dateNow = new Date();

        System.out.println("\n\t----- [WALLET STATISTIC] -----");
        do {
            try {
                isValid = true;
                //Gets date (from) by user
                System.out.print("Please enter the day to search (From): ");
                dateF = cin.nextLine().trim();

                if (!(dateF.matches("\\d{4}-\\d{2}-\\d{2}"))) {
                    System.out.println("Error: Please enter follow format YYYY-MM-DD!");
                    isValid = false;
                } else if (!isDateValid(dateF)) {
                    System.out.println("Error: Date invalid!");
                    isValid = false;
                } else {
                    Date date1 = new SimpleDateFormat(DATE_FORMAT).parse(dateF);
                    if (date1.after(dateNow)) {
                        System.out.println("Error: Please enter yesterday or today!");
                        isValid = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter follow format YYYY-MM-DD!'");
                isValid = false;
            }
        } while (isValid == false);
        do {
            try {
                isValid = true;
                Date date1 = new SimpleDateFormat(DATE_FORMAT).parse(dateF);
                //Gets date(to) by user
                System.out.print("Please enter the day to search (To): ");
                dateT = cin.nextLine().trim();
                if (!(dateT.matches("\\d{4}-\\d{2}-\\d{2}"))) {
                    System.out.println("Error: Please enter follow format YYYY-MM-DD!");
                    isValid = false;
                } else if (!isDateValid(dateT)) {
                    System.out.println("Error: Date invalid!");
                    isValid = false;
                } else {
                    Date date2 = new SimpleDateFormat(DATE_FORMAT).parse(dateT);
                    isValid = true;
                    if (date2.before(date1)) {
                        System.out.println("Error: Date (To) must be after Date(From) ");
                        isValid = false;
                    } else if (date2.after(dateNow)) {
                        System.out.println("Error: Error: Please enter yesterday or today!");
                        isValid = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter follow format YYYY-MM-DD!'");
                isValid = false;
            }
        } while (isValid == false);
        Date date1 = new SimpleDateFormat(DATE_FORMAT).parse(dateF);
        for (PMWData item : pm) {
            Date dateS = new SimpleDateFormat(DATE_FORMAT).parse(item.getDate());
            Date date2 = new SimpleDateFormat(DATE_FORMAT).parse(dateT);
            if (date1.before(date2) && date2.before(dateNow) && dateS.after(date1) && dateS.before(date2)) {
                cb.add(item);
            } else if (date1.equals(dateS) && date2.equals(dateS)) {
                cb.add(item);
            }
        }
        display(cb);
        System.out.printf("Total amount of spending from %s to %s: %,d VND\n", dateF, dateT, (Spend(cb)));
        System.out.printf("Total amount of earning  from %s to %s: %,d VND\n", dateF, dateT, (Earn(cb)));
        displayStatic(pm);
    }

    /**
     * display total on screen
     * @param cb
     */
    public void displayStatic(ArrayList<PMWData> pm) {
        System.out.printf("Total amount of money in wallet %,d VND\n", Total(pm));
    }

    /**
     * summation spend money
     * @param newList
     * @return
     */
    public long Spend(ArrayList<PMWData> newList) {
        Long spend = 0L;
        for (int i = 0; i < newList.size(); i++) {
            PMWData pm = newList.get(i);
            if (pm.getMoney() < 0) {
                spend += pm.getMoney();
            }
        }
        return spend;
    }

    /**
     * summation earn money
     * @param newList
     * @return
     */
    public long Earn(ArrayList<PMWData> newList) {
        Long earn = 0L;
        for (int i = 0; i < newList.size(); i++) {
            PMWData pm = newList.get(i);
            if (pm.getMoney() > 0) {
                earn += pm.getMoney();
            }
        }
        return earn;
    }

    public long Total(ArrayList<PMWData> newList) {
        Long total = 0L;
        for (int i = 0; i < newList.size(); i++) {
            PMWData pm = newList.get(i);

            total += pm.getMoney();
        }
        return total;
    }

    /**
     * sort date
     * @param cb
     * @return
     */
    public ArrayList<PMWData> sort(ArrayList<PMWData> cb) {

        cb.sort((tr1, tr2) -> tr1.getDate().compareTo(tr2.getDate()));
        return cb;
    }
}
