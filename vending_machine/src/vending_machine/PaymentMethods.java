package vending_machine;

public abstract class PaymentMethods {
	private String name;
	private String image;
	
	public abstract boolean processPayment(int valor);
}