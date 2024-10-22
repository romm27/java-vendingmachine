package vending_machine;

import java.util.ArrayList;

public class Currency {
	public enum CurrencyType{ bill, coin}
	private String name;
	private int value;
	private int divider;
	private CurrencyType currencyType;
	public static  ArrayList<Currency> availableBills = new ArrayList<Currency>();
	
	// Defines the available bills
	static {
		availableBills.add(new Currency("Um Centavo", 5, 100, CurrencyType.coin));
		availableBills.add(new Currency("Cinco Centavos", 5, 100, CurrencyType.coin));
		availableBills.add(new Currency("Dez Centavos", 10, 100, CurrencyType.coin));
		availableBills.add(new Currency("Vinte e Cinco Centavos", 25, 100, CurrencyType.coin));
		availableBills.add(new Currency("Cinquenta Centavos", 5, 10, CurrencyType.coin));
		availableBills.add(new Currency("Um Real", 1, 1, CurrencyType.coin));
		availableBills.add(new Currency("Dois Reais", 2, 1, CurrencyType.bill));
		availableBills.add(new Currency("Cinco Reais", 5, 1, CurrencyType.bill));
		availableBills.add(new Currency("Dez Reais", 10, 1, CurrencyType.bill));
		availableBills.add(new Currency("Cinquenta Reais", 50, 1, CurrencyType.bill));
		availableBills.add(new Currency("Cem Reais", 100, 1, CurrencyType.bill));

	}
	
	public static void main(String[] args) {
		for(int i = 0; i < availableBills.size();i++) {
			System.out.println("R$" + availableBills.get(i).getValue());
		}

	}
	
	public Currency(String name, int value, int divider, CurrencyType currencyType) {
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
	public double getValue() {
		return (double)value/divider;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}
}
