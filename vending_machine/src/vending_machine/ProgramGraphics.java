package vending_machine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ProgramGraphics {
    public static int WINDOW_SIZE_MULTIPLIER = 8;
    public static int WIDTH_RATIO = 49;
    public static int HEIGHT_RATIO = 105;
    
    public static int WINDOW_WIDTH = WIDTH_RATIO * WINDOW_SIZE_MULTIPLIER;
    public static int WINDOW_HEIGHT = HEIGHT_RATIO * WINDOW_SIZE_MULTIPLIER;

    public static JFrame createFrame() {
        JFrame frame = new JFrame("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

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
        		'>', '0', 'x' };
        
        for(int y = 0; y < 4; y++) {
        	for(int x = 0; x < 3; x++) {
        		JButton button = new JButton(String.valueOf(values[(3 - y)*3 + x]));
                int[] pos = normalizedToPixelPosition(initialX + x * deltaX, initialY + y * deltaY); 
                button.setBounds(pos[0], pos[1], 20, 20);
                button.setFont(new Font("Arial", Font.PLAIN, 10));
                button.setMargin(new Insets(0, 0, 0, 0));
                backgroundPanel.add(button);
            }
        }

        frame.add(backgroundPanel);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
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
        int pixelX = (int) Math.round(normalizedX * WINDOW_WIDTH);
        int pixelY = (int) Math.round(normalizedY * WINDOW_HEIGHT);
        return new int[] { pixelX, pixelY };
    }
}
