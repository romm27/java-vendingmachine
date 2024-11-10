package vending_machine;

public class Money extends PaymentMethods{
	@Override
	public boolean processPayment(int value) throws NoChangeException {
		CashRegister cashRegister = new CashRegister();
		cashRegister.withdraw(value);
		return true;
	}
	
}
