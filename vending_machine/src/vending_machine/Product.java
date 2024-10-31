package vending_machine;

import java.util.ArrayList;
import vending_machine.Product.ProductType;

public class Product {
	private String name;
	private String brand;
	public enum ProductType { food, beverage }
	private ProductType productType; 
	private int price; 
	private int stock; 
    private static ArrayList<Product> products = new ArrayList<>();

    static{
        products.add(new Product("Refrigerante de matte", "Capivara Lysa", ProductType.beverage, 1300, 8));
        products.add(new Product("Salgadinho de crina", "Pato Galinha", ProductType.food, 690, 5));
        products.add(new Product("Salgadinho musical", "Filhote de Tim Maia", ProductType.food, 490, 6));
        products.add(new Product("Batatas rústicas", "Navio spine", ProductType.food, 1175, 7));
        products.add(new Product("Cerveja sabor tranquilidade", "Programador tranquilão", ProductType.beverage, 1350, 9));
        products.add(new Product("Batata antigravitacional", "Hemisfério invertido", ProductType.food, 5040, 4));
        products.add(new Product("Fuga do laboratório", "Capivara Lysa", ProductType.beverage, 1145, 8));
        products.add(new Product("Refrigerante fluorescente", "Dr. Nefarious" , ProductType.beverage, 940, 8));
        products.add(new Product("Cupcake sabor rosa", "Fada dos bolos", ProductType.food, 515, 6));
        products.add(new Product("Pipoca Vacas voadoras", "Fada dos bolos", ProductType.food, 750, 2));
        products.add(new Product("Refrigente amargo sabor derrota e limão", "Glowtf", ProductType.food, 750, 2));
    }

    public Product(String name, String brand, ProductType productType, int price, int stock) {
        this.name = name;
        this.brand = brand;
        this.productType = productType;
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
		this.price = price;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setType(ProductType productType) {
		this.productType = productType;
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
}