package vending_machine;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VendingMachineConsole {

    public static void main(String[] args) throws NoChangeException, ProductUnavailableException, InsufficientPaymentException {
    	System.out.println("Boas vindas à máquina de vendas!");
    	
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();
        int productsQuantity = Product.getProducts().size() + 1;
        boolean menu = true;

        while (menu) {
        	
        	vendingMachine.generateMenu();
            
            try {
                int option = scanner.nextInt();

                if (option >= 1 && option < productsQuantity) {
                
                    Product selectedProduct = Product.getProducts().get(option - 1);

                    if (!selectedProduct.isAvailable()) {
                        Product.productUnavailableMsg();
                        continue;
                    }
                    //////implement PaymentMethod generatePaymentMethodsMenu()
                    System.out.println("Escolha o método de pagamento:\n[ 1 ] Dinheiro\n[ 2 ] Cartão\n[ 3 ] Cancelar");
                    int paymentMethodOption = scanner.nextInt();
                    PaymentMethods paymentMethod;

                    switch (paymentMethodOption) {
                        case 1 -> paymentMethod = new Money();
                        case 2 -> paymentMethod = new Card();
                        case 3 -> {
                            System.out.println("Compra cancelada.");
                            continue;
                        }
                        
                        default -> {
                            System.out.println("Opção de pagamento inválida. Tente novamente.");
                            continue;
                        }
                    }
                    ///////

                    int value = (int) (selectedProduct.getPrice() * 100); 
                    
                    if (paymentMethodOption == 1) {
                        System.out.print("Digite o valor que irá inserir na máquina em reais: "); // convert/ verify int vs double
                        int payment = scanner.nextInt(); 
                        
                        if (paymentMethod.processPayment(payment)) {
                        	
                            vendingMachine.sell(selectedProduct, payment); 
                            
                            System.out.printf("Retire o produto %s da máquina.\n", selectedProduct.getName());
                            
                            Sale sale = new Sale(selectedProduct);
                            
                            try {
                            	sale.writeOnFile();
                            	
                            }catch(IOException e) {
                            	System.out.println("O arquivo não foi encontrado para fazer o registro.");
                            }
                            
                        } else {
                        	PaymentMethods.errorOnPaymentMsg();
                        }
                        
                    } else if (paymentMethodOption == 2) {

                        System.out.println("Pagamento com cartão realizado com sucesso!");
                        vendingMachine.sell(selectedProduct, value); 
                      
                        System.out.printf("Retire o produto %s da máquina.\n", selectedProduct.getName());
                        Sale sale = new Sale(selectedProduct);
                        
                        try {
                        	sale.writeOnFile();
                        	
                        }catch(IOException e) {
                        	System.out.println("O arquivo não foi encontrado para fazer o registro da venda.");
                        }
                        
                    } else {
                    	PaymentMethods.errorOnPaymentMsg();
                    }
                    
                } else if (option == productsQuantity) {

                	vendingMachine.goodByeMsg();
                    menu = false;
                    
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Apenas números são permitidos. Tente novamente.");
                scanner.next(); 
                
            } catch (NoSuchElementException e) {
                vendingMachine.goodByeMsg();
                break; 
            }
        }
        scanner.close(); 
    }
}
