package vending_machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import vending_machine.Product.ProductType;

public class VendingMachine {
	private ArrayList<Slot> slots = new ArrayList<Slot>();
    private ArrayList<Product> products = new ArrayList<Product>();
	private CashRegister cashRegister = new CashRegister();
	
    public VendingMachine() {
    	//TODO ler os produtos de um CSV, criar o arraylist de slots e passar como parametro do construtor
        // read csv
        File productsPath = new File("src" + FileSystems.getDefault().getSeparator() 
				+ "vending_machine" + FileSystems.getDefault().getSeparator()
				+ "resources" + FileSystems.getDefault().getSeparator() + "products.csv");
        
        BufferedReader reader = null;
            String line;
            try {
                reader = new BufferedReader(new FileReader(productsPath));
                int id = 0;
                while ((line = reader.readLine()) != null) {
                    String[] csvProduct = line.split(", ");
                    Product product = new Product(Integer.parseInt(csvProduct[0]),
                    		csvProduct[1],
                    		csvProduct[2],
                            ProductType.valueOf(csvProduct[3]),
                            Integer.parseInt(csvProduct[4]
                        ));
                    products.add(product);
                    slots.add(new Slot(id,8, product));
                    id++;
                }
            } catch (FileNotFoundException e) {
                System.err.println("Arquivo não encontrado: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Erro ao ler arquivo: " + e.getMessage());
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        System.err.println("Erro ao fechar o arquivo: " + e.getMessage());
                    }
                }
            }   
    }
 
	public void validateSale(Slot slot, int payment) throws PaymentCannotBeProcessedException, ProductUnavailableException, InsufficientPaymentException {
		if (!slot.hasProduct()) {
            throw new ProductUnavailableException();
        }

        int productPrice = slot.getProduct().getPrice();
        if (payment < productPrice) {
            throw new InsufficientPaymentException();
        }
        
        int changeAmount = payment - productPrice;
        if (!cashRegister.hasChange(changeAmount)) {
        	throw new PaymentCannotBeProcessedException();
        }
    }
	
	public ArrayList<Currency> sell(Slot slot, int payment, PaymentMethods selectedPaymentMethod)
			throws ProductUnavailableException, InsufficientPaymentException, NoChangeException, PaymentCannotBeProcessedException {
		this.validateSale(slot, payment);
		
		PaymentMethod paymentMethod = selectedPaymentMethod == PaymentMethods.cash ? new Cash() : new Card();
		paymentMethod.processPayment(payment);
		
		int changeAmount = payment - slot.getProduct().getPrice();
		ArrayList<Currency> change = null;
		if (selectedPaymentMethod == PaymentMethods.cash) {
			change = this.cashRegister.withdraw(changeAmount);
		}

		Sale sale = new Sale(slot.getProduct());
		sale.canWriteToFile();
		
		slot.decreaseQuantity();
		
		return change;
	}
	
	public Slot getSlot(int id) {
		try {
			return slots.get(id);
		}
		catch (Exception e){
			return null;
		}
	}
	
    public ArrayList<Slot> getSlots() {
        return slots;
    }
}
