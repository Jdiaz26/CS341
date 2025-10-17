package diaz;

public class SalesItem {
	private String name;
	private double price;
	private int quantity;
	
	//Constructor
	public SalesItem (String n, double p, int q) {
		name = n;
		price = p;
		quantity = q;
	}
	
	//Getters
	//Gets total price of item based on quantity
	public double getTotal() {
		return price * quantity;
	}
	
	//toString
	public String toString() {
		return name + " $" + price + " x " + quantity + " = $ " + getTotal();
	}
	
	

}
