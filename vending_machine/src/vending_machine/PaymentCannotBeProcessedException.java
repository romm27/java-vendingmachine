package vending_machine;

public class PaymentCannotBeProcessedException extends Exception {
	public PaymentCannotBeProcessedException(Exception e) {
		super(e);
	}
}
