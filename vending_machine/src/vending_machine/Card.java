package vending_machine;

public class Card extends PaymentMethods{
	@Override
	public boolean processPayment(int valor) {
		System.out.println("Pagamento do valor " + valor/100 + " executado com sucesso!\n");
		return true;
	}
}
