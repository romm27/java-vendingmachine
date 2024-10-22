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
    
    public boolean isAvailable() {
    	return this.stock > 0;   	
    }
    
    public String getName() {
    	return name;
    }
    
    public String getBrand() {
    	return brand;
    }
    
    public int getStock() {
    	return stock;
    }
    
    public float getPrice() {
    	return price/100;
    }
}
