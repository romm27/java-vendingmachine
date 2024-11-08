package vending_machine;

import java.util.ArrayList;

public class VendingMachine {
	public static  ArrayList<Product> availableProducts = new ArrayList<Product>();
	
	public boolean sell(Product product, double payment) throws NoChangeException, ProductUnavailableException, InsufficientPaymentException {
		if (!product.isAvailable()) {
            throw new ProductUnavailableException("O produto " + product.getName() + " está indisponível.");
        }

        double productPrice = product.getPrice();
        
        if (payment < productPrice) {
            throw new InsufficientPaymentException("Pagamento insuficiente para o produto " + product.getName() + ".");
        }

        double changeAmount = payment - productPrice;
        
        ArrayList<Currency> change = Currency.getChange(changeAmount);

        if (change == null) {
        	throw new NoChangeException("Máquina com estoque insuficiente de dinheiro para finalizar a venda.");
        }

        product.decreaseStock();

        return true;
    }
	
	

}
