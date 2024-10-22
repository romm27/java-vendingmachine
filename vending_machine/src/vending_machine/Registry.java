package vending_machine;
import java.util.ArrayList;

// ArrayList sales, generate relatory of sales,
public class Registry {
    private ArrayList<Sale> sales = new ArrayList<>();

    public Registry (ArrayList<Sale> sales) {
    	this.sales = sales;
    }
    
    public void setSales(Sale sale) {
        sales.add(sale);
    }

    public void printSaleHistory(){
        System.out.println("Aqui está o histórico de vendas:");
        for(int i = 0; i < sales.size(); i++){
            System.out.printf("%s \n", sales.get(i).getName());
        }
    }   
    
    public static void main(String[] args) {
    	ArrayList<Sale> sales = new ArrayList<>();
        Registry registry = new Registry(sales);
        Sale sale = new Sale("ahhhhh");
        registry.setSales(sale);
        registry.printSaleHistory();
    }
}
