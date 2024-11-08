package vending_machine;

public class Card extends PaymentMethods{
	@Override
	public boolean processPayment(int valor) {
		System.out.println("Pagamento efetuado com sucesso!\n");
		return true;
	}
	
	@Override
	public void successPaymentMsg() {
		System.out.println("Pagamento em dinheiro realizado com sucesso!");
	}
}
