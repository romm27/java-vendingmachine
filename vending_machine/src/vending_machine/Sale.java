package vending_machine;
import java.util.ArrayList;
//ArrayList sales, generate relatory of sales,

public class Sale {
    private static ArrayList<Sale> sales = new ArrayList<>();
    private Product product;
    
 
	public void printSaleHistory(){
        System.out.println("Aqui está o histórico de vendas:");
        double totalValue = 0;
        for(int i = 0; i < sales.size(); i++){
            System.out.printf("O produto %s custou %f reais \n",
            sales.get(i).product.getName(),
            sales.get(i).product.getPrice()
            );
         totalValue = totalValue + sales.get(i).product.getPrice();
        }
        System.out.printf("\nO valor total de vendas foi de: %.2f", totalValue);
	}
	
	
    public static void main(String[] args) { 	
        
    }
    
}