/*
    Kenny Nguyen
    CPSC 1060: Pizza Part 5
    Section 3
    04-25-23
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Receipt {
    private ArrayList<Pizza> pizzas;
    private String cusname;
    private Customer customer;
    private boolean isRewardsMember;
    private int tipPercent;
    private double total;
    private double grandTotal;
    private int numPizzas;
    private double pointsUsed;
    
    private final double salesTax = 0.06; 

    // Pass in Scanner class
    public static Scanner scnr;

    public static void setScanner(Scanner s) {
        scnr = s;
    }

    // Define constructor
    public Receipt(Customer customer) {
        this.customer = customer;
        pizzas = new ArrayList<Pizza>();
    }

    // SETTERS AND GETTERS
    // getCusname method
    public String getCusname() {
        return this.cusname;
    }

    // setTotal method
    public void setTotal(double total) {
        this.total = total;
    }
    
    // set how many points are used
    public void setPointsUsed(double pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public void setPizzaArray(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    // Calculate tax method
    public double calcTax() {
        double tax = this.total * salesTax;
        tax = (double) Math.round(tax * 100) / 100;
        return tax;
    }

    // Calculate tip method
    public double calcTip(double tipPercent) {
        tipPercent /= 100;
        double tipTotal = tipPercent * this.total;
        tipTotal = (double) Math.round(tipTotal * 100) / 100;
        return tipTotal;
    }

    // Ask for tip method
    public void askTip() {
        System.out.println("What percent tip would you like to leave?");

        // Validate tip
        boolean isValid = false;
        int tipPercent = 0;
        // while loop with try-catch block to catch InputMismatchException when using scnr.nextInt()
        while (!isValid) {
            try {
                tipPercent = scnr.nextInt();
                if (tipPercent >=0 && tipPercent <= 100) {
                    this.tipPercent = tipPercent;
                    isValid = true;
                }
                else {
                    System.out.println("Please input a valid tip percent between 0 and 100 percent.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please input a value between 0 and 100.");
                scnr.nextLine();
            }
        }
    }

    // Add pizza method for receipt
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    // Set numPizza
    public void setNumPizzas(int numPizzas) {
        this.numPizzas = numPizzas;
    }

    // Calculate grand total
    public double calcGrandTotal() {
        double tax = calcTax();
        double tip = calcTip(tipPercent);
        double grandTotal = total + tax + tip;
        grandTotal = (double) Math.round(grandTotal * 100) / 100;
        return grandTotal;
    }

    // Print receipt method
    public String printRecipt() {
        String fullReceipt = "";
        fullReceipt = fullReceipt + "Receipt for customer: " + this.customer.getName() + "\n";

        // Enhanced for loop for pizzas
        int temp = 1;
        for (Pizza pizza : pizzas) {
            fullReceipt = fullReceipt + "Pizza: " + temp + "\n";
            fullReceipt = fullReceipt + pizza + "\n";
            temp++;
        }
        
        // Round tempTotal
        double tempTotal = (double) Math.round(total * 100) / 100;

        // Print rest of receipt (Subtotal, Tax amount, Tip amount, Grand Total)
        // Print receipt with rewards points used if user is rewards member
        if (this.customer.isRewardsMember()) {
            fullReceipt = fullReceipt + "Subtotal: $" + String.format("%.2f", tempTotal) + "\n" +
            "Tax (6%): $" + String.format("%.2f", calcTax()) + "\n" + 
            "Tip (" + tipPercent + "%): $" + String.format("%.2f", calcTip(tipPercent)) + "\n" +
            "Rewards Points Used: " + this.pointsUsed + "\n" +
            "Total: $" + String.format("%.2f", (calcGrandTotal() - this.pointsUsed)) + "\n";
            return fullReceipt;
        }
        // Print normal receipt if user is NOT rewards member
        else {
            fullReceipt = fullReceipt + "Subtotal: $" + String.format("%.2f", tempTotal) + "\n" +
            "Tax (6%): $" + String.format("%.2f", calcTax()) + "\n" + 
            "Tip (" + tipPercent + "%): $" + String.format("%.2f", calcTip(tipPercent)) + "\n" +
            "Total: $" + String.format("%.2f", (calcGrandTotal() - this.pointsUsed)) + "\n";

            return fullReceipt;
        }
    }
}
