package vending_machine;

public abstract class PaymentMethods {
	private String name;
	private String image;
	
	public abstract boolean processPayment(int valor);
	
	public abstract void successPaymentMsg();
	
	public static void generatePaymentMethodsMenu() {
		System.out.println("Escolha o método de pagamento:\n[ 1 ] Dinheiro\n[ 2 ] Cartão\n[ 3 ] Cancelar");
	}
	
	public static void errorOnPaymentMsg() {
		System.out.println("O pagamento não foi processado. Tente novamente.");
	}

	public void invalidPaymentOption() {
		System.out.println("Oopção de pagamento desconhecida. Tente novamente.");
	}
}
