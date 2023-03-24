package Session6;

import java.util.Scanner;

/**
 * @author Do Huynh Anh Vu Coder
 * @version 1
 * @Date 8/02/2022
 */
public class String01 {

    /**
     * Main include algorithm to process need ( enter text, count number,digit,..)
     */
    public static void main(String[] args) {
        String myString;
        //Requires user to enter any text
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter any text: ");
        myString = input.nextLine();

        System.out.println("Your text is: " + myString);

        System.out.println("Number of character of your text: " + myString.length());

        int countAlpbet = 0;
        for (int i = 0; i <= myString.length() - 1; i++) {
            if (myString.charAt(i) >= 65 && myString.charAt(i) <= 122) {
                countAlpbet++;
            }
        }
        System.out.println("Number of alpabet of the text: " + countAlpbet);

        int countDigit = 0;
        for (int i = 0; i <= myString.length() - 1; i++) {

            if (myString.charAt(i) >= 48 && myString.charAt(i) <= 57) {
                countDigit++;
            }
        }
        System.out.println("Number of digit of the text: " + countDigit);
        //found substring
        String keyword;
        System.out.print("Enter the term that you want to find: ");
        keyword = input.nextLine();

        int index = myString.indexOf(keyword);
        if (index != -1) {
            System.out.println("The term '" + keyword + "' is found at index [" + index + "] in your rext");

        } else {
            System.out.println("The term '" + keyword + "' is not found");
        }

        StringBuilder objStrBuilder = new StringBuilder(myString);

        System.out.println("The reverse text is: " + objStrBuilder.reverse());

        String[] resources = myString.split("\\s+");
        String myReverseString = resources[resources.length - 1];
        for (int i = resources.length - 2; i >= 0; i--) {
            myReverseString += " " + resources[i];
        }
        System.out.println("The reverse text (word reverse): " + myReverseString);
    }

}
