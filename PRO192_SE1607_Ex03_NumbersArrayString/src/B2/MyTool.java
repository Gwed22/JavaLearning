package B2;

import java.util.Scanner;

/**
 * @author Do Huynh Anh Vu Coder
 * @version 1
 * @Date 8/02/2022
 */
public class MyTool {

    private int[] array;

    /**
     * For user to enter array elements
     */
    public MyTool() {
        int n = 0;
        Scanner element = new Scanner(System.in);
        boolean isNumberValid = true;
        do {
            try {
                isNumberValid = true;
                System.out.print("Enter the number elements of array : ");
                n = Integer.parseInt(element.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an positive integer!");
                isNumberValid = false;
            }
        } while (isNumberValid == false);

        array = new int[n];
        System.out.println("Enter elements : ");
        for (int i = 0; i < n; i++) {
            do {
                try {
                    isNumberValid = true;
                    System.out.print("Enter a[" + i + "] = ");
                    array[i] = Integer.parseInt(element.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer!");
                    isNumberValid = false;
                }
            } while (isNumberValid == false);
        }
    }

    /**
     * Method to get max element in array
     */
    public int getMax() {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Method to sort element in array
     */
    public void sort() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * Method to check array sorted or not
     */
    public boolean isSort() {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to display array
     */
    public void display() {
        System.out.println("-------- Array in ascending order --------");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

    /**
     * Call method for process
     */
    public static void main(String[] args) {
        MyTool tool = new MyTool();
        if (tool.isSort() == false) {
            System.out.println("Array is not sorted yet!");
            tool.sort();
            System.out.println("Loading.......Sort Complete!");
            tool.display();
        } else {
            System.out.println("Array is sorted!");
            tool.display();
        }

        System.out.println("Max element is : " + tool.getMax());
    }
}
