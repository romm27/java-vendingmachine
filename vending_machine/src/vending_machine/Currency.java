package vending_machine;

import java.util.ArrayList;

public class Currency {
	public enum CurrencyType{ bill, coin}
	private int quantityInStock;
	private String name;
	private int value;
	private int divider;
	private CurrencyType currencyType;
	public static  ArrayList<Currency> availableBills = new ArrayList<Currency>();
	
	// Defines the available bills
	static {
		availableBills.add(new Currency(10, "Um Centavo", 1, 100, CurrencyType.coin));
		availableBills.add(new Currency(10, "Cinco Centavos", 5, 100, CurrencyType.coin));
		availableBills.add(new Currency(10, "Dez Centavos", 10, 100, CurrencyType.coin));
		availableBills.add(new Currency(10, "Vinte e Cinco Centavos", 25, 100, CurrencyType.coin));
		availableBills.add(new Currency(10, "Cinquenta Centavos", 5, 10, CurrencyType.coin));
		availableBills.add(new Currency(10, "Um Real", 1, 1, CurrencyType.coin));
		availableBills.add(new Currency(10, "Dois Reais", 2, 1, CurrencyType.bill));
		availableBills.add(new Currency(10, "Cinco Reais", 5, 1, CurrencyType.bill));
		availableBills.add(new Currency(10, "Dez Reais", 10, 1, CurrencyType.bill));
		availableBills.add(new Currency(10, "Cinquenta Reais", 50, 1, CurrencyType.bill));
		availableBills.add(new Currency(10, "Cem Reais", 100, 1, CurrencyType.bill));
		

	}
	
	public static void main(String[] args) {
		printCurrency(availableBills);
	}
	
	public static ArrayList<Currency> getExchange(float value){
		ArrayList<Currency> temp = new ArrayList<Currency>();
		
		for(int i = availableBills.size(); i > 0; i--) {
			for(int j = 0; j < availableBills.get(i - 1).quantityInStock; j++) {
				if(value > availableBills.get(j).value) {
					value -= availableBills.get(j).value;
					temp.add(availableBills.get(j));
				}
			}
		}
		
		return temp;
	}
	
	public static void printCurrency(ArrayList<Currency> currencyList) {
		int acumulated = 1;
		for(int i = 0; i < currencyList.size(); i++) {
			Currency current = currencyList.get(i);
			Currency next = (i == (currencyList.size() - 1))? null : currencyList.get(i + 1);
			if(next == null || current.getDoubleValue() != next.getDoubleValue()) {
				System.out.printf("%dx - %s" + System.lineSeparator(), acumulated, currencyList.get(i).name);
				acumulated = 0;
			}
			acumulated += 1;
		}
	}
	
	public Currency(int quantityInStock, String name, int value, int divider, CurrencyType currencyType) {
		this.quantityInStock = quantityInStock;
		this.name = name;
		this.value = value;
		this.divider = divider;
		this.currencyType = currencyType;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRealValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public double getDoubleValue() {
		return (double)value/divider;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public int getQuantityAvailable() {
		return quantityInStock;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityInStock = quantityAvailable;
	}
}
