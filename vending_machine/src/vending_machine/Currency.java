package vending_machine;

public class Currency {
	public enum CurrencyType{ bill, coin}
	private String name;
	private int value;
	private int divider;
	private CurrencyType currencyType;
	public static Currency[] availableBills = new Currency[10];
	
	// Defines the available bills
	// 5, 10, 25, 50
	static {
		availableBills[0] = new Currency("Cinco Centavos", 5, 100, CurrencyType.coin);
		availableBills[1] = new Currency("Dez centavos", 10, 100, CurrencyType.coin);
		availableBills[2] = new Currency("Vinte e cinco centavos", 25, 100, CurrencyType.coin);
		availableBills[3] = new Currency("Cinquenta Centavos", 5, 10, CurrencyType.coin);
		availableBills[4] = new Currency("Um Real", 1, 1, CurrencyType.coin);
		availableBills[5] = new Currency("Dois Reais", 2, 1, CurrencyType.bill);
		availableBills[6] = new Currency("Cinco Reais", 5, 1, CurrencyType.bill);
		availableBills[7] = new Currency("Dez Reais", 10, 1, CurrencyType.bill);
		availableBills[8] = new Currency("Cinquenta Reais", 50, 1, CurrencyType.bill);
		availableBills[9] = new Currency("Cem Reais", 100, 1, CurrencyType.bill);
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < availableBills.length;i++) {
			System.out.println("R$" + availableBills[i].getValue());
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
