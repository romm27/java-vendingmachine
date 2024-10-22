package vending_machine;

public class Product {
	private String name;
	private String brand;
	private int price;
	private int stock;
	
    public Product(String name, String brand, int price, int stock) {
        this.setName(name);
        this.brand = brand;
        this.setPrice(price);
        this.stock = stock;
    }
    
    
    public boolean sell() {
    	// Add later check if there is enough cash
    	if (this.stock > 0) {
    		this.stock -= 1;
    		
    		return true;
    	} else { return false; }
    }


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}



	}
    
    
}
