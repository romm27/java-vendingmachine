package vending_machine;
import java.util.Scanner;

public class VendingMachineConsole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

            switch (option) {
                case 1:
                    System.out.println("Você escolheu Amendoim.");
                    break;
                case 2:
                    System.out.println("Você escolheu Salgadinho.");
                    break;
                case 3:
                    System.out.println("Você escolheu Guaraná Zero.");
                    break;
                case 4:
                    System.out.println("Você escolheu Café.");
                    break;
                case 5:
                    System.out.println("Saindo do menu...");
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
