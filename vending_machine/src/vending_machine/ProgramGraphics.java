package vending_machine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.GraphicsDevice;

public class ProgramGraphics {
    public static int WINDOW_SIZE_MULTIPLIER = 8;
    public static int WIDTH_RATIO = 49;
    public static int HEIGHT_RATIO = 105;
    
    public static int WINDOW_WIDTH = WIDTH_RATIO * WINDOW_SIZE_MULTIPLIER;
    public static int WINDOW_HEIGHT = HEIGHT_RATIO * WINDOW_SIZE_MULTIPLIER;
    
    private static String displayPlaceholder = "|";
    
    private static String output = ":)";

    static JLabel productSelectedDisplay = new JLabel(":)");
    static JButton creditButton = new JButton("Crédito");
    static JButton cashButton = new JButton("Dinheiro");
    static JButton deleteButton;
    static JLabel cashSymbolDisplay = new JLabel("$");
    static JTextField cashInput = new JTextField("");
    static VendingMachine vendingMachine;
    static JFrame frame;
    
    public ProgramGraphics(VendingMachine vendingMachine) {
    	createFrame(vendingMachine);
    }

    public static void createFrame(VendingMachine machine) {
        frame = new JFrame("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        vendingMachine = machine;

        BackgroundPanel backgroundPanel = new BackgroundPanel("images/machine.png"); 
        backgroundPanel.setLayout(null); 
        
        //Numpad Settings
        float initialX = 0.785f;
        float initialY = 0.34f;
        float deltaX = 0.05f;
        float deltaY = -0.025f;
        
        // Keypad
        
        char values[] = {
        		'7', '8', '9', 
        		'4', '5', '6',
        		'1', '2', '3', 
        		'<', '0', 'x' };
        
        char commands[] = {
        		'7', '8', '9', 
        		'4', '5', '6',
        		'1', '2', '3', 
        		'<', '0', 'x' };
        
        
        //productSelectionDisplay
        int[] displayPos = normalizedToPixelPosition(initialX + 0.04, initialY - 0.175); 
        productSelectedDisplay.setBounds(displayPos[0], displayPos[1], 50, 20);
        productSelectedDisplay.setFont(new Font("Arial", Font.PLAIN, 25));
        productSelectedDisplay.setForeground(Color.green);
        backgroundPanel.add(productSelectedDisplay);
        
		//creditButton
        int[] creditButtonPos = normalizedToPixelPosition(initialX, initialY + 0.075); 
        creditButton.setBounds(creditButtonPos[0], creditButtonPos[1], 60, 20);
        creditButton.setFont(new Font("Arial", Font.PLAIN, 10));
        creditButton.setMargin(new Insets(0, 0, 0, 0));
        creditButton.setEnabled(false);
        backgroundPanel.add(creditButton);
        
        //cashButton 
        int[] cashButtonPos = normalizedToPixelPosition(initialX, initialY + 0.075 + 0.03); 
        cashButton.setBounds(cashButtonPos[0], cashButtonPos[1], 60, 20);
        cashButton.setFont(new Font("Arial", Font.PLAIN, 10));
        cashButton.setMargin(new Insets(0, 0, 0, 0));
        cashButton.setEnabled(false);
        backgroundPanel.add(cashButton);
        
        //cashSymbolDisplay
        int[] cashSymbolPos = normalizedToPixelPosition(initialX + 0.11, initialY + 0.075 + 0.03 + 0.03); 
        cashSymbolDisplay.setBounds(cashSymbolPos[0], cashSymbolPos[1], 50, 20);
        cashSymbolDisplay.setFont(new Font("Arial", Font.PLAIN, 25));
        cashSymbolDisplay.setForeground(Color.white);
        backgroundPanel.add(cashSymbolDisplay);
        
        //cashInput
        int[] cashInputPos = normalizedToPixelPosition(initialX, initialY + 0.075 + 0.03 + 0.03); 
        cashInput.setBounds(cashInputPos[0], cashInputPos[1], 40, 20);
        cashInput.setFont(new Font("Arial", Font.PLAIN, 15));
        cashInput.setPreferredSize(new Dimension(200, 30));
        cashInput.setEnabled(false);
        backgroundPanel.add(cashInput);
        
        for(int y = 0; y < 4; y++) {
        	for(int x = 0; x < 3; x++) {
        		int oneDimensionalIndex = (3 - y)*3 + x; // Converts x and y to the command/values index
        		JButton button = new JButton(String.valueOf(values[oneDimensionalIndex]));
                int[] buttonPos = normalizedToPixelPosition(initialX + x * deltaX, initialY + y * deltaY); 
                button.setBounds(buttonPos[0], buttonPos[1], 20, 20);
                button.setFont(new Font("Arial", Font.PLAIN, 10));
                button.setMargin(new Insets(0, 0, 0, 0));
                
                if(commands[oneDimensionalIndex] == '<') {
                	deleteButton = button;
                	deleteButton.setEnabled(false);
                }
                
                button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickNumpad(commands[oneDimensionalIndex], productSelectedDisplay);
						frame.repaint();
					}
                });
                
                backgroundPanel.add(button);
            }
        }

        frame.add(backgroundPanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    //Methods
    public static void setOuput(String value, JLabel target) {
    	output = value;
    	target.setText(output);
    }
    
    public static String getOutput() {
    	return output;
    }
    
    private static void onClickNumpad(char key, JLabel target) {
    	if(key == 'x') {
    		output = "||";
    		target.setText(output);
    		setValidPurchase(false);
    		deleteButton.setEnabled(false);
    		return;
    	}
    	else if(key == '<') {
    		if(output.length() != 0) {
	    		output = output.substring(0, output.length() - 1);
	    		if(output != "") {
	    			target.setText(output + "|");
	    		}
	    		else {
	    			target.setText("||");
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
    		target.setText(output);
    		
    		Slot slot = null;
    		try {
    			slot = vendingMachine.getSlot(Integer.valueOf(output));
    		}
    		catch (Exception e){
    			slot = null;
    		}
    		if(slot != null) {
    			setValidPurchase(true);
    		}
    		else {
    			setOuput(":/", target);
    			setValidPurchase(false);
    			deleteButton.setEnabled(false);
    		}
    	}
    	else {
    		output = String.valueOf(key);
    		target.setText(output + displayPlaceholder);
    	}
    }
    
    private static void setValidPurchase(boolean status) {
    	creditButton.setEnabled(status);
    	cashButton.setEnabled(status);
    	cashInput.setEnabled(status);
    	
    	if(!status) {
    		cashInput.setText("");
    	}
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

    public static int[] normalizedToPixelPosition(double normalizedX, double normalizedY) {
        double scaleX = Toolkit.getDefaultToolkit().getScreenResolution() / 96.0; 
        double scaleY = scaleX; 
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        scaleX = gd.getDefaultConfiguration().getDefaultTransform().getScaleX();
        scaleY = gd.getDefaultConfiguration().getDefaultTransform().getScaleY();
        
        int adjustedWindowWidth = (int) (WINDOW_WIDTH * scaleX);
        int adjustedWindowHeight = (int) (WINDOW_HEIGHT * scaleY);

        int pixelX = (int) Math.round(normalizedX * adjustedWindowWidth);
        int pixelY = (int) Math.round(normalizedY * adjustedWindowHeight);
        
        return new int[] { pixelX, pixelY };
    }
}
