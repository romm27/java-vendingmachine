package vending_machine;

public abstract class PaymentMethod {
	private String name;
	private String image;
	
	public abstract boolean processPayment(int value);
}
