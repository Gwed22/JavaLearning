package labcex2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Group4_SE1607_EX02_Fraction
 * @version 1.0
 */
public class Fraction {

    int numerator;
    int denominator;

    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }


    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }


    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }


    public void setNumerator(int num) {
        this.numerator = num;
    }


    public void setDenominator(int den) {
        this.denominator = den;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     *
     *  Adding two fraction together ;
     *  Return result after adding two fractions
     */
    public Fraction add(Fraction f2) {
        int numerator1 = this.numerator;
        int denominator1 = this.denominator;
        int numerator2 = f2.getNumerator();
        int denominator2 = f2.getDenominator();

        int numerator = numerator1 * denominator2 + numerator2 * denominator1;
        int denominator = denominator1 * denominator2;
        return new Fraction(numerator, denominator);
    }

    /**
     *
     * Adding a integer with a fraction ;
     * Return result after adding a integer with a fraction
     */
    public Fraction add(int num) {
        int bnumerator = this.numerator;
        int bdenominator = this.denominator;

        int numerator = bnumerator + num * denominator;
        int denominator = bdenominator;
        return new Fraction(numerator, denominator);
    }

    /**
     *
     * Sub two fraction ;
     *  Return result after Sub two fraction  
     */
    public Fraction sub(Fraction f2) {
        int numerator1 = this.numerator;
        int denominator1 = this.denominator;
        int numerator2 = f2.getNumerator();
        int denominator2 = f2.getDenominator();

        int numerator = numerator1 * denominator2 - numerator2 * denominator1;
        int denominator = denominator1 * denominator2;
        return new Fraction(numerator, denominator);
    }

    /**
     *
     *  Sub a integer from a fraction ;
     *  Return result after sub integer from a fraction
     */
    public Fraction sub(int num) {
        int bnumerator = this.numerator;
        int bdenominator = this.denominator;

        int numerator = bnumerator - num * denominator;
        int denominator = bdenominator;
        return new Fraction(numerator, denominator);
    }

    /**
     *
     *  Mul two fraction together ;
     *  Return result after mul two fraction
     */
    public Fraction mul(Fraction f2) {
        int numerator1 = this.numerator;
        int denominator1 = this.denominator;
        int numerator2 = f2.getNumerator();
        int denominator2 = f2.getDenominator();

        int numerator = numerator1 * numerator2;
        int denominator = denominator1 * denominator2;
        return new Fraction(numerator, denominator);
    }

    /**
     *
     *  Mul a integer and a fraction ;
     *  Return result after mul a integer and a fraction
     */
    public Fraction mul(int num) {
        int bnumerator = this.numerator;
        int bdenominator = this.denominator;

        int numerator = bnumerator * num;
        int denominator = bdenominator;
        return new Fraction(numerator, denominator);
    }

    /**
     *
     * Div two fraction ;
     * Return result after div two fraction
     */
    public Fraction div(Fraction f2) {
        int numerator1 = this.numerator;
        int denominator1 = this.denominator;
        int numerator2 = f2.getNumerator();
        int denominator2 = f2.getDenominator();

        int numerator = numerator1 * denominator2;
        int denominator = denominator1 * numerator1;
        return new Fraction(numerator, denominator);
    }

    /**
     *
     * Div a fraction with a integer ;
     * Return result after div 
     */
    public Fraction div(int num) {
        int bnumerator = this.numerator;
        int bdenominator = this.denominator;

        int numerator = bnumerator * 1;
        int denominator = bdenominator * num;
        return new Fraction(numerator, denominator);
    }

    /**
     *
     * Main method for user to input the fraction and check if the input is validable
     * Print out all the output 
     */
    public static void main(String[] args) {

        int fraction_num1 = 0;
        int fraction_denom1 = 0;

        Scanner input = new Scanner(System.in);
        boolean isInputValid = true;
        do {
            try {
                isInputValid = true;
                System.out.println("Please enter the numerator of fraction one: ");
                fraction_num1 = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("The numerator must be a number!");
                isInputValid = false;
                input.nextLine();
            }

        } while (isInputValid == false);

        do {
            try {
                isInputValid = true;
                System.out.println("Please enter the denominator of fraction one: ");
                fraction_denom1 = input.nextInt();
                if (fraction_denom1 == 0) {
                    System.out.println("The denominator can not 0!");
                    isInputValid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("The denominator must be a number!");
                isInputValid = false;
                input.nextLine();
            }

        } while (isInputValid == false);

        Fraction f1 = new Fraction(fraction_num1, fraction_denom1);
        int fraction_num2;
        int fraction_denom2;
        Scanner c = new Scanner(System.in);
        System.out.println("Please enter the numerator of fraction two: ");
        fraction_num2 = c.nextInt();
        do {
            System.out.println("Please enter the denominator of fraction two: ");
            fraction_denom2 = c.nextInt();
            if (fraction_denom2 == 0) {
                System.out.println("The denominator can not 0!");
            }
        } while (fraction_denom2 == 0);
        Fraction f2 = new Fraction(fraction_num2, fraction_denom2);
        int num = 0;
        do {
            try {
                isInputValid = true;
                System.out.println("Please enter any interger number:");
                num = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a integer number!");
                isInputValid = false;
                input.nextLine();
            }

        } while (isInputValid == false);

        Fraction result = f1.add(f2);
        Fraction result2 = result.add(num);
        System.out.printf("---- Adding Fraction ----\n");
        System.out.printf("%s + %s = %s\n", f1.toString(), f2.toString(), result.toString());
        System.out.printf("%s + %s = %s\n\n", result.toString(), num, result2.toString());

        Fraction s = f1.sub(f2);
        Fraction s1 = s.sub(num);
        System.out.printf("---- Substracting Fraction ----\n");
        System.out.printf("%s - %s = %s\n", f1.toString(), f2.toString(), s.toString());
        System.out.printf("%s - %s = %s\n\n", s.toString(), num, s1.toString());
        
        Fraction result1 = f1.mul(f2);
        Fraction result3 = result1.mul(num);
        System.out.printf("---- Multiplying Fraction ----\n");
        System.out.printf("%s * %s = %s\n", f1.toString(), f2.toString(), result1.toString());
        System.out.printf("%s * %s = %s\n\n", result1.toString(), num, result3.toString());

        Fraction r = f1.div(f2);
        Fraction r1 = r.div(num);
        System.out.printf("---- Dividing Fraction ----\n");
        System.out.printf("%s / %s = %s\n", f1.toString(), f2.toString(), r.toString());
        System.out.printf("%s / %s = %s\n\n", r.toString(), num, r1.toString());
    }

}
