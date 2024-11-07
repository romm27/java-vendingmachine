package vending_machine;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VendingMachineConsole {

    public static void main(String[] args) throws NoChangeException {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();

        boolean menu = true;

        while (menu) {
            System.out.println("\n|------------------------------------ MENU ------------------------------------|\n");

            int i = 1;
            for (Product product : Product.getProducts()) {
                System.out.printf("[ %d ]\t%s (%s) - R$ %.2f\n", i, product.getName(), product.getBrand(), product.getPrice());
                i++;
            }
            System.out.printf("[ %d ]\tSair\n", i);
            System.out.print("\nDigite a opção desejada: ");
            
            try {
                int option = scanner.nextInt();

                if (option >= 1 && option < i) {
                
                    Product selectedProduct = Product.getProducts().get(option - 1);

                    if (!selectedProduct.isAvailable()) {
                        System.out.println("Produto indisponível no momento. Tente outro produto.");
                        continue;
                    }

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

                    int value = (int) (selectedProduct.getPrice() * 100); 
                    if (paymentMethodOption == 1) {
                        System.out.print("Digite o valor que irá inserir na máquina em reais: "); // convert/ verify int vs double
                        int payment = scanner.nextInt(); 
                        
                        if (paymentMethod.processPayment(payment)) {
                            vendingMachine.sell(selectedProduct, payment); 
                            
                            //write on json 
                            System.out.printf("Retire o produto %s da máquina.\n", selectedProduct.getName());
                            Sale sale = new Sale(selectedProduct);
                            try {
                            	sale.writeOnFile();
                            	
                            }catch(IOException e) {
                            	System.out.println("O arquivo não foi encontrado para fazer o registro.");
                            }
                            
                        } else {
                            System.out.println("Pagamento não foi processado. Tente novamente.");
                        }
                    } else if (paymentMethodOption == 2) {

                        System.out.println("Pagamento com cartão realizado com sucesso!");
                        vendingMachine.sell(selectedProduct, value); 
                        
                        //write on json
                        System.out.printf("Retire o produto %s da máquina.\n", selectedProduct.getName());
                        Sale sale = new Sale(selectedProduct);
                        try {
                        	sale.writeOnFile();
                        	
                        }catch(IOException e) {
                        	System.out.println("O arquivo não foi encontrado para fazer o registro.");
                        }
                    } else {
                        System.out.println("Pagamento não foi processado. Tente novamente.");
                    }
                } else if (option == i) {

                    System.out.println("Obrigado por usar a máquina de vendas! Volte sempre!");
                    menu = false;
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Apenas números são permitidos. Tente novamente.");
                scanner.next(); 
            } catch (NoSuchElementException e) {
                System.out.println("Obrigado por usar a máquina de vendas! Volte sempre!");
                break; 
            }
        }
        scanner.close(); 
    }
}
