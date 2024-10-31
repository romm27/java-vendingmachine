package vending_machine;

public class Money extends PaymentMethods{
	@Override
	public boolean processPayment(int valor) {
		if (Currency.getChange(valor) != null) {
			System.out.println("Pagamento do valor " + valor/100 + " executado com sucesso!\n");
			return true;
			
		} else {
			
			return false;
		}
	}
}
