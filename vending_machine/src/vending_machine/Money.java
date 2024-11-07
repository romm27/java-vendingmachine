package vending_machine;

public class Money extends PaymentMethods{
	@Override
	public boolean processPayment(int value) {
		if (Currency.getChange(value) != null) {
			Currency.getChange(value);
			System.out.println("Pagamento do valor realizado com sucesso!\n");
			return true;
			
		} else {
			
			return false;
		}
	}
}
