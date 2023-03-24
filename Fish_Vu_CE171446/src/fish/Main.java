package fish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class Main {

    private static FishManagement fm;

    /**
     *
     * @param args the command line arguments
     * @throws IOException
     * @throws FishException
     */
    public static void main(String[] args) throws IOException, FishException {

        try {
            fm = new FishManagement("test/Fishes.txt");
            fm.loadFish();

            Scanner cin = new Scanner(System.in);
            int func = 0;
            boolean isValid;
            String strUserEntered = "";

            do {
                //Show menu
                System.out.println("\n        FISHES SELLING SHOP        ");
                System.out.println("1. Add new fish .");
                System.out.println("2. Search fish by family name.");
                System.out.println("3. Search fish by price.");
                System.out.println("4. Update fish selling price.");
                System.out.println("5. Show promotion.");
                System.out.println("6. Quit.");
                //Gets funcetion that selected by user
                do {
                    isValid = false;
                    try {
                        System.out.print("\t Please enter the function: ");
                        func = Integer.parseInt(cin.nextLine());
                        if (func <= 0 || func > 6) {
                            System.out.println("Error: Please choose the function from 1 to 6!");
                            isValid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please choose the function from 1 to 6!");
                        isValid = true;
                    }
                } while (isValid == true);

                switch (func) {
                    case 1:
                        System.out.println("\n----- [ADD NEW FISH] -----");
                        int ID = 0;
                        String familyName = "";
                        double importPrice = 0.0;
                        double sellingPrice = 0.0;
                        String origin = "";
                        Fish f = new Fish();

                        isValid = false; //Allow the function to execute again or not
                        //Get the id of the fish
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the ID of the fish: ");
                                ID = Integer.parseInt(cin.nextLine());
                                if (ID <= 0) {
                                    System.out.println("Error: ID must be a positive integer number!");
                                    isValid = true;
                                } else if (fm.isIdExist(ID)) {
                                    System.out.println("Error: ID has been existed! Please enter another ID!");
                                    isValid = true;
                                } else {
                                    f.setID(ID);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("ID must be a positive integer number!");
                                isValid = true;
                            }
                        } while (isValid);
                        //Gets the family name of the fish 
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter family name of the fish: ");
                                familyName = cin.nextLine().trim();
                                if (familyName.equals("")) {
                                    System.out.println("Error: Fish family name can't be emty!");
                                    isValid = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Fish family name can't be empty");
                                isValid = true;
                            }
                        } while (isValid);
                        //Gets import price of the fish 
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the import price of the fish: ");
                                importPrice = Double.parseDouble(cin.nextLine());
                                if (importPrice <= 0) {
                                    System.out.println("Fish import price must be a positive number!");
                                    isValid = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Fish import price must be a positive number!");
                                isValid = true;
                            }
                        } while (isValid);
                        //Get selling price of the fish
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the selling price of the fish: ");
                                sellingPrice = Double.parseDouble(cin.nextLine());
                                if (sellingPrice <= 0) {
                                    System.out.println("Fish selling price must be a positive number!");
                                    isValid = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Fish selling price must be a positive number!");
                                isValid = true;
                            }
                        } while (isValid);
                        //Gets the origin 
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the origin of the fish: ");
                                origin = cin.nextLine().trim();
                                if (origin.equals("")) {
                                    System.out.println("Error: Origin of the fish can't be empty!");
                                    isValid = true;
                                } else if (fm.isFishValid(familyName, origin)) {
                                    System.out.println("Error: Origin and family name of this fish has been existed! Please enter another fish!");
                                    isValid = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Fish origin can't be empty");
                                isValid = true;
                            }
                        } while (isValid);
                        fm.addFish(ID, familyName, importPrice, sellingPrice, origin);
                        System.out.println("Your new fish has been add!");
                        fm.display();
                        break;

                    case 2:
                        //Find fish with family name user enter
                        do {
                            System.out.print("Please enter the family name of the fish you want to find: ");
                            strUserEntered = cin.nextLine().trim();
                            if (strUserEntered.equals("")) {
                                System.out.println("Error: The family name of the fish can't be empty");
                            }
                        } while (strUserEntered.equals(""));
                        fm.findFishbyFN(strUserEntered);
                        cin.nextLine();
                        break;
                    case 3:
                        //Find the price user enter
                        double maximumPrice = 0.0;
                        double minimumPrice = 0.0;
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the minimum price of the fish: ");
                                minimumPrice = Double.parseDouble(cin.nextLine());
                                if (minimumPrice <= 0) {
                                    System.out.println("Fish minimum price must be a positive number!");
                                    isValid = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Fish minimum price must be a positive number!");
                                isValid = true;
                            }
                        } while (isValid);
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the maximum price of the fish: ");
                                maximumPrice = Double.parseDouble(cin.nextLine());
                                if (maximumPrice <= 0) {
                                    System.out.println("Fish maximum price must be a positive number!");
                                    isValid = true;
                                } else if (maximumPrice < minimumPrice) {
                                    System.out.println("Maximum price must larger than minimum price!");
                                    isValid = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Fish maximum price must be a positive number!");
                                isValid = true;
                            }
                        } while (isValid);
                        fm.findFishbyPrice(minimumPrice, maximumPrice);
                        cin.nextLine();
                        break;
                    case 4:
                        //Change price of the fish
                        fm.display();
                        fm.updateFishPrice();
                        fm.display();
                        cin.nextLine();
                        break;
                    case 5:
                        //Show fish on promote 
                        System.out.println("\n\t\t\t----- [FISH ON SELL!!!] -----");
                        fm.promote();
                        cin.nextLine();
                        break;
                    case 6:
                        //Quit the program
                        System.out.println("\n-----------------");
                        System.out.println("Thanks for using our software!\n" + "See you again!");
                        break;
                    default:
                        System.out.println("Error: The function must be from 1 to 6!");
                }
            } while (func != 6);
        } catch (Exception e) {
            System.out.println("Error: You must enter a number!");
        } finally {
            try {
                fm.saveFish(); //Save fishes
            } catch (IOException e) {
                System.out.println("Exception: Can't save fishes!");
            }
        }
    }
}
