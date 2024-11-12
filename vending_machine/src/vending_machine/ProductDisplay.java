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
	private static float priceDisplayOffsetX = 0.06f;
	private static float priceDisplayOffsetY = 0.13f;
	
	private static float idDisplayOffsetX = 0f;
	private static float idDisplayOffsetY = 0.13f;
	
	public ProductDisplay(Slot slot, float x, float y, JFrame frame) {
		this.slot = slot;
		boolean isEmptySlot = slot.getProduct() == null;
		
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
		slotDisplay = new JLabel(new ImageIcon(picture.getScaledInstance(60, 110, 0)));
		int[] productDisplayPos = ProgramGraphics.normalizedToPixelPosition(x, y); 
		slotDisplay.setBounds(productDisplayPos[0], productDisplayPos[1], 60, 120);
		slotDisplay.setSize(new Dimension(60, 120));
		frame.add(slotDisplay);
		
		if(!isEmptySlot) {
			priceDisplay = new JLabel(String.format("R$%.2f", (float)slot.getProduct().getPrice() / 100));
			int[] priceDisplayPos = ProgramGraphics.normalizedToPixelPosition(x + priceDisplayOffsetX, y + priceDisplayOffsetY); 
			priceDisplay.setBounds(priceDisplayPos[0], priceDisplayPos[1], 60, 30);
			priceDisplay.setForeground(Color.white);
			frame.add(priceDisplay);

			idDisplay = new JLabel(String.format("%02d -", slot.getProduct().getId()));
			int[] idDisplayPos = ProgramGraphics.normalizedToPixelPosition(x + idDisplayOffsetX, y + idDisplayOffsetY); 
			idDisplay.setBounds(idDisplayPos[0], idDisplayPos[1], 60, 30);
			idDisplay.setForeground(Color.white);
			frame.add(idDisplay);
		}
	}
}
