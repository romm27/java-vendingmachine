package vending_machine;

import java.util.ArrayList;
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
		
		int slotQuantity = vendingMachine.getSlots().size() + 1;
		
		welcomeMsg();
		
		boolean menu = true;

		while (menu) {
			generateMenu(vendingMachine);

			try {
				int option = scanner.nextInt();

				if (option >= 1 && option < slotQuantity) {

					Slot selectedSlot = vendingMachine.getSlots().get(option - 1);

					if (!selectedSlot.hasProduct()) {
						emptySlotMsg();
						continue;
					}
				
					PaymentMethods paymentMethod;
					
					generatePaymentMethodsMenu();
					
					int paymentMethodOption = scanner.nextInt();

					switch (paymentMethodOption) {
					case 1 -> paymentMethod = PaymentMethods.cash;
					case 2 -> paymentMethod = PaymentMethods.card;
					case 3 -> {
						cancelMsg();
						continue;
					}

					default -> {
						invalidPaymentOptionMsg();
						continue;
						}
					}
					
					int value = (int) (selectedSlot.getProduct().getPrice());

					if (paymentMethodOption == 1) {
						
						insertMoneyMsg();
						
						int payment = scanner.nextInt() * 100;

						ArrayList<Currency> change = vendingMachine.sell(selectedSlot, payment, paymentMethod);
						for (Currency changeBill : change) {
							System.out.println(changeBill.getQuantity() + " x " + changeBill.getName());
						}
						successCashPaymentMsg();
						removeProductFromMachineMsg(selectedSlot.getProduct());

					} else if (paymentMethodOption == 2) {						
						vendingMachine.sell(selectedSlot, value, paymentMethod);
						successCardPaymentMsg();
						removeProductFromMachineMsg(selectedSlot.getProduct());

					} else {						
						errorOnPaymentMsg();
					}

				} else if (option == 0) {					
					goodByeMsg();
					menu = false;

				} else {
					invalidOptionMsg();
				}

			} catch (InputMismatchException e) {
				invalidOptionMsg();
				scanner.next();

			} catch (NoSuchElementException e) {
				goodByeMsg();
				break;
			
			} catch (InsufficientPaymentException e) {
				insuficientPaymentMsg();
				continue;
			
			} catch (ProductUnavailableException e) {
				productUnavailableMsg();
				continue;
				
			} catch (NoChangeException e) {
				noChangetMsg();
				continue;
				
			} catch (PaymentCannotBeProcessedException e) {
				e.printStackTrace();
			}	
		}
		
		scanner.close();
	}
	
	public static void generateMenu(VendingMachine vendingMachine) {
        System.out.println("\n|------------------------------------ MENU ------------------------------------|\n");
        ArrayList<Slot> slots = vendingMachine.getSlots();
        int i = 1;
        for (Slot slot : slots) {
            if(slot.getProduct() != null) {
            	System.out.printf("[ %d ]\t%s (%s) - R$ %s\n", i, capitalizeFirstLetter(slot.getProduct().getName()), slot.getProduct().getBrand(), slot.getProduct().formatToCurrency());
            }
            i++;
        }
        
        System.out.printf("[ %d ]\tSair\n", 0);
        System.out.print("\nDigite a opção desejada: ");
	}
	
	public static String capitalizeFirstLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
	
	public static void goodByeMsg() {
		System.out.print("Obrigado por usar a máquina de vendas! Volte sempre!");
	}
	
	public static void welcomeMsg() {
		System.out.print("Boas vindas à Máquina de Vendas!\n");
	}
	
	public static void invalidOptionMsg() {
		System.out.print("Apenas números são permitidos. Tente novamente.");
	}
	
	public static void cancelMsg() {
		System.out.println("Compra cancelada.");
	}
	
	public static void removeProductFromMachineMsg(Product product) {
		System.out.printf("Retire o produto %s da máquina.\n", product.getName());
	}
	
	public static void insertMoneyMsg() {
		System.out.print("Digite o valor que irá inserir na máquina em reais: "); 
	}
	
    public static void emptySlotMsg() {
    	System.out.print("Seção vazia. Escolha outro produto.");
    }
    
	public static void generatePaymentMethodsMenu() {
		System.out.println("Escolha o método de pagamento:\n[ 1 ] Dinheiro\n[ 2 ] Cartão\n[ 3 ] Cancelar");
	}
	
	public static void errorOnPaymentMsg() {
		System.out.println("O pagamento não foi processado. Tente novamente.");
	}
	
	public static void insuficientPaymentMsg() {
		System.out.println("O valor inserido é insuficiente. Compra cancelada. Por favor retire o seu dinheiro do compartimento.");
	}

	public static void invalidPaymentOptionMsg() {
		System.out.println("Opção de pagamento desconhecida. Tente novamente.");
	}
	
	public static void productUnavailableMsg() {
		System.out.println("Produto fora de estoque. Por favor escolha outro produto.");
	}
	
	public static void noChangetMsg() {
		System.out.println("Máquina com troco insuficiente. Escolha outro produto, ou insira uma quantidade de dinheiro menor.");
	}
	
	public static void successCashPaymentMsg() {
		System.out.println("Pagamento em dinheiro realizado com sucesso!");
	}
	
	public static void successCardPaymentMsg() {
		System.out.println("Pagamento realizado com sucesso!");
	}
	
}
