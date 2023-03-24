/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook;

/**
 *
 * @author Group 4 - SE1607
 */
public class ComicBook {

    private int ID;
    private String Title;
    private double bookRentalPrice;
    private String Author;
    private int Volume;

    /**
     *
     * @param ID
     * @param Title
     * @param bookRentalPrice
     * @param Author
     * @param Volume
     * @throws comicbook.ComicBookException
     */
    public ComicBook(int ID, String Title, double bookRentalPrice, String Author, int Volume) throws ComicBookException {
        this.setID(ID);
        this.setTitle(Title);
        this.setBookRentalPrice(bookRentalPrice);
        this.setAuthor(Author);
        this.setVolume(Volume);
    }

    ComicBook() {
    }

    /**
     * Gets the id of books
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets id for books
     *
     * @param ID
     * @throws ComicBookException
     */
    public void setID(int ID) throws ComicBookException {
        if (ID < 0) {
            throw new ComicBookException("The id of Comic Book must be a positve number!");
        } else {
            this.ID = ID;
        }
    }

    /**
     * Gets the Title of books
     *
     * @return
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Sets the Title for books
     *
     * @param Title
     * @throws ComicBookException
     */
    public void setTitle(String Title) throws ComicBookException {
        if (Title.equals("")) {
            throw new ComicBookException("The title of the book can't be empty!");
        }
        this.Title = Title;
    }

    /**
     * Gets the bookRentalPrice of books
     *
     * @return
     */
    public double getBookRentalPrice() {
        return bookRentalPrice;
    }

    /**
     * Sets bookRentalPrice for books
     *
     * @param bookRentalPrice
     */
    public void setBookRentalPrice(double bookRentalPrice)  {
        
        this.bookRentalPrice = bookRentalPrice;
    }

    /**
     * Gets the Author of books
     *
     * @return
     */
    public String getAuthor() {
        return Author;
    }

    /**
     * Sets Author for book
     *
     * @param Author
     * @throws ComicBookException
     */
    public void setAuthor(String Author) throws ComicBookException {
        if (Author.equals("")) {
            throw new ComicBookException("Author of the book can't be empty!");
        }
        this.Author = Author;
    }

    /**
     * Gets volume of books
     *
     * @return
     */
    public int getVolume() {
        return Volume;
    }

    /**
     * Sets volume for books
     *
     * @param Volume
     * @throws ComicBookException
     */
    public void setVolume(int Volume) throws ComicBookException {
        if (Volume <= 0) {
            throw new ComicBookException("The volume must be a positive integer!");
        }
        this.Volume = Volume;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Display value
     */
    public void display() {
        System.out.printf("| %3d | %-18s | $%6.2f | %-18s | %6d |\n", ID, Title, bookRentalPrice, Author, Volume);
    }

}
