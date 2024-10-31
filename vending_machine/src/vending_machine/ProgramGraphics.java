package vending_machine;
import javax.swing.*;

public abstract class ProgramGraphics {
	public static int screenSizeMultiplier = 120;
	
	public static JFrame CreateFrame() {
		JFrame w = new JFrame();
		w.setBounds(130,100,100, 40);
		w.setSize(16 *screenSizeMultiplier,9 * screenSizeMultiplier);
		w.setExtendedState(JFrame.MAXIMIZED_BOTH);
		w.setVisible(true);
		w.add(new JLabel(new ImageIcon("images/temp.jpeg")));
		w.revalidate();
		return w;
	}
	
}
