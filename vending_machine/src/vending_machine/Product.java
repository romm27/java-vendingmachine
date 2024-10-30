package vending_machine;

import vending_machine.Currency.CurrencyType;

public class Product {
	private String name;
	private String brand;
	public enum ProductType { food, beverage }
	private ProductType productType; 
	private int price; 
	private int stock; 
	
    public Product(String name, String brand, ProductType productType, int price, int stock) {
        this.name = name;
        this.brand = brand;
        this.productType = productType;
        this.price = price;
        this.stock = stock;
    }
  	
    public boolean isAvailable() {
    	return this.stock > 0;  
    }

    public Product(String name, String brand, int price, int stock) {
        this.setName(name);
        this.brand = brand;
        this.setPrice(price);
        this.stock = stock;
    }
    
    public boolean sell() {
    	if (this.stock > 0) {
    		this.stock -= 1;
    		
    		return true;
    	} else { return false; }
    }
 
    public String getName() {
    	return name;
    }
    
    public String getBrand() {
    	return brand;
    }
    
    public float getPrice() {
    	return (float) price / 100;
    }
    
    public int getStock() {
    	return stock;
    }
    
    public void decreaseStock() {
    	if (stock > 0) {
    	    stock -= 1;
    	}
    }
    
  	public void setName(String name) {
  		this.name = name;
  	}
  	
  	public void setStock(int stock) {
  		this.stock = stock;
  	}
  	
  	public void setPrice(int price) {
  		this.price = price;
  	}
  	
  	public void setBrand(String brand) {
  		this.brand = brand;
  	}
  	
  	public void setType(ProductType productType) {
  		this.productType = productType;
  	}
}