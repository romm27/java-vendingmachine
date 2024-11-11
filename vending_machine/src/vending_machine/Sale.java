package vending_machine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vending_machine.Product.ProductType;

public class Sale {
    private static ArrayList<Sale> sales = new ArrayList<>();
    private Product product;
    private String time;
    private String date;
    
    public Sale (Product product) {
        Format timeFormat = new SimpleDateFormat("HH:mm");
        Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        time = timeFormat.format(new Date());
        date = dateFormat.format(new Date());
        this.product = product;
        sales.add(this);
    }
    
	public ArrayList<String> generateSalesReport(){
		ArrayList<String> report = new ArrayList<String>();
        report.add("Aqui está o histórico de vendas:\n");
        int totalValue = 0;
        for (Sale s: sales) {
            report.add(String.format("O produto '%s' custou R$ %s | Data da venda: %s às %sh.\n",
						            s.product.getName(),
						            s.product.formatToCurrency(),
						            s.getDate(),
						            s.getTime()
						            )
           );
            
         totalValue = totalValue + s.product.getPrice();
        }
        
        report.add(String.format("\nO valor total de vendas foi de: R$ %s", totalValue));
        return report;
	}
	
	public void writeReport() throws IOException {

		File reportPath = new File("src" + FileSystems.getDefault().getSeparator() 
				+ "vending_machine" + FileSystems.getDefault().getSeparator()
				+ "resources" + FileSystems.getDefault().getSeparator() + "report.txt");
		
		if (!reportPath.exists()) {
			reportPath.createNewFile();
		}
		
		FileWriter writeReport = new FileWriter(reportPath, false);
		ArrayList<String> reportMessage = new ArrayList<String>();
		reportMessage = generateSalesReport();
		
		for(String s: reportMessage) {
			writeReport.append(s);
		}
		
		writeReport.flush();
		writeReport.close();
		
	}
	
	public void writeReportOnFile () throws IOException {
		File reportPath = new File("src" + FileSystems.getDefault().getSeparator() 
				+ "vending_machine" + FileSystems.getDefault().getSeparator()
				+ "resources" + FileSystems.getDefault().getSeparator()+ "report.txt");
		
		if (!reportPath.exists()) {
			reportPath.createNewFile();
		}
		
		FileWriter writeReport = new FileWriter(reportPath, true);
		
		String message = String.format("O produto '%s' custou R$ %s | Data da venda: %s às %sh.\n",
	            this.product.getName(),
	            this.product.formatToCurrency(),
	            this.getDate(),
	            this.getTime()
	            );
		
		writeReport.append(message);
		writeReport.flush();
		writeReport.close();	
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
    
    public void canWriteToFile() {
        try {
        	this.writeReportOnFile();
        } catch(IOException e) {
        	System.out.println("O arquivo não foi encontrado para fazer o registro da venda.");
        }
    }
    
}