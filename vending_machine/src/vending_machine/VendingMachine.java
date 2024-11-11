package vending_machine;

import java.util.ArrayList;
import vending_machine.Product.ProductType;

public class VendingMachine {
	public ArrayList<Slot> slots = new ArrayList<Slot>();
    private ArrayList<Product> products = new ArrayList<Product>();
	private CashRegister cashRegister = new CashRegister();
	
    public VendingMachine() {
    	//TODO ler os produtos de um CSV, criar o arraylist de slots e passar como parametro do construtor
        products.add(new Product(0, "refrigerante de matte", "Capivara Lysa", ProductType.beverage, 1300));
        products.add(new Product(2, "suco musical", "Filhote de Tim Maia", ProductType.beverage, 490));
        products.add(new Product(4, "cerveja sabor tranquilidade", "Programador tranquilão", ProductType.beverage, 1350));
        products.add(new Product(6, "fuga do laboratório", "Capivara Lysa", ProductType.beverage, 1145));
        products.add(new Product(7, "refrigerante fluorescente", "Dr. Nefarious" , ProductType.beverage, 940));
        
        slots.add(new Slot(1, 8, products.get(0)));
        slots.add(new Slot(2, 8, products.get(1)));
        slots.add(new Slot(3, 8, products.get(2)));
        slots.add(new Slot(4, 8, products.get(3)));
        slots.add(new Slot(5, 8, products.get(4)));
    }
 
	public void validateSale(Slot slot, int payment) throws PaymentCannotBeProcessedException, ProductUnavailableException, InsufficientPaymentException {
		if (!slot.hasProduct()) {
            throw new ProductUnavailableException();
        }

        int productPrice = slot.getProduct().getPrice();
        if (payment < productPrice) {
            throw new InsufficientPaymentException();
        }
        
        int changeAmount = payment - productPrice;
        if (!cashRegister.hasChange(changeAmount)) {
        	throw new PaymentCannotBeProcessedException();
        }
    }
	
	public ArrayList<Currency> sell(Slot slot, int payment, PaymentMethods selectedPaymentMethod)
			throws ProductUnavailableException, InsufficientPaymentException, NoChangeException, PaymentCannotBeProcessedException {
		this.validateSale(slot, payment);
		
		PaymentMethod paymentMethod = selectedPaymentMethod == PaymentMethods.cash ? new Cash() : new Card();
		paymentMethod.processPayment(payment);
		
		int changeAmount = payment - slot.getProduct().getPrice();
		ArrayList<Currency> change = null;
		if (selectedPaymentMethod == PaymentMethods.cash) {
			change = this.cashRegister.withdraw(changeAmount);
		}

		Sale sale = new Sale(slot.getProduct());
		sale.canWriteToFile();
		
		slot.decreaseQuantity();
		
		return change;
	}
	
    public ArrayList<Slot> getSlots() {
        return slots;
    }
}
