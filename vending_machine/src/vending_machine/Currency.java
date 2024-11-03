package vending_machine;

import java.util.ArrayList;

public class Currency {
	//Config
	private static int currencyDecimalDivider = 100;
	
	public enum CurrencyType{ bill, coin}
	private int quantityInStock;
	private String name;
	private int value;
	private CurrencyType currencyType;
	public static  ArrayList<Currency> availableBills = new ArrayList<Currency>();
	
	// Defines the available bills
	static {
		availableBills.add(new Currency(10, "Um Centavo", 1, CurrencyType.coin));
		availableBills.add(new Currency(10, "Cinco Centavos", 5, CurrencyType.coin));
		availableBills.add(new Currency(10, "Dez Centavos", 10, CurrencyType.coin));
		availableBills.add(new Currency(10, "Vinte e Cinco Centavos", 25, CurrencyType.coin));
		availableBills.add(new Currency(10, "Cinquenta Centavos", 50, CurrencyType.coin));
		availableBills.add(new Currency(10, "Um Real", 100, CurrencyType.coin));
		availableBills.add(new Currency(10, "Dois Reais", 200, CurrencyType.bill));
		availableBills.add(new Currency(10, "Cinco Reais", 500, CurrencyType.bill));
		availableBills.add(new Currency(10, "Dez Reais", 1000, CurrencyType.bill));
		availableBills.add(new Currency(10, "Cinquenta Reais", 5000, CurrencyType.bill));
		availableBills.add(new Currency(10, "Cem Reais", 10000, CurrencyType.bill));
	}
	
	public static void main(String[] args) {
		printCurrency(getChange(395.85));
	}
	
	public static ArrayList<Currency> getChange(double value){
		ArrayList<Currency> temp = new ArrayList<Currency>();
		
		int realValue = (int)Math.round(value * currencyDecimalDivider);
		
		System.out.println(realValue);
		
		for(int i = availableBills.size() - 1; i > 0; i--) {
			for(int j = 0; j < availableBills.get(i - 1).quantityInStock; j++) {
				if(realValue >= availableBills.get(i).getRealValue()) {
					realValue -= availableBills.get(i).getRealValue();
					temp.add(availableBills.get(i));
				}
			}
		}
		
		if(realValue == 0) {
			for(int i = 0; i < temp.size();i++) {
				temp.get(i).quantityInStock -= 1;
			}
			return temp;
		}
		else {
			return null;
		}
	}
	
	public static boolean printCurrency(ArrayList<Currency> currencyList) {
		boolean valid = currencyList != null &&  currencyList.size() != 0;
		if(valid) {
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
		return valid;
	}
	
	public Currency(int quantityInStock, String name, int value, CurrencyType currencyType) {
		this.quantityInStock = quantityInStock;
		this.name = name;
		this.value = value;
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
		return (double)value / currencyDecimalDivider;
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
