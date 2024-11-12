package vending_machine;

public class Slot {
	private int id;
	private int quantity;
	private Product product;
	private ProductDisplay productDisplay;
		
    public Slot(int id, int quantity, Product product) {
        this.setId(id);
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
    	
    	if(productDisplay != null) {
    		productDisplay.checkForEmptySlot();
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
    
    public ProductDisplay getProductDisplay() {
		return this.productDisplay;
	}

	public void setProductDisplay(ProductDisplay productDisplay) {
		this.productDisplay = productDisplay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
