package fish;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to manage
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class FishManagement {

    private String C_FILE;
    private int numberOfFish;
    private ArrayList<Fish> fList;

    /**
     *
     */
    public FishManagement fm;

    /**
     * Created instance for fish managements
     *
     * @param C_FILE
     * @throws FishException
     */
    public FishManagement(String C_FILE) throws FishException {
        if (C_FILE.equals("")) {
            throw new FishException("The URL of fish data file can't be empty!");
        } else {
            this.C_FILE = C_FILE;
            this.fList = new ArrayList<Fish>();
            this.numberOfFish = 0;
        }
    }

    /**
     * Load data of fish for data file and stored it in into ArrayList
     *
     * @throws fish.FishException
     * @throws IOException
     */
    public void loadFish() throws FishException, IOException {
        File cFile = new File(C_FILE);

        if (!cFile.exists()) {      //Checks is file created
            cFile.createNewFile();  //If not, creates new file
            System.out.print("The data file Fishes.txt is not exits. "
                    + "Creating new data file Fishes.txt... "
                    + "Done!");
            this.numberOfFish = 0;  //New data file with the number of comic books is 0
        } else {
            //If file is existed so loading this data file
            System.out.print("\nThe data file Fishes.txt is found. "
                    + "Data of Fishes is loading... ");
            //Loads text file into buffer
            BufferedReader br = new BufferedReader(new FileReader(C_FILE));
            try {
                String ID, familyName, importPrice, sellingPrice, origin;
                this.numberOfFish = Integer.parseInt(br.readLine()); //Reads number of fishes
                for (int i = 0; i < this.numberOfFish; ++i) {
                    //Read book's infomation
                    ID = br.readLine();
                    familyName = br.readLine();
                    importPrice = br.readLine();
                    sellingPrice = br.readLine();
                    origin = br.readLine();
                    //Create new instance of ComicBook and adds to ComicBooks bank
                    this.fList.add(new Fish(Integer.parseInt(ID), familyName, Double.parseDouble(importPrice), Double.parseDouble(sellingPrice), origin));
                }
            } finally {
                br.close();
            }
            System.out.println("Done! [" + this.numberOfFish + " Fishes]");
        }
    }

    /**
     * Save Fishes into File
     *
     * @throws IOException
     */
    public void saveFish() throws IOException {

        FileWriter fw = new FileWriter(new File(C_FILE), false);

        try {
            System.out.print("\nFishes is saving into data file Fishes.txt...");

            fw.append(String.valueOf(this.numberOfFish) + "\n");
            for (int i = 0; i < this.numberOfFish; i++) {
                int ID = this.fList.get(i).getID();
                String familyName = this.fList.get(i).getFamilyName();
                double importPrice = this.fList.get(i).getImportPrice();
                double sellingPrice = this.fList.get(i).getSellingPrice();
                String origin = this.fList.get(i).getOrigin();
                // Write value to the file
                fw.append(String.valueOf(ID) + "\n");
                fw.append(familyName + "\n");
                fw.append(String.valueOf(importPrice) + "\n");
                fw.append(String.valueOf(sellingPrice) + "\n");
                fw.append(origin + "\n");

            }
        } finally {
            fw.close();
            System.out.println("Done! [" + this.numberOfFish + " fishes]");
        }
    }

    /**
     * Adds fishes in bank (array list)
     *
     * @param ID
     * @param familyName
     * @param importPrice
     * @param sellingPrice
     * @param origin
     * @return the number of fishes exist in the array
     * @throws fish.FishException
     */
    public int addFish(int ID, String familyName, double importPrice, double sellingPrice, String origin) throws FishException {
        this.fList.add(new Fish(ID, familyName, importPrice, sellingPrice, origin));
        return ++this.numberOfFish;
    }

    /**
     * Method to display the element of the fish from the array to user
     */
    public void display() {
        System.out.println("+-----+----+-----------------+--------------+---------------+----------------+");
        System.out.println("| No. | ID |   Family Name   | Import Price | Selling Price |     Origin     |");
        System.out.println("+-----+----+-----------------+--------------+---------------+----------------+");
        for (int i = 0; i < this.numberOfFish; i++) {
            int ID = this.fList.get(i).getID();
            String familyName = this.fList.get(i).getFamilyName();
            double importPrice = this.fList.get(i).getImportPrice();
            double sellingPrice = this.fList.get(i).getSellingPrice();
            String origin = this.fList.get(i).getOrigin();

            System.out.printf("|%4d |%3d | %-15s | $%11.2f | $%12.2f | %-15s|\n", i + 1, ID, familyName, importPrice, sellingPrice, origin);
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Finds the family name instance by query
     *
     * @param query
     */
    public void findFishbyFN(String query) {
        ArrayList<Fish> newList = new ArrayList<>();
        for (int i = 0; i < fList.size(); i++) {
            //for (ComicBook item : listComicBooks) {
            if (fList.get(i).getFamilyName().toLowerCase().contains(query.toLowerCase())) {
                newList.add(fList.get(i));
            }
        }
        if (newList.isEmpty()) {
            System.out.println("Not found!");
        } else {
            //show if found
            this.show(newList);

        }
    }

    /**
     * Finds the fish by max and min price
     *
     * @param minimumPrice
     * @param maximumPrice
     */
    public void findFishbyPrice(double minimumPrice, double maximumPrice) {
        ArrayList<Fish> newList = new ArrayList<>();
        for (int i = 0; i < fList.size(); i++) {
            //for (ComicBook item : listComicBooks) {
            if (fList.get(i).getSellingPrice() < maximumPrice && minimumPrice < fList.get(i).getSellingPrice()) {
                newList.add(fList.get(i));
            }
        }
        if (newList.isEmpty()) {
            System.out.println("Not found!");
        } else {
            this.show(newList);

        }
    }

    /**
     * Show array list
     *
     * @param newList
     */
    public void show(ArrayList<Fish> newList) {

        System.out.println("+-----+----+-----------------+--------------+---------------+----------------+");
        System.out.println("| No. | ID |   Family Name   | Import Price | Selling Price |     Origin     |");
        System.out.println("+-----+----+-----------------+--------------+---------------+----------------+");

        for (int i = 0; i < newList.size(); i++) {
            Fish Fish = newList.get(i);
            System.out.printf("|%4d |%3d | %-15s | $%11.2f | $%12.2f | %-15s|\n", i + 1, Fish.getID(), Fish.getFamilyName(), Fish.getImportPrice(), Fish.getSellingPrice(), Fish.getOrigin());
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     *
     * @param ID
     * @return
     */
    public boolean isIdExist(int ID) {
        for (Fish item : fList) {
            if (item.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update price of the fish with the id user enter
     * @throws InterruptedException
     */
    public void updateFishPrice() throws InterruptedException {
        int id = -1;
        boolean isActive = false; //Allow the function to execute again or not
        Scanner sc = new Scanner(System.in); //Creates a scanner
        do {
            try {
                isActive = false;
                System.out.print("Please enter the ID of the fish you want to change price: ");
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
            Fish f = new Fish();
            for (Fish item : fList) {
                if (item.getID() == id) {
                    f = item;
                }
            }

            boolean isValid = false; //Allow the function to execute again or not
            do {
                try {
                    isValid = false;
                    System.out.print("Please enter new price of the fish: ");
                    double newPrice = Double.parseDouble(sc.nextLine());
                    if (newPrice <= 0) {
                        System.out.println("The new price must be a positive number!");
                        isValid = true;
                    } else {
                        f.setSellingPrice(newPrice);
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
     * @param Check the fish enter is valid or not
     */
    public boolean isFishValid(String familyName, String origin) {
        for (Fish item : fList) {
            if (item.getOrigin().toLowerCase().equals(origin.toLowerCase()) == true && item.getFamilyName().toLowerCase().equals(familyName.toLowerCase()) == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * Show table of fish on promote
     */
    public void promote() {
        ArrayList<Fish> newList = new ArrayList<>();
        for (int i = 0; i < fList.size(); i++) {
            //for (ComicBook item : listComicBooks) {
            if (fList.get(i).getSellingPrice() < fList.get(i).getImportPrice()) {
                newList.add(fList.get(i));
            }
        }
        if (newList.isEmpty()) {
            System.out.println("Not found!");
        } else {
            this.show(newList);

        }
    }
}
