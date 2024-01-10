/*
    Kenny Nguyen
    CPSC 1060: Pizza Part 5
    Section 3
    04-25-23
*/

public class Pizza {
    private int diameter;
    private String[] toppings;
    private String name;
    private String toppingsString;

    final double cheeseCostPerSquareInch = .0272;
    final double sauceCostPerSquareInch = .0316;
    final double doughCostPerSquareInch = .0228;

    // Define constructor
    public Pizza(int diameter, String[] toppings, String name) {
        this.diameter = diameter;
        this.toppings = toppings;
        this.name = name;
    }

    // Define get methods: getDiameter(), getToppings(), getName()
    public int getDiameter() {
        return this.diameter;
    }

    public String[] getToppings() {
        return this.toppings;
    }

    public String getName() {
        return this.name;
    }

    // pizzaArea method
    public double pizzaArea(int diameter) {
        double area = Math.PI * Math.pow((double) diameter / 2.0, 2.0);
        return area;
    }

    // pizzaCost method
    public double pizzaCost() {
        double area = pizzaArea(this.diameter);
        double total = area * (cheeseCostPerSquareInch + sauceCostPerSquareInch + doughCostPerSquareInch);
        for (int i = 0; i < this.toppings.length; i++) {
            Topping userTopping = new Topping(this.toppings[i]);
            total += (area * userTopping.getCostPerInch());
        }
        total = (double) Math.round(total * 100) / 100;
        return total;
    }

    // toString method
    public String toString() {
        // If pizza has no toppings
        if (toppings.length == 0) {
            return "   Name: " + name + "\n" +
        "   Size: " + diameter + "\n" +
        "   Toppings: NONE.\n" + 
        "   Total: $" + String.format("%.2f", pizzaCost());
        }
        // Pizza has toppings
        else {
            // Convert toppings to String
            String topStr = "";
            for (int i = 0; i < toppings.length - 1; i++) {
                topStr = topStr + toppings[i] + ", ";
            }
            topStr = topStr + toppings[toppings.length - 1] + ".";
            return "   Name: " + name + "\n" +
            "   Size: " + diameter + "\n" +
            "   Toppings: " + topStr + "\n" + 
            "   Total: $" + String.format("%.2f", pizzaCost());
        }
    }
        
}
