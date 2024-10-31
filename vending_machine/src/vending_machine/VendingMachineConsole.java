package vending_machine;
import java.util.Scanner;

public class VendingMachineConsole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();


        boolean menu = true;

        while (menu) {
            System.out.println("\n--- MENU ---"
                    + "\n1. Amendoim"
                    + "\n2. Salgadinho"
                    + "\n3. Guaraná Zero"
                    + "\n4. Café"
                    + "\n5. Sair"
                    + "\nDigite a opção desejada: ");

            int option = scanner.nextInt();

            if (option >= 1 && option <= 4) {
                System.out.println("Realize o pagamento: "); // Modify later
                double payment = scanner.nextDouble();
                
                switch (option) {
                    case 1:
                        System.out.println("Você escolheu Amendoim.");
                        // vendingMachine.sell(amendoim, payment);
                        break;
                    case 2:
                        System.out.println("Você escolheu Salgadinho.");
                        // vendingMachine.sell(salgadinho, payment);
                        break;
                    case 3:
                        System.out.println("Você escolheu Guaraná Zero.");
                        // vendingMachine.sell(guarana, payment);
                        break;
                    case 4:
                        System.out.println("Você escolheu Café.");
                        // vendingMachine.sell(cafe, payment);
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
        }

        scanner.close();
    }
}
