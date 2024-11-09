package vending_machine;

import java.util.ArrayList;

public class VendingMachine {
	public static  ArrayList<Product> availableProducts = new ArrayList<Product>();
	
	public void generateMenu() {
        System.out.println("\n|------------------------------------ MENU ------------------------------------|\n");

        int i = 1;
        for (Product product : Product.getProducts()) {
            System.out.printf("[ %d ]\t%s (%s) - R$ %.2f\n", i, product.getName(), product.getBrand(), product.getPrice());
            i++;
        }
        System.out.printf("[ %d ]\tSair\n", i);
        System.out.print("\nDigite a opção desejada: ");
	}
	
	public void goodByeMsg() {
		System.out.println("Obrigado por usar a máquina de vendas! Volte sempre!");
	}
	
	public void welcomeMsg() {
		System.out.println("Boas vindas à Máquina de Vendas!");
	}
	
	public void invalidOptionMsg() {
		System.out.println("Apenas números são permitidos. Tente novamente.");
	}
	
	public void removeProductFromMachineMsg(Product product) {
		System.out.printf("Retire o produto %s da máquina.\n", product.getName());
	}
	
	public void insertMoneyMsg() {
		System.out.print("Digite o valor que irá inserir na máquina em reais: "); 
	}
	
	public boolean canSell(Product product, double payment) throws NoChangeException, ProductUnavailableException, InsufficientPaymentException {
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
	
	public void sell(Product product, double payment) throws NoChangeException, ProductUnavailableException, InsufficientPaymentException {
		if (this.canSell(product, payment) == true) {
			
			Sale sale = new Sale(product);

			sale.canWriteToFile(sale);
			
			this.removeProductFromMachineMsg(product);
		}
	}
}
