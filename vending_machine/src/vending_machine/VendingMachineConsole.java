package vending_machine;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VendingMachineConsole {

    public static void main(String[] args) {
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
            System.out.print("\n Digite a opção desejada: ");
            
            try {
                int option = scanner.nextInt();

                if (option >= 1 && option < i) {
                    Product selectedProduct = Product.getProducts().get(option - 1);

                    System.out.printf("Você escolheu %s. Realize o pagamento: R$ %.2f\n", selectedProduct.getName(), selectedProduct.getPrice());
                    double payment = scanner.nextDouble();

                    vendingMachine.sell(selectedProduct, payment);
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
