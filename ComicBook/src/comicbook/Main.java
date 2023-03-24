package comicbook;

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Group 4 - SE1607
 */
public class Main {

    private static ComicBookManagement cm;

    /**
     *
     * @param args the command line arguments
     * @throws IOException
     * @throws ComicBookException
     */
    public static void main(String[] args) throws IOException, ComicBookException {

        try {
            cm = new ComicBookManagement("test/ComicBooks.txt");
            cm.loadComicBook();

            Scanner cin = new Scanner(System.in);
            int func = 0;
            boolean isValid;

            do {
                //Show menu
                System.out.println("\n        COMIC BOOK RENTAL SHOP        ");
                System.out.println("1. Add new comic book.");
                System.out.println("2. Search book by title.");
                System.out.println("3. Update book rental price.");
                System.out.println("4. Quit.");
                //Gets funcetion that selected by user
                do {
                    isValid = false;
                    try {
                        System.out.print("      Please enter the function: ");
                        func = Integer.parseInt(cin.nextLine());
                        if (func <= 0 || func > 4) {
                            System.out.println("Error: Please choose the function from 1 to 4!");
                            isValid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please choose the function from 1 to 4!");
                        isValid = true;
                    }
                } while (isValid == true);

                String strUserEntered = "";
                int intUserEntered = 0;
                double dbUserEntered = 0.0;
                switch (func) {
                    case 1:
                        System.out.println("----- [ADD NEW COMIC BOOK] -----");
                        int ID = 0;
                        String Title = "";
                        double bookRentalPrice = 0.0;
                        String Author = "";
                        int Volume = 0;
                        ComicBook cb = new ComicBook();

                        isValid = false; //Allow the function to execute again or not
                        //Get the id
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the ID of the book: ");
                                ID = Integer.parseInt(cin.nextLine());
                                if (ID <= 0) {
                                    System.out.println("Error: ID must be a positive integer number!");
                                    isValid = true;
                                } else if (cm.isIdExist(ID)) {
                                    System.out.println("Error: ID has been existed! Please enter another ID!");
                                    isValid = true;
                                } else {
                                    cb.setID(ID);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("ID must be a positive integer number!");
                                isValid = true;
                            }
                        } while (isValid);
                        //Gets the titles of books
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter title of comic book: ");
                                Title = cin.nextLine().trim();
                                if (Title.equals("")) {
                                    System.out.println("Error: Comic book title can't be emty!");
                                    isValid = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Comic book title can't be empty");
                                isValid = true;
                            }
                        } while (isValid);
                        //Gets Price of books
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the price of the book: ");
                                bookRentalPrice = Double.parseDouble(cin.nextLine());
                                if (bookRentalPrice <= 0) {
                                    System.out.println("Book rental price must be a positive number!");
                                    isValid = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Book rental price must be a positive number!");
                                isValid = true;
                            }
                        } while (isValid);
                        //Gets the author 
                        do {
                            System.out.print("Please enter the author of the comic book: ");
                            Author = cin.nextLine().trim();
                            if (Author.equals("")) {
                                System.out.println("Error: The author of the comic book can't be empty!");
                            }
                        } while (Author.equals(""));
                        //gets the volumedo {
                        isValid = false;
                        do {
                            isValid = false;
                            try {
                                System.out.print("Please enter the volume of the book: ");
                                Volume = Integer.parseInt(cin.nextLine());
                                if (Volume <= 0) {
                                    System.out.println("Error: Volume must be a positive integer number!");
                                    isValid = true;
                                } else if (cm.isVolumeValid(Volume, Title, Author)) {
                                    System.out.println("Error: Volume of this comic book has been existed! Please enter another volume!");
                                    isValid = true;
                                } else {
                                    cb.setVolume(Volume);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Volume must be a positive integer number!");
                                isValid = true;
                            }
                        } while (isValid);
                        //Add comicbooks
                        cm.addComicBook(ID, Title, bookRentalPrice, Author, Volume);
                        System.out.println("Your new comic book is created!");
                        cm.display();
                        break;
                    case 2:
                        //Find book with Title user enter
                        do {
                            System.out.print("Please enter the title of the comic you want to find: ");
                            strUserEntered = cin.nextLine().trim();
                            if (strUserEntered.equals("")) {
                                System.out.println("Error: The title of the comic book can't be empty");
                            }
                        } while (strUserEntered.equals(""));
                        cm.findComicBookByTitle(strUserEntered);
                        cin.nextLine();
                        break;
                    case 3:
                        //Change price of books
                        cm.display();
                        cm.updateComicBookPrice();
                        cm.display();
                        cin.nextLine();
                        break;
                    case 4:
                        System.out.println("\n-----------------");
                        System.out.println("Thanks for using our software!\n" + "See you again!");
                        break;
                }
            } while (func != 4);
        } catch (Exception e) {
            System.out.println("Error: You must enter a number!");
        } finally {
            try {
                cm.saveComicBook(); //Save comicBooks
            } catch (IOException e) {
                System.out.println("Exception: Can't save comic books!");
            }
        }
    }
}
