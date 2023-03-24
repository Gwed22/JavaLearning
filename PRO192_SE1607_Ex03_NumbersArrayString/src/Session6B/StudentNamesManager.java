package Session6B;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Do Huynh Anh Vu Coder
 * @version 1
 * @Date 8/02/2022
 */
public class StudentNamesManager {

    /**
     * Menu for user to choose and call class to process
     */
    public static void main(String[] args) {
        int so = 0;
        boolean flag = true;
        Student dssv = new Student();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-----------------------");
            System.out.println("STUDENT NAMES MANAGER");
            System.out.println(" 1. Add a name");
            System.out.println(" 2. Show name list");
            System.out.println(" 3. Search for name");
            System.out.println(" 4. Exit");
            System.out.println("-----------------------");

            Scanner input = new Scanner(System.in);
            boolean isNumberValid = true;

            try {
                isNumberValid = true;
                System.out.print("Please enter your choice:");
                so = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                if (so == 1 || so == 2 || so == 3 || so == 4) {
                    System.out.println("Please choose options above!");
                    isNumberValid = false;
                }
            }
            switch (so) {
                case 1:
                    System.out.print("Please enter your name: ");
                    String name = sc.nextLine();
                    System.out.println("      **Name Add**");
                    StudentName sv = new StudentName(name);
                    dssv.addname(sv);
                    break;
                case 2:
                    System.out.print("* Name List:\n");
                    dssv.inStudent();
                    break;
                case 3:
                    System.out.print("Please enter keyword: ");
                    String Name = sc.nextLine();
                    System.out.println("Search results: ");
                    dssv.searchStudent(Name);
                    break;
                case 4:
                    System.out.println("Thank you!");
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose options above!");
                    break;
            }

        } while (flag);

    }

}
