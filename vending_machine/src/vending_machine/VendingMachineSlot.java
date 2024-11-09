package vending_machine;

import java.util.ArrayList;

public class VendingMachineSlot {
	private int id;
	private int quantity;
	private Product product;
	
	private static ArrayList<VendingMachineSlot> slots = new ArrayList<>();
	
    public VendingMachineSlot(int id, int quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }
    
    static {
        slots.add(new VendingMachineSlot(1, 8, Product.getProducts().get(0)));
        slots.add(new VendingMachineSlot(2, 8, Product.getProducts().get(1)));
        slots.add(new VendingMachineSlot(3, 8, Product.getProducts().get(2)));
        slots.add(new VendingMachineSlot(4, 8, Product.getProducts().get(3)));
        slots.add(new VendingMachineSlot(5, 8, Product.getProducts().get(4)));
    }
	
    public boolean hasProduct() {
    	return this.quantity > 0;   	
    }
    
    public void decreaseQuantity() {
    	if (hasProduct()) {
    		quantity -= 1;
    	}
    }
    
    public static void emptySlotMsg() {
    	System.out.println("Seção vazia. Escolha outro produto.");
    }
     
}
