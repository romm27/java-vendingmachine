package vending_machine;

public abstract class PaymentMethods {
	private String name;
	private String image;
	
	public abstract boolean processPayment(int valor);
	
	public static void errorOnPaymentMsg() {
		System.out.println("Pagamento não foi processado. Tente novamente.");
	}
	
	public static void generatePaymentMethodsMenu() {
		//implement this method latter
	}
}
