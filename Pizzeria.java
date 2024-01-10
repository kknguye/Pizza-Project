/*
    Kenny Nguyen
    CPSC 1060: Pizza Part 5
    Section 3
    04-25-23
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pizzeria {
    public static void main(String[] args) throws IOException {
        // Declare scanner class
        Scanner scnr = new Scanner(System.in);
        Receipt.setScanner(scnr);

        char selection = ' ';
        while (selection != 'c') {
            // Create String ArrayList to store pizzas
            ArrayList<String> pizzaList = new ArrayList<String>();
            
            // Introduce the pizzeria to the user and ask for their name
            System.out.println("Next in line, please!");
            System.out.println("Welcome to Adkins Pizzeria!");
            System.out.println("Can I have your name please?");
            String name = scnr.nextLine();
            System.out.println();

            // Implement selection menu
            System.out.printf("Ok, let's start your order, %s.\n", name);
            System.out.println("Here is what you can do next:");
            System.out.println("MENU");
            System.out.println("a - Add a pizza");
            System.out.println("t - Print the total");
            System.out.println("r - Receipt");
            System.out.println("q - Quit");
            System.out.println("c - Close the store");
            System.out.println("Please make a selection:");
            selection = scnr.nextLine().charAt(0);
            System.out.println();

            // Validate selection
            while (!(selection == 'a' || selection == 't' || selection == 'r' || selection == 'q' || selection == 'c')) {
                System.out.println("Please input a proper selection:");
                selection = scnr.nextLine().charAt(0);
            }

            // Initialize pizza count
            int pizzaCount = 0;

            // Initialize total cost of pizza
            double pizzaTotal = 0;

            Customer customer = new Customer(name);
            Receipt receipt = new Receipt(customer);

            // Loop until quit
            while (selection != 'q') {
                // Set customer object for receipt class
                ArrayList<Pizza> listPizzas = new ArrayList<Pizza>();

                // Selection "a"
                double pizzaCost;
                if (selection == 'a') {
                    pizzaCount++;

                    // Ask for pizza size
                    System.out.printf("What would you like to name pizza number %d?\n", pizzaCount);
                    pizzaList.add(scnr.nextLine());
                    System.out.println();
                    System.out.println("What size would you like " + pizzaList.get(pizzaCount - 1) + " to be?");
                    System.out.println("We can handle pizzas between 8 and 48 inches.");

                    int pizzaSize = 0;
                    boolean isValid = false;
                    while (!isValid) {
                        try {
                            pizzaSize = Integer.parseInt(scnr.nextLine());
                            isValid = true;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Please input a value between 8 and 48.");
                        }
                    }

                    // Validate pizza size
                    while (pizzaSize < 8 || pizzaSize > 48) {
                        System.out.println("Please order a pizza between 8 and 48 inches.");
                        pizzaSize = Integer.parseInt(scnr.nextLine());    
                    }
                    System.out.println();
                    
                    // Ask for number of toppings
                    System.out.println("How many toppings would you like on this pizza?");
                    System.out.println("You can have between 0-8 toppings.");
                    int numToppings = 0;

                    isValid = false;
                    while (!isValid) {
                        try {
                            numToppings = Integer.parseInt(scnr.nextLine());
                            isValid = true;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Please input a value between 0 and 8.");
                        }
                    }
                    
                    // Validate toppings count
                    while (numToppings < 0 || numToppings > 8) {
                        System.out.println("Please choose a number of toppings between 0 and 8.");
                        numToppings = Integer.parseInt(scnr.nextLine());
                    }
                    System.out.println();

                    // Toppings String
                    String toppingStr = "";

                    // Ask which toppings
                    String[] toppingList = new String[numToppings];
                    for (int i = 0; i < numToppings; i++) {
                        System.out.println("What topping would you like?");
                        System.out.println("Your options are: Pepperoni, Mushroom, Chicken, Ham, Pineapple, Sausage, Basil, Olive.");
                        toppingList[i] = scnr.nextLine();
                        // Validate topping choice
                        while (!(toppingList[i].equalsIgnoreCase("pepperoni") || toppingList[i].equalsIgnoreCase("mushroom") || toppingList[i].equalsIgnoreCase("chicken") || toppingList[i].equalsIgnoreCase("ham") || toppingList[i].equalsIgnoreCase("pineapple") || toppingList[i].equalsIgnoreCase("sausage") || toppingList[i].equalsIgnoreCase("basil") || toppingList[i].equalsIgnoreCase("olive"))) {
                            System.out.println("Chosen topping is not valid. Please input a different topping:");
                            toppingList[i] = scnr.nextLine();
                        }
                        System.out.println("Chosen topping " + toppingList[i] + " added.");
                        System.out.println();
                    }

                    // Add pizza to receipt class
                    listPizzas.add(new Pizza(pizzaSize, toppingList, pizzaList.get(pizzaCount - 1)));
                    receipt.addPizza(new Pizza(pizzaSize, toppingList, pizzaList.get(pizzaCount - 1)));

                    // Print cost of pizza
                    // If no topppings were ordered
                    if (numToppings == 0) {
                        System.out.println("You have ordered " + pizzaList.get(pizzaCount - 1) + " with toppings: NONE.");
                    }
                    // If toppings were ordered
                    else {
                        System.out.print("You have ordered " + pizzaList.get(pizzaCount - 1) + " with toppings: ");
                        for (int i = 0; i < numToppings - 1; i++) {
                            System.out.print(toppingList[i] + ", ");
                            toppingStr = toppingStr + toppingList[i] + ", ";
                        }
                        System.out.println(toppingList[numToppings - 1] + ".");
                        toppingStr = toppingStr + toppingList[numToppings - 1] + ".";
                    }

                    // Calculate cost of current pizza    
                    Pizza pizza = new Pizza(pizzaSize, toppingList, pizzaList.get(pizzaCount - 1));
                    System.out.printf("This pizza costs $%.2f.\n", pizza.pizzaCost());
                    System.out.println();

                    // Add pizza cost to total
                    pizzaTotal += pizza.pizzaCost();
                }

                // Selection "t"
                else if (selection == 't') {

                    // Print total
                    System.out.printf("Total $%.2f.\n", pizzaTotal);
                    System.out.println();
                }

                // Selection "r"
                else if (selection == 'r') {
                    // Create file
                    // Get file name from user name
                    String fileName = name.replace(' ', '_') + "Receipt.txt";
                    FileOutputStream fileStream = new FileOutputStream(fileName);
                    PrintWriter outFS = new PrintWriter(fileStream);

                    // If no pizzas were ordered
                    if (pizzaCount == 0) {
                        System.out.println("You have not ordered any pizzas yet.");
                    }
                    else {
                        // Implement customer rewards program
                        System.out.println("Before we give you your receipt, would you like to join our rewards program?");
                        String joinInput = scnr.nextLine();

                        // Validate input to be "yes" or "no"
                        while (!(joinInput.equalsIgnoreCase("yes") || joinInput.equalsIgnoreCase("no"))) {
                            System.out.println("Please input yes or no.");
                            joinInput = scnr.nextLine();
                        }

                        // If user inputs "yes"
                        if (joinInput.equalsIgnoreCase("yes")) {
                            // Ask user for email
                            System.out.println("What is your email?");
                            String email = scnr.nextLine();
                            System.out.println();

                            // Ask user for phone number
                            System.out.println("What is your phone number?");
                            String phoneNum = scnr.nextLine();
                            System.out.println();

                            // Set user rewards member status, default rewards points, email, and phone number
                            boolean rewardsMem = true;
                            customer.setRewardsMember(rewardsMem);
                            customer.setRewardsPoints(20.0);
                            customer.setEmail(email);
                            customer.setPhoneNumber(phoneNum);

                            // Add rewards points and ask user if they would like to spend them
                            customer.addRewardsPoints(pizzaTotal);
                            double totalRewards = customer.getRewardsPoints();
                            System.out.printf("You now have %.2f rewards points.\n", customer.getRewardsPoints());
                            System.out.println();
                            System.out.println("Would you like to spend them?");
                            String spendPoints = scnr.next();

                            // Validate input to be "yes" or "no"
                            while (!(spendPoints.equalsIgnoreCase("yes") || spendPoints.equalsIgnoreCase("no"))) {
                            System.out.println("Please input yes or no.");
                            spendPoints = scnr.next();
                            }
                            System.out.println();

                            // If they want to spend points
                            if (spendPoints.equalsIgnoreCase("yes")) {
                                System.out.println("Ok, we will be using your rewards points today.");

                                // Set total
                                receipt.setTotal(pizzaTotal);

                                // Set number of pizzas total
                                receipt.setNumPizzas(pizzaCount);

                                // Call askTip method to ask and get tip amount
                                receipt.askTip();

                                // Calc rewards used
                                double origPoints = customer.getRewardsPoints();
                                // If grand total is greater than current rewards points, use up all rewards points and set amount used
                                if (receipt.calcGrandTotal() > origPoints) {
                                    receipt.setPointsUsed(origPoints);
                                }
                                // Otherwise, use up rewards points based on total, subtract from current rewards, and set amount used
                                else {
                                customer.useRewardsPoints(receipt.calcGrandTotal());
                                double rewardsUsed = origPoints - customer.getRewardsPoints();
                                receipt.setPointsUsed(rewardsUsed);
                                }

                                // Print receipt to file
                                outFS.println(receipt.printRecipt());

                                // Close print writer
                                outFS.close();

                                // Print out file name
                                System.out.println();
                                System.out.println("Receipt printed to " + fileName);
                                System.out.println();

                                // FIXES: after program breaks from loop, program does not prompt the next user for their name
                                scnr.nextLine();

                                break;
                            }
                            else {
                                rewardsMem = false;
                                customer.setRewardsMember(rewardsMem);
                                // Set total
                                receipt.setTotal(pizzaTotal);

                                // Set number of pizzas total
                                receipt.setNumPizzas(pizzaCount);

                                // Call askTip method to ask and get tip amount
                                receipt.askTip();
                                
                                // Print receipt to file
                                outFS.println(receipt.printRecipt());

                                // Close print writer
                                outFS.close();

                                // Print out file name
                                System.out.println();
                                System.out.println("Receipt printed to " + fileName);
                                System.out.println();

                                // FIXES: after program breaks from loop, program does not prompt the next user for their name
                                scnr.nextLine();

                                break;
                            }
                        }
                        else {
                                // Set total
                                receipt.setTotal(pizzaTotal);

                                // Set number of pizzas total
                                receipt.setNumPizzas(pizzaCount);

                                // Call askTip method to ask and get tip amount
                                receipt.askTip();

                                // Print receipt to file
                                outFS.println(receipt.printRecipt());

                                // Close print writer
                                outFS.close();

                                // Print out file name
                                System.out.println();
                                System.out.println("Receipt printed to " + fileName);
                                System.out.println();

                                // FIXES: after program breaks from loop, program does not prompt the next user for their name
                                scnr.nextLine();

                                break;

                        }
                    } 
                }

                // Selection 'c'
                else if (selection == 'c') {
                    System.out.println("Would you like a detailed review of today's transactions?");
                    String seeReview = scnr.nextLine();
                    System.out.println("Adkins Pizzeria is now closed!");
                    System.exit(0);
                }
            
            

                // Prompt menu after each action
                System.out.println("Here is what you can do next:");
                System.out.println("MENU");
                System.out.println("a - Add a pizza");
                System.out.println("t - Print the total");
                System.out.println("r - Receipt");
                System.out.println("q - Quit");
                System.out.println("c - Close the store");
                System.out.println("Please make a selection:");
                selection = 'b';
                selection = scnr.nextLine().charAt(0);
                System.out.println();

                // Validate selection for end of loop
                while (!(selection == 'a' || selection == 't' || selection == 'r' || selection == 'q' || selection == 'c')) {
                    System.out.println("Please input a proper selection:");
                    selection = scnr.nextLine().charAt(0);
                }
            }
        }
    }
}