package vending_machine;

public class Cash extends PaymentMethod{
	@Override
	public boolean processPayment(int value) {
		return true;
	}
}
