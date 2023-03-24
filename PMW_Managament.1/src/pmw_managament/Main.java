package pmw_managament;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Group 4 - PRO192
 */
public class Main {

    private static PMW_Managament pmw;

    /**
     *
     * @param args the command line arguments
     * @throws IOException
     * @throws PMWException
     */
    public static void main(String[] args) throws IOException, PMWException {
        try {
            pmw = new PMW_Managament("test/wallet.txt");
            pmw.loadData(); //load data from file

            Scanner cin = new Scanner(System.in);
            int func = 0;
            boolean isValid;
            do {
                //Show menu
                System.out.println("\n        Personal Money Management    \n");
                System.out.println("1. Add transaction.");
                System.out.println("2. Update transaction.");
                System.out.println("3. Delete transaction.");
                System.out.println("4. Transaction statistics.");
                System.out.println("5. Quit.");
                //Gets funcetion that selected by user
                do {
                    isValid = false;
                    try {
                        System.out.print("\t Please enter the function: ");
                        func = Integer.parseInt(cin.nextLine().trim());
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
                        //add new transaction
                        pmw.add();
                        cin.nextLine();
                        break;
                    case 2:
                        //Update transaction
                        pmw.update();
                        cin.nextLine();
                        break;
                    case 3:
                        //Deleted transaction
                        pmw.deleted();
                        cin.nextLine();
                        break;
                    case 4:
                        //Statics transaction
                        pmw.Static();
                        cin.nextLine();
                        break;
                    case 5:
                        //Quit program
                        System.out.println("\n-----------------");
                        System.out.println("Thanks for using our software!\n" + "See you again!");
                        break;
                }
            } while (func != 5);
        } catch (Exception e) {
            System.out.println("Error: You must enter a number!");
        } finally {
            try {
                pmw.saveData(); //Save data
            } catch (IOException e) {
                System.out.println("Exception: Can't save data!");
            }
        }
    }
}
