package vending_machine;

public class Card extends PaymentMethod {
	@Override
	public boolean processPayment(int value) {
		return true;
	}
}
