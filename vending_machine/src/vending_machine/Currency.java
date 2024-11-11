package vending_machine;

public class Currency {
	public enum CurrencyType { bill, coin }
	private int quantity;
	private String name;
	private int value;
	private CurrencyType currencyType;
	
	public Currency(int quantity, String name, int value, CurrencyType currencyType) {
		this.quantity = quantity;
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
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void decrementQuantity(int amount) {
		this.quantity = quantity - amount;
	}

}
