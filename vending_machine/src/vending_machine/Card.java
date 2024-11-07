package vending_machine;

public class Card extends PaymentMethods{
	@Override
	public boolean processPayment(int valor) {
		System.out.println("Pagamento efetuado com sucesso!\n");
		return true;
	}
}
