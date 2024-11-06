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
        JFrame frame = new JFrame("Resizable Background Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        BackgroundPanel backgroundPanel = new BackgroundPanel("images/machine.png"); 
        backgroundPanel.setLayout(null); 
        
        // Test button
        JButton button = new JButton("Sample Button");
        int[] pos = normalizedToPixelPosition(0.5, 0.5); 
        button.setBounds(pos[0], pos[1], 50, 50);  
        backgroundPanel.add(button);

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
