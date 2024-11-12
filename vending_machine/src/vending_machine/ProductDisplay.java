package vending_machine;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProductDisplay {
	private Slot slot;
	private JLabel slotDisplay;
	private JTextArea priceDisplay;
	private JTextArea idDisplay;
	
	private static String emptySlotImagePath = "images/empty_slot.png";
	private static int priceDisplayOffsetX = 0;
	private static int priceDisplayOffsetY = 0;
	
	private static int idDisplayOffsetX = 0;
	private static int idDisplayOffsetY = 0;
	
	public ProductDisplay(Slot slot, int x, int y, JFrame frame) {
		this.slot = slot;
		
		BufferedImage picture = null;
		try {
		if(slot.getProduct() == null) {
				picture = ImageIO.read(new File(emptySlotImagePath));
		}
		else {
			picture = ImageIO.read(new File(slot.getProduct().getImagePath()));
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		slotDisplay = new JLabel(new ImageIcon(picture.getScaledInstance(60, 110, y)));
		slotDisplay.setBounds(x, y, 60, 120);
		slotDisplay.setSize(new Dimension(60, 120));
		frame.add(slotDisplay);
	}
}
