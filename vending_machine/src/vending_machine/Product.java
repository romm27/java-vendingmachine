package vending_machine;

import java.text.DecimalFormat;

public class Product {
    private int id;
    private String name;
	private String brand;
	public enum ProductType { food, beverage }
	private ProductType productType; 
	private int price;
	
    public Product(int id, String name, String brand, ProductType productType, int price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.productType = productType;
        this.price = price;
    }

	public void setName(String name) {
		this.name = name;
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
	
    public String getName() {
    	return name;
    }
    
    public String getBrand() {
    	return brand;
    }

    public int getPrice() {
    	return price;
    }

    public int getId() {
        return id;
    }
    
    public ProductType getProductType() {
        return productType;
    }
    
    public String formatToCurrency() {
        double value = this.price / 100.0;
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        
        return decimalFormat.format(value);
    }

}
