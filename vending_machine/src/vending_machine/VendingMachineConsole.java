package vending_machine;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VendingMachineConsole {

	public static void main(String[] args)
			throws NoChangeException, 
				   ProductUnavailableException, 
				   InsufficientPaymentException {
		
		Scanner scanner = new Scanner(System.in);
		VendingMachine vendingMachine = new VendingMachine();
		int productsQuantity = Product.getProducts().size() + 1;
		
		vendingMachine.welcomeMsg();
		
		boolean menu = true;

		while (menu) {
			vendingMachine.generateMenu();

			try {
				int option = scanner.nextInt();

				if (option >= 1 && option < productsQuantity) {

					Product selectedProduct = Product.getProducts().get(option - 1);

					if (!selectedProduct.isAvailable()) {// change to vedingmachineslot
						Product.productUnavailableMsg();
						continue;
					}
				
					PaymentMethods paymentMethod;
					
					PaymentMethods.generatePaymentMethodsMenu();
					
					int paymentMethodOption = scanner.nextInt();

					switch (paymentMethodOption) {
					case 1 -> paymentMethod = new Money();
					case 2 -> paymentMethod = new Card();
					case 3 -> {
						System.out.println("Compra cancelada.");
						continue;
					}

					default -> {
						PaymentMethods.invalidPaymentOptionMsg();
						continue;
						}
					}
					
					int value = (int) (selectedProduct.getPrice() * 100);

					if (paymentMethodOption == 1) {
						
						vendingMachine.insertMoneyMsg();
						
						int payment = scanner.nextInt();

						if (paymentMethod.processPayment(payment)) {

							vendingMachine.sell(selectedProduct, payment);

						} else {
							PaymentMethods.errorOnPaymentMsg();
						}

					} else if (paymentMethodOption == 2) {
						vendingMachine.sell(selectedProduct, value);

					} else {
						PaymentMethods.errorOnPaymentMsg();
					}

				} else if (option == productsQuantity) {
					vendingMachine.goodByeMsg();
					menu = false;

				} else {
					vendingMachine.invalidOptionMsg();
				}

			} catch (InputMismatchException e) {
				vendingMachine.invalidOptionMsg();
				scanner.next();

			} catch (NoSuchElementException e) {
				vendingMachine.goodByeMsg();
				break;
			
			} catch (InsufficientPaymentException e) {
				vendingMachine.goodByeMsg();
				break;
				
			}
				
		}
		
		scanner.close();
	}
}
