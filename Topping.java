/*
    Kenny Nguyen
    CPSC 1060: Pizza Part 5
    Section 3
    04-25-23
*/

public class Topping {
    private String name;
    private double costPerInch;

    // Toppings constructor
    public Topping(String name) {
        this.name = name;

        if (this.name.equalsIgnoreCase("pepperoni")) {
            this.costPerInch = 0.047;
        }
        else if (this.name.equalsIgnoreCase("mushroom")) {
            this.costPerInch = 0.005;
        }
        else if (this.name.equalsIgnoreCase("chicken")) {
            this.costPerInch = 0.01;
        }
        else if (this.name.equalsIgnoreCase("ham")) {
            this.costPerInch = 0.03;
        }
        else if (this.name.equalsIgnoreCase("pineapple")) {
            this.costPerInch = 0.04;
        }
        else if (this.name.equalsIgnoreCase("sausage")) {
            this.costPerInch = 0.052;
        }
        else if (this.name.equalsIgnoreCase("basil")) {
            this.costPerInch = 0.032;
        }
        else if (this.name.equalsIgnoreCase("olive")) {
            this.costPerInch = 0.002;
        }
    }

    // Define get methods: getName(), getCostPerInch()
    public String getName() {
        return this.name;
    }

    public double getCostPerInch() {
        return this.costPerInch;
    }
    
}
