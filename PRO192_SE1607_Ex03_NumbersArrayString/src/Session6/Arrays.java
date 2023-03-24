package Session6;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Arrays {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int length = 0;
        Scanner input = new Scanner(System.in);
        boolean isNumberValid = true;
        do {
            try {
                isNumberValid = true;
                System.out.print("Please enter number of elements: ");
                length = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer!");
                isNumberValid = false;
            }
        } while (isNumberValid == false);

        int[] arrayA = new int[length];

        for (int i = 0; i <= length - 1; i++) {
            do {
                try {
                    isNumberValid = true;
                    System.out.print("Enter " + (i + 1) + "'s number: ");
                    arrayA[i] = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer!");
                    isNumberValid = false;
                }
            } while (isNumberValid == false);
        }

        //Show array values
        System.out.println("------------------------");
        System.out.println("\nArray has been entered: ");
        for (int i = 0; i <= length - 1; i++) {
            System.out.print(arrayA[i] + " ");
        }
        //Use the bubble sort
        for (int i = 0; i <= arrayA.length - 2; i++) {
            for (int j = i + 1; j <= arrayA.length - 1; j++) {
                if (arrayA[i] < arrayA[j]) {
                    int tmp = arrayA[i];
                    arrayA[i] = arrayA[j];
                    arrayA[j] = tmp;

                }
            }

        }

        //shows descending array
        System.out.println("\n-------------------------");
        System.out.println("Array in descending order: ");
        for (int i = 0; i < length; i++) {
            System.out.println(arrayA[i] + " ");
        }
        //shows all elements that are divisible by 5
        System.out.println("\n-------------------------");
        System.out.println("Array elements that are divisible by 5: ");
        for (int i = 0; i <= arrayA.length - 1; i++) {
            if (arrayA[i] % 5 == 0) {
                System.out.println(arrayA[i] + " ");
            }
        }
        System.out.println("\n-------------------------");
        System.out.print("Enter the value that you want to find: ");
        int value = input.nextInt();
        int count = 0;
        for (int i = 0; i <= arrayA.length - 1; i++) {
            if (arrayA[i] == value) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("The value " + value + " is not found");

        } else if (count == 1) {
            System.out.println("There are " + count + " elements which value is " + value);
        } else {
            System.out.println("There are " + count + " elements which value is " + value);
        }
    }
}
