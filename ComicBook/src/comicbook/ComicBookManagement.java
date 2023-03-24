package comicbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class is used to manage
 *
 * @author Group 4 - SE1607
 */
public class ComicBookManagement {

    private String C_FILE;
    private int numberOfBook;
    private LinkedList<ComicBook> ComicBooks;

    /**
     *
     */
    public ComicBookManagement cm;

    /**
     * Created instance for Comic Book managements
     *
     * @param C_FILE
     * @throws ComicBookException
     */
    public ComicBookManagement(String C_FILE) throws ComicBookException {
        if (C_FILE.equals("")) {
            throw new ComicBookException("The URL of comic book data file can't be empty!");
        } else {
            this.C_FILE = C_FILE;
            this.ComicBooks = new LinkedList<>();
            this.numberOfBook = 0;
        }
    }

    /**
     * Load data of comic book for data file and stored it in into ArrayList
     *
     * @throws comicbook.ComicBookException
     * @throws IOException
     */
    public void loadComicBook() throws ComicBookException, IOException {
        File cFile = new File(C_FILE);

        if (!cFile.exists()) {      //Checks is file created
            cFile.createNewFile();  //If not, creates new file
            System.out.print("The data file ComicBooks.txt is not exits. "
                    + "Creating new data file ComicBooks.txt... "
                    + "Done!");
            this.numberOfBook = 0;  //New data file with the number of comic books is 0
        } else {

            //If file is existed so loading this data file
            System.out.print("\nThe data file ComicBooks.txt is found. "
                    + "Data of ComicBooks is loading... ");
            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(C_FILE))) {
                String ID, Title, bookRentalPrice, Author, Volume;
                this.numberOfBook = Integer.parseInt(br.readLine()); //Reads number of books
                for (int i = 0; i < this.numberOfBook; ++i) {
                    //Read book's infomation
                    ID = br.readLine();
                    Title = br.readLine();
                    bookRentalPrice = br.readLine();
                    Author = br.readLine();
                    Volume = br.readLine();
                    //Create new instance of ComicBook and adds to ComicBooks bank
                    this.ComicBooks.add(new ComicBook(Integer.parseInt(ID), Title, Double.parseDouble(bookRentalPrice), Author, Integer.parseInt(Volume)));
                }
            }
            if (this.numberOfBook > 1) {
                System.out.println("Done! [" + this.numberOfBook + "books]");
            } else {
                System.out.println("Done! [" + this.numberOfBook + "book]");
            }
        }
    }

    /**
     * Save comicbooks into arraylist
     *
     * @throws IOException
     */
    public void saveComicBook() throws IOException {

        FileWriter fw = new FileWriter(new File(C_FILE), false); 
            // Initiallize new file to overwrite old file to save
        try {
            System.out.print("\nComic Book is saving into data file comicbooks.txt...");

            fw.append(String.valueOf(this.numberOfBook) + "\n");
            for (int i = 0; i < this.numberOfBook; i++) {      //Loop until no book left to save
                int ID = this.ComicBooks.get(i).getID();
                String Title = this.ComicBooks.get(i).getTitle();
                double bookRentalPrice = this.ComicBooks.get(i).getBookRentalPrice();          // Get attribute value of object
                String Author = this.ComicBooks.get(i).getAuthor();
                int Volume = this.ComicBooks.get(i).getVolume();

                fw.append(String.valueOf(ID) + "\n");
                fw.append(Title + "\n");
                fw.append(String.valueOf(bookRentalPrice) + "\n");       // Write that value into file
                fw.append(Author + "\n");
                fw.append(String.valueOf(Volume) + "\n");

            }
        } finally {
            fw.close();
            if (this.numberOfBook > 1) {
                System.out.println("Done! [" + this.numberOfBook + "books]");
            } else {
                System.out.println("Done! [" + this.numberOfBook + "book]");
            }
        }
    }

    /**
     * Adds comicbooks in bank (arraylist)
     *
     * @param ID
     * @param Title
     * @param bookRentalPrice
     * @param Volume
     * @param Author
     * @return the number of book exist in the array
     * @throws comicbook.ComicBookException
     */
    public int addComicBook(int ID, String Title, double bookRentalPrice, String Author, int Volume) throws ComicBookException {
        this.ComicBooks.add(new ComicBook(ID, Title, bookRentalPrice, Author, Volume));
        return ++this.numberOfBook;
    }

    /**
     * Method to display the element of the book from the array to user
     */
    public void display() {
        System.out.println("+-----+------+----------------------+----------+--------------------+----------+");
        System.out.println("| No. |  ID  |        Title         |   Price  |       Author       |  Volume  |");
        System.out.println("+-----+------+----------------------+----------+--------------------+----------+");
        for (int i = 0; i < this.numberOfBook; i++) {
            int ID = this.ComicBooks.get(i).getID();
            String Title = this.ComicBooks.get(i).getTitle();
            double bookRentalPrice = this.ComicBooks.get(i).getBookRentalPrice();
            String Author = this.ComicBooks.get(i).getAuthor();
            int Volume = this.ComicBooks.get(i).getVolume();

            System.out.printf("|%4d |%5d | %-20s | " + "$" + "%7.2f | %-18s | %8d |\n", i + 1, ID, Title, bookRentalPrice, Author, Volume);
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    /**
     * Finds the Title instance by query
     *
     * @param query
     */
    public void findComicBookByTitle(String query) {
        LinkedList<ComicBook> newList = new LinkedList<>();
        for (int i = 0; i < ComicBooks.size(); i++) {
            //for (ComicBook item : listComicBooks) {
            if (ComicBooks.get(i).getTitle().toLowerCase().contains(query.toLowerCase())) {
                newList.add(ComicBooks.get(i));
            }
        }
        if (newList.isEmpty()) {
            System.out.println("Not found!");
        } else {
            this.show(newList);

        }
    }

    /**
     * Show arraylist
     *
     * @param newList
     */
    public void show(LinkedList<ComicBook> newList) {

        System.out.println("+-----+------+----------------------+----------+--------------------+----------+");
        System.out.println("| No. |  ID  |        Title         |   Price  |       Author       |  Volume  |");
        System.out.println("+-----+------+----------------------+----------+--------------------+----------+");

        for (int i = 0; i < newList.size(); i++) {
            ComicBook comicbook = newList.get(i);
            int number = i + 1;
            System.out.printf("|%4d |%5d | %-20s |   $%.2f  | %-18s | %8d |\n", number, comicbook.getID(), comicbook.getTitle(), comicbook.getBookRentalPrice(), comicbook.getAuthor(), comicbook.getVolume());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    /**
     *
     * @param ID
     * @return
     */
    public boolean isIdExist(int ID) {
        for (ComicBook item : ComicBooks) {
            if (item.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @throws InterruptedException
     */
    public void updateComicBookPrice() throws InterruptedException {
        int id = -1;
        boolean isActive = false; //Allow the function to execute again or not
        Scanner sc = new Scanner(System.in); //Creates a scanner
        do {
            try {
                isActive = false;
                System.out.print("Please enter the ID of the book you want to change price: ");
                id = Integer.parseInt(sc.nextLine());
                if (id <= 0) {
                    System.out.println("Error: ID must be an positive integer number!");
                    isActive = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ID must be an positive integer number!");
                isActive = true;
            }
        } while (isActive);
        if (!isIdExist(id)) {
            System.out.println("Error: The ID does not exist!");
        } else {
            ComicBook cb = new ComicBook();
            for (ComicBook item : ComicBooks) {
                if (item.getID() == id) {
                    cb = item;
                }
            }

            boolean isValid = false; //Allow the function to execute again or not
            do {
                try {
                    isValid = false;
                    System.out.print("Please enter new price of the book: ");
                    double newPrice = Double.parseDouble(sc.nextLine());
                    if (newPrice <= 0) {
                        System.out.println("The new price must be a positive number!");
                        isValid = true;
                    } else {
                        cb.setBookRentalPrice(newPrice);
                        System.out.println("Price has been updated!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The new price must be a positive number!");
                    isValid = true;
                }
            } while (isValid);
        }
    }

    /**
     *
     * @param Volume
     * @param Title
     * @param Author
     * @return
     */
    public boolean isVolumeValid(int Volume, String Title, String Author) {
        for (ComicBook item : ComicBooks) {
            if (item.getVolume() == Volume && item.getTitle().toLowerCase().equals(Title.toLowerCase()) == true && item.getAuthor().toLowerCase().equals(Author.toLowerCase()) == true) {
                return true;
            }
        }
        return false;
    }
}
