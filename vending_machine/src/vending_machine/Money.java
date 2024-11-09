package vending_machine;

public class Money extends PaymentMethods{
	@Override
	public boolean processPayment(int value) {
		if (Currency.getChange(value) != null) {
			Currency.getChange(value);
			System.out.println("Pagamento recebido.\n");
			return true;
			
		} else {
			return false;
		}
	}
	
	@Override
	public void successPaymentMsg() {
		System.out.println("Pagamento em dinheiro realizado com sucesso!");
	}
}
