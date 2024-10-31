package vending_machine;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Sale {
    private static ArrayList<Sale> sales = new ArrayList<>();
    private Product product;
    private String time;
    private String date;
    
    public Sale (Product product) {
    	product.decreaseStock();
        Format timeFormat = new SimpleDateFormat("HH:mm");
        Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        time = timeFormat.format(new Date());
        date = dateFormat.format(new Date());
        this.product = product;
    }
    
	public void generateSalesReport(){
        System.out.println("Aqui está o histórico de vendas:");
        double totalValue = 0;
        for(Sale s: sales){
            System.out.printf("O produto '%s' custou %.2fR$ | Data da venda: %s às %sh.\n",
            s.product.getName(),
            s.product.getPrice(),
            s.getDate(),
            s.getTime()
            );
            
         totalValue = totalValue + s.product.getPrice();
        }
        
        System.out.printf("\nO valor total de vendas foi de: %.2fR$", totalValue);
	}
    
    public String getTime() {
    	return time;
    }
    
    public String getDate() {
    	return date;
    }
    
    public ArrayList<Sale> getSales(){
    	return sales;
    }
}