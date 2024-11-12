package vending_machine;

public class Slot {
	private int id;
	private int quantity;
	private Product product;
		
    public Slot(int id, int quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }
	
    public boolean hasProduct() {
    	return this.quantity > 0;   	
    }
    
    public void decreaseQuantity() {
    	if (hasProduct()) {
    		quantity -= 1;
    	}
    }
    
    public Product getProduct() {
    	return this.product;	
    }
    
    public void setProduct(Product product) {
    	this.product = product;
    }
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
}
