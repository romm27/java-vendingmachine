package vending_machine;

public class Card extends PaymentMethods{
	@Override
	public boolean processPayment(int valor) {
		return true;
	}
	
}
