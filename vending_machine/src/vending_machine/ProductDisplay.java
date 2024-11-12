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
	private JLabel priceDisplay;
	private JLabel idDisplay;
	
	private static String emptySlotImagePath = "images/empty_slot.png";
	
	public ProductDisplay(Slot slot, int x, int y, JFrame frame) {
		this.slot = slot;
		boolean isEmptySlot = slot.getProduct() == null;
		
		BufferedImage picture = null;
		try {
			if (slot.getProduct() == null) {
				picture = ImageIO.read(new File(emptySlotImagePath));
			} else {
				picture = ImageIO.read(new File(slot.getProduct().getImagePath()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		slotDisplay = new JLabel(new ImageIcon(picture.getScaledInstance(60, 110, 0)));
		slotDisplay.setBounds(x, y, 60, 120);
		slotDisplay.setSize(new Dimension(60, 120));
		frame.add(slotDisplay);
		

		int price = isEmptySlot ? 0 : slot.getProduct().getPrice();
		priceDisplay = new JLabel(String.format("R$%02.2f", (float) price / 100));
		
		int priceX = 20;
		int priceY = 110;
		priceDisplay.setBounds(x + priceX, y + priceY, 60, 30);
		priceDisplay.setForeground(Color.white);
		frame.add(priceDisplay);

		idDisplay = new JLabel(String.format("%02d", slot.getId()));

		int idX = priceX - 20;
		int idY = priceY + 0;
		idDisplay.setBounds(x + idX, y + idY, 60, 30);
		idDisplay.setForeground(Color.white);
		frame.add(idDisplay);
	}
	
	public void checkForEmptySlot() {
		if (!slot.hasProduct()) {
			BufferedImage picture;
			try {
				picture = ImageIO.read(new File(emptySlotImagePath));
				slotDisplay.setIcon(new ImageIcon(picture.getScaledInstance(60, 110, 0)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
