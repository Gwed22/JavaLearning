package fish;

/**
 *
 * @author Do Huynh Anh Vu CE171446
 */
public class Fish {

    private int ID;
    private String familyName;
    private double importPrice;
    private double sellingPrice;
    private String origin;

    /**
     *
     * @param ID
     * @param familyName
     * @param importPrice
     * @param sellingPrice
     * @param origin
     * @throws fish.FishException
     */
    public Fish(int ID, String familyName, double importPrice, double sellingPrice, String origin) throws FishException {
        this.setID(ID);
        this.setFamilyName(familyName);
        this.setImportPrice(importPrice);
        this.setSellingPrice(sellingPrice);
        this.setOrigin(origin);
    }

 Fish() {
    }

    /**
     * Gets the id of fish
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets id for fish
     *
     * @param ID
     * @throws FishException
     */
    public void setID(int ID) throws FishException {
        if (ID < 0) {
            throw new FishException("The id of Comic Book must be a positve number!");
        } else {
            this.ID = ID;
        }
    }

    /**
     * Gets the Family name of fish
     *
     * @return
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the Family name of fish
     *
     * @param familyName
     * @throws fish.FishException
     */
    public void setFamilyName(String familyName) throws FishException {
        if (familyName.equals("")) {
            throw new FishException("The title of the book can't be empty!");
        }
        this.familyName = familyName;
    }

    /**
     * Gets the import price of fish
     *
     * @return
     */
    public double getImportPrice() {
        return importPrice;
    }

    /**
     * Sets import price for fish
     *
     * @param importPrice
     */
    public void setImportPrice(double importPrice)  {
        
        this.importPrice = importPrice;
    }
    
    /**
     * Get the selling price of fish
     * @return selling price
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets selling for fish
     *
     * @param sellingPrice
     */
    public void setSellingPrice(double sellingPrice)  {
        
        this.sellingPrice = sellingPrice;
    }

    /**
     * Gets the origin of fish
     *
     * @return
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets origin for fish
     *
     * @param origin
     * @throws fish.FishException
     */
    public void setOrigin(String origin) throws FishException {
        if (origin.equals("")) {
            throw new FishException("Author of the book can't be empty!");
        }
        this.origin = origin;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Display value
     */
    public void display() {
        System.out.printf("| %3d | %-18s | $%6.2f | $%6.2f | %-18s |\n", ID, familyName, importPrice, sellingPrice, origin);
    }

}
