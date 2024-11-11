package vending_machine;

import vending_machine.Currency.CurrencyType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Arrays;

public class CashRegister {
	public static HashMap<Integer, Currency> currencies = new HashMap<Integer, Currency>();
	
	static {
		currencies.put(1, new Currency(10, "um centavo", 1, CurrencyType.coin));
		currencies.put(10, new Currency(10, "dez centavos", 10, CurrencyType.coin));
		currencies.put(25, new Currency(10, "vinte e cinco centavos", 25, CurrencyType.coin));
		currencies.put(50, new Currency(10, "cinquenta centavos", 50, CurrencyType.coin));
		currencies.put(100, new Currency(10, "um real", 100, CurrencyType.coin));
		currencies.put(200, new Currency(10, "dois reais", 200, CurrencyType.bill));
		currencies.put(500, new Currency(10, "cinco reais", 500, CurrencyType.bill));
		currencies.put(1000, new Currency(10, "dez reais", 1000, CurrencyType.bill));
		currencies.put(5000, new Currency(10, "cinquenta reais", 5000, CurrencyType.bill));
		currencies.put(10000, new Currency(10, "cem reais", 10000, CurrencyType.bill));
	}
	
//	public static void main(String[] args) throws NoChangeException {
//		CashRegister cashRegister = new CashRegister();
//		ArrayList<Currency> change = cashRegister.withdraw(39585);
//		
//		for (Currency changeBill : change) {
//			System.out.println(changeBill.getQuantity() + " x " + changeBill.getName());
//		}
//	}
//	
	public ArrayList<Currency> getCurrency(int value) throws NoChangeException {
		ArrayList<Currency> changeCurrencies = new ArrayList<Currency>();
		int remainingValue = value;
		
		Object[] currencyValues = currencies.keySet().toArray();
		Arrays.sort(currencyValues, Collections.reverseOrder());
		
		for (Object currencyValue : currencyValues) {
			Currency currency = currencies.get(currencyValue);
			int requiredQuantity = remainingValue / currency.getValue();
			int quantity = currency.getQuantity() >= requiredQuantity ? requiredQuantity : currency.getQuantity();
			
			remainingValue -= quantity * currency.getValue();
			
			if (quantity > 0) {
				changeCurrencies.add(new Currency(quantity, currency.getName(), currency.getValue(), currency.getCurrencyType()));
			}
		}
		
		if (remainingValue > 0) {
			throw new NoChangeException();
		}

		return changeCurrencies;
	}
	
	public ArrayList<Currency> withdraw(int value) throws NoChangeException {
		ArrayList<Currency> changeCurrencies = this.getCurrency(value);

		for (Currency currency : changeCurrencies) {
			currencies.get(currency.getValue()).decrementQuantity(currency.getQuantity());
		}
		
		return changeCurrencies;
	}

	public boolean hasChange(int changeAmount) {
		try {
			this.getCurrency(changeAmount);
		} catch (NoChangeException e) {
			return false;
		} 
		
		return true;
	}
}
