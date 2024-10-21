package vending_machine;

public class Product {
	private String name;
	private String brand;
	private int price;
	private int stock;
	
    public Product(String name, String brand, int price, int stock) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }
    
    public boolean sell() {
    	// Add later check if there is enough cash
    	if (this.stock > 0) {
    		this.stock -= 1;
    		
    		return true;
    	} else { return false; }
    }
    
    
}
