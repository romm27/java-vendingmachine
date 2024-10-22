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
    
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void setPrice(int price) {
		this.price = price; // probably will have to multiply by 100
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
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
    
    public float getPrice() {
    	return price/100;
    }
    
    public int getStock() {
    	return stock;
    }
    
    public void decreaseStock() {
    	stock -= 1;
    }
}
