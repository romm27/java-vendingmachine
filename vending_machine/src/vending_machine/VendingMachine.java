package vending_machine;

import java.util.ArrayList;

public class VendingMachine {
	public static  ArrayList<Product> availableProducts = new ArrayList<Product>();
	
	public boolean sell(Product product, double payment) {
        if (!product.isAvailable()) {
            System.out.println("O produto " + product.getName() + " está indisponível.");
            return false;
        }

        double productPrice = product.getPrice();
        
        if (payment * 100 < productPrice) {
            System.out.println("Pagamento insuficiente para o produto " + product.getName() + ".");
            return false;
        }

        double changeAmount = payment - productPrice;
        
        ArrayList<Currency> change = Currency.getChange(changeAmount);

        if (change == null) {
            System.out.println("Máquina com estoque insuficiente de dinheiro para finalizar a venda.");
            return false;
        }

        product.decreaseStock();

        return true;
    }

}
