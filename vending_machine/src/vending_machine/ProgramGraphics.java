package vending_machine;
import javax.swing.*;

public abstract class ProgramGraphics {
	public static JFrame CreateFrame() {
		JFrame w = new JFrame();
		w.setBounds(130,100,100, 40);
		w.setSize(500,400);
		w.setVisible(true);
		return w;
	}
	
}
