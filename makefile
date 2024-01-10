default: Pizza.java Pizzeria.java Topping.java Receipt.java Customer.java
	javac Pizza.java Pizzeria.java Topping.java Receipt.java Customer.java

run: Pizza.class Pizzeria.class Topping.class Receipt.class Customer.class
	java Pizzeria

clean:
	rm -f *.class
