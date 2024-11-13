package vending_machine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

public class ProgramGraphics {
    public static int WINDOW_WIDTH = 360;
    public static int WINDOW_HEIGHT = 840;
    
    private static String displayPlaceholder = "|";
    private static int rowSize = 3;
    
    private static String output = ":)";

    static JLabel productSelectedDisplay = new JLabel(":)");
    static JButton creditButton = new JButton("Crédito");
    static JButton cashButton = new JButton("Dinheiro");
    static JButton deleteButton;
    static JLabel cashSymbolDisplay = new JLabel("$");
    static JTextField cashInput = new JTextField("");
    static VendingMachine vendingMachine;
    static JFrame frame;
    static Slot lastValidSlot = null;

    private static ArrayList<ProductDisplay> productDisplays = new ArrayList<ProductDisplay>();
    
    public ProgramGraphics(VendingMachine vendingMachine) {
        createFrame(vendingMachine);
    }

    public static void createFrame(VendingMachine machine) {
        frame = new JFrame("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        vendingMachine = machine;

        BackgroundPanel backgroundPanel = new BackgroundPanel("images/machine_empty.png"); 
        backgroundPanel.setOpaque(false);
        backgroundPanel.setLayout(null); 
        
        // Numpad offsets
        int numpadInitialX = 280; 
        int numpadInitialY = 220;  
        int numpadDeltaX = 20;
        int numpadDeltaY = 20;
        
        // Keypad values
        char keypadValues[] = {
        		'<', '0', 'x',
        		'1', '2', '3', 
        		'4', '5', '6',
                '7', '8', '9'
                 };
       
        

        productSelectedDisplay.setBounds(numpadInitialX + 15, numpadInitialY - 80, 50, 20);
        productSelectedDisplay.setFont(new Font("Arial", Font.PLAIN, 25));
        productSelectedDisplay.setForeground(Color.black);
        backgroundPanel.add(productSelectedDisplay);
        

        creditButton.setBounds(numpadInitialX, numpadInitialY + 105, 60, 20);
        creditButton.setFont(new Font("Arial", Font.PLAIN, 10));
        creditButton.setMargin(new Insets(0, 0, 0, 0));
        creditButton.setEnabled(false);
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPurchase(lastValidSlot, PaymentMethods.card);
            }
        });
        backgroundPanel.add(creditButton);
        

        cashButton.setBounds(numpadInitialX, numpadInitialY + 150, 60, 20);
        cashButton.setFont(new Font("Arial", Font.PLAIN, 10));
        cashButton.setMargin(new Insets(0, 0, 0, 0));
        cashButton.setEnabled(false);
        cashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPurchase(lastValidSlot, PaymentMethods.cash);
            }
        });
        backgroundPanel.add(cashButton);
        

        cashSymbolDisplay.setBounds(numpadInitialX + 5, numpadInitialY + 150 + 25, 50, 20);
        cashSymbolDisplay.setFont(new Font("Arial", Font.PLAIN, 25));
        cashSymbolDisplay.setForeground(Color.white);
        backgroundPanel.add(cashSymbolDisplay);
        

        cashInput.setBounds(numpadInitialX + 20, numpadInitialY + 150 + 25, 40, 20);
        cashInput.setFont(new Font("Arial", Font.PLAIN, 15));
        cashInput.setPreferredSize(new Dimension(200, 30));
        cashInput.setEnabled(false);
        backgroundPanel.add(cashInput);
        
        // Numpad Buttons
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 3; x++) {
                int oneDimensionalIndex = (3 - y) * 3 + x;
                JButton button = new JButton(String.valueOf(keypadValues[oneDimensionalIndex]));
                button.setBounds(numpadInitialX + x * numpadDeltaX, numpadInitialY + y * numpadDeltaY, 20, 20);
                button.setFont(new Font("Arial", Font.PLAIN, 10));
                button.setMargin(new Insets(0, 0, 0, 0));
                
                if(keypadValues[oneDimensionalIndex] == '<') {
                    deleteButton = button;
                    deleteButton.setEnabled(false);
                }
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onClickNumpad(keypadValues[oneDimensionalIndex]);
                        frame.repaint();
                    }
                });
                backgroundPanel.add(button);
            }
        }

        // Slots
        int displayInitialX = 25;
        int displayInitialY = 30;
        int displayDeltaX = 80;
        int displayDeltaY = 132; 
        for (int i = 0; i < vendingMachine.getSlots().size(); i++) {
            int x = i % rowSize;
            int y = i / rowSize;
            ProductDisplay display = new ProductDisplay(vendingMachine.getSlot(i), displayInitialX + x * displayDeltaX, displayInitialY + y * displayDeltaY, frame);
            productDisplays.add(display);
            vendingMachine.getSlot(i).setProductDisplay(display);
        }

        frame.add(backgroundPanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    //Methods
    public static void setOuput(String value) {
    	output = value;
    	productSelectedDisplay.setText(output);
    }
    
    public static String getOutput() {
    	return output;
    }
    
    private static void onClickNumpad(char key) {
    	if(key == 'x') {
    		output = "||";
    		productSelectedDisplay.setText(output);
    		setValidPurchase(false);
    		deleteButton.setEnabled(false);
    		return;
    	}
    	else if(key == '<') {
    		if(output.length() != 0) {
	    		output = output.substring(0, output.length() - 1);
	    		if(output != "") {
	    			productSelectedDisplay.setText(output + "|");
	    		}
	    		else {
	    			productSelectedDisplay.setText("||");
	    		}
	    		setValidPurchase(false);
	    		return;
    		}
    		else {
    			return;
    		}
    	}
    	
    	deleteButton.setEnabled(true);
    	if(output.length() == 1) {
    		output += key;
    		productSelectedDisplay.setText(output);
    		
    		Slot slot = null;
    		try {
    			slot = vendingMachine.getSlot(Integer.valueOf(output));
    		}
    		catch (Exception e){
    			slot = null;
    		}
    		if(slot != null) {
    			setValidPurchase(true);
    			lastValidSlot = slot;
    		}
    		else {
    			setOuput(":/");
    			setValidPurchase(false);
    			deleteButton.setEnabled(false);
    		}
    	}
    	else {
    		output = String.valueOf(key);
    		productSelectedDisplay.setText(output + displayPlaceholder);
    	}
    }
    
    private static String getRandomSmile() {
        String[] smiles = new String[] {":D", "xD", ":)", ":>", ":V", ":P", ":]", ";)"};
        Random random = new Random();
        int index = random.nextInt(smiles.length);
        return smiles[index]; 
    }
    
    private static void onPurchase(Slot selectedSlot, PaymentMethods paymentMethods) {
    	String purchaseText = "";
    	try {
    	    switch (paymentMethods) {
    	        case card:
    	            vendingMachine.sell(selectedSlot, 999999, PaymentMethods.card);
    	            purchaseText = String.format("%s foi comprado(a) no crédito com sucesso!", selectedSlot.getProduct().getName());
    	            output = getRandomSmile();
    	            break;
    	        case cash:
    	        	int payment =  Math.round(Float.valueOf(cashInput.getText().replace(',', '.'))*100);
    	        	ArrayList<Currency> change = vendingMachine.sell(selectedSlot, payment, PaymentMethods.cash);
    	            purchaseText = String.format("%s foi comprado(a) em dinheiro com sucesso!", selectedSlot.getProduct().getName());
    	            output = getRandomSmile();
    	            String currencyDump = "";
    	            for (Currency changeBill : change) {
    	            	currencyDump += "\n" + changeBill.getQuantity() + " x " + changeBill.getName();
					}
    	            if(currencyDump.length() > 0) {
    	            	currencyDump = "\n aqui está o seu troco..." + currencyDump;
    	            }
    	            purchaseText += currencyDump;
    	            break;
    	        default:
    	            throw new IllegalArgumentException("Método de pagamento desconhecido!");
    	    }
    	} catch (NoChangeException e) {
    	    purchaseText = "Erro: Sem troco!";
    	    output = ":<";
    	} catch (ProductUnavailableException e) {
    	    purchaseText = "Erro: Sem estoque!";
    	    output = ":(";
    	} catch (InsufficientPaymentException e) {
    	    purchaseText = "Erro: Dinheiro insuficiente :/";
    	    output = ":c";
    	} catch (NumberFormatException e) {
    		purchaseText = "Erro: Por favor insira um valor válido!";
    		output = ":|";
    	} catch (Exception e) {
    	    purchaseText = "Erro inesperado: " + e.getMessage();
    	    output = ":o";
    	    e.printStackTrace();
    	}
    	
    	productSelectedDisplay.setText(output);
    	deleteButton.setEnabled(false);
    	showMessageDialog(null, purchaseText);
    }
    
    private static void setValidPurchase(boolean status) {
    	creditButton.setEnabled(status);
    	cashButton.setEnabled(status);
    	cashInput.setEnabled(status);

    }

    private static class BackgroundPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
                
                addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        repaint();
                    }
                });
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}