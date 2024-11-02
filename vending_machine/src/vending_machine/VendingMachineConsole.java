package vending_machine;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VendingMachineConsole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();

        Product amendoim = new Product(0, "Amendoim", "Yoki", Product.ProductType.food, 200, 10);
        Product salgadinho = new Product(1, "Salgadinho", "Elma Chips", Product.ProductType.food, 300, 10);
        Product guarana = new Product(2, "Guaraná Zero", "Coca-Cola", Product.ProductType.beverage, 250, 10);
        Product cafe = new Product(3, "Café", "3 Corações", Product.ProductType.beverage, 150, 10);


        boolean menu = true;

        while (menu) {
            System.out.println("""
            \n--- MENU ---
            1. Amendoim
            2. Salgadinho
            3. Guaraná Zero
            4. Café
            5. Sair
            Digite a opção desejada: 
            """);
            
            try{
                int option = scanner.nextInt();
            
                if (option >= 1 && option <= 4) {
                        System.out.println("Realize o pagamento: "); // Modify later
                        double payment = scanner.nextDouble();
                        switch (option) {
                            case 1:
                                System.out.println("Você escolheu Amendoim.");
                                vendingMachine.sell(amendoim, payment);
                                break;
                            case 2:
                                System.out.println("Você escolheu Salgadinho.");
                                vendingMachine.sell(salgadinho, payment);
                                break;
                            case 3:
                                System.out.println("Você escolheu Guaraná Zero.");
                                vendingMachine.sell(guarana, payment);
                                break;
                            case 4:
                                System.out.println("Você escolheu Café.");
                                vendingMachine.sell(cafe, payment);
                                break;
                            default:
                                break;
                        }
                    } else if (option == 5) {
                        
                        System.out.println("Obrigado por usar a máquina de vendas! Volte sempre!");
                        menu = false;
                    } else {
                        
                        System.out.println("Opção inválida. Tente novamente.");
                    }
            }catch (InputMismatchException e) {
                System.out.println("Apenas números são permitidos. Tente novamente.");
                scanner.next();
                continue;
            }catch (NoSuchElementException e){
                System.out.println("Obrigado por usar a máquina de vendas! Volte sempre!");
                break;
            }
        }
        scanner.close();
    }
}
