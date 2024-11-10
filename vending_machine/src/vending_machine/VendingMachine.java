package vending_machine;

import java.util.ArrayList;

public class VendingMachine {
	
	public static  ArrayList<Product> availableProducts = new ArrayList<Product>();
	public static  ArrayList<Slot> slots = new ArrayList<Slot>();
	private CashRegister cashRegister = new CashRegister();
	
    static {
        slots.add(new Slot(1, 8, Product.getProducts().get(0)));
        slots.add(new Slot(2, 8, Product.getProducts().get(1)));
        slots.add(new Slot(3, 8, Product.getProducts().get(2)));
        slots.add(new Slot(4, 8, Product.getProducts().get(3)));
        slots.add(new Slot(5, 8, Product.getProducts().get(4)));
    }

	public void validateSale(Product product, int payment) throws NoChangeException, ProductUnavailableException, InsufficientPaymentException {
		if (!product.isAvailable()) {
            throw new ProductUnavailableException();
        }

        int productPrice = product.getPrice();
        if (payment < productPrice) {
            throw new InsufficientPaymentException();
        }

        int changeAmount = payment - productPrice;
        cashRegister.calculateCurrency(changeAmount);  
    }
	
	public void sell(Product product, int payment) throws NoChangeException, ProductUnavailableException, InsufficientPaymentException {
		this.validateSale(product, payment);

		Sale sale = new Sale(product);
		sale.canWriteToFile(sale);
		
		product.decreaseStock();
	}
	
    public ArrayList<Slot> getSlots() {
        return slots;
    }
}
